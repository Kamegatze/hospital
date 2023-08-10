package com.kamegatze.hospital.servisies;


import com.kamegatze.hospital.DTO.PatientDTO;
import com.kamegatze.hospital.models.Patient;
import com.kamegatze.hospital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientDTO> getAll() {
        PatientDTO patientDTO = new PatientDTO();
        return patientDTO.getPatientDTOToList(patientRepository.findAll());
    }

    public PatientDTO getById(int id) {
        Patient patient = this.patientRepository.getReferenceById(id);
        //форматирование данных под нужный формат
        PatientDTO patientDTO = new PatientDTO();
        patientDTO = patientDTO.getPatientDTOToList(List.of(patient)).get(0);
        patientDTO.setDoctorsDTOS(patient.getDoctors());

        return patientDTO;
    }

    public Patient lastPatient () {
        return this.patientRepository.findLastRecord();
    }
    @Transactional
    public void addPatient(Patient patient) {
        patientRepository.save(patient);
    }

    @Transactional
    public void updatePatient(Patient patient) {
        patientRepository.save(patient);
    }
    @Transactional
    public void removePatient(Integer id) {
        patientRepository.deleteById(id);
    }
}
