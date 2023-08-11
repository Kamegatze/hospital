package com.kamegatze.hospital.servisies;


import com.kamegatze.hospital.DTO.PatientDTOList;
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

    public List<PatientDTOList> getAll() {
        PatientDTOList patientDTOList = new PatientDTOList();
        return patientDTOList.getPatientDTOToList(patientRepository.findAll());
    }

    public PatientDTOList getById(int id) {
        Patient patient = this.patientRepository.getReferenceById(id);

        PatientDTOList patientDTOList = new PatientDTOList();
        patientDTOList = patientDTOList.getPatientDTOToList(List.of(patient)).get(0);
        patientDTOList.setDoctorsDTOS(patient.getDoctors());

        return patientDTOList;
    }

    public PatientDTOList lastPatient () {
        Patient patient = this.patientRepository.findLastRecord();
        PatientDTOList patientDTOList = new PatientDTOList();

        patientDTOList.setId(patient.getId());
        patientDTOList.setFirstName(patient.getFirstName());
        patientDTOList.setPatronymic(patient.getPatronymic());
        patientDTOList.setLastName(patient.getLastName());
        patientDTOList.setDisease(patient.getDisease());
        patientDTOList.setAge(patient.getAge());
        patientDTOList.setDoctorsDTOS(patient.getDoctors());

        return patientDTOList;
    }
    @Transactional
    public void addAndUpdatePatient(PatientDTOList patientDTOList) {
        Patient patient = new Patient();

        patient.setId(patientDTOList.getId());
        patient.setFirstName(patientDTOList.getFirstName());
        patient.setPatronymic(patientDTOList.getPatronymic());
        patient.setLastName(patientDTOList.getLastName());
        patient.setDisease(patientDTOList.getDisease());
        patient.setAge(patientDTOList.getAge());

        patientRepository.save(patient);
    }

    @Transactional
    public void removePatient(Integer id) {
        patientRepository.deleteById(id);
    }
}
