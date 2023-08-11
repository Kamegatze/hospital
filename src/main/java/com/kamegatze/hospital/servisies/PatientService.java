package com.kamegatze.hospital.servisies;


import com.kamegatze.hospital.DTO.PatientDTO;
import com.kamegatze.hospital.DTO.PatientDTOList;
import com.kamegatze.hospital.models.Doctor;
import com.kamegatze.hospital.models.Patient;
import com.kamegatze.hospital.repositories.DoctorRepository;
import com.kamegatze.hospital.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    private final DoctorRepository doctorRepository;


    public List<PatientDTOList> getAll() {
        PatientDTOList patientDTO = new PatientDTOList();
        return patientDTO.getPatientDTOList(patientRepository.findAll());
    }

    public PatientDTOList getById(int id) {
        Patient patient = this.patientRepository.getReferenceById(id);

        PatientDTOList patientDTO = new PatientDTOList();
        patientDTO = patientDTO.getPatientDTOList(List.of(patient)).get(0);
        patientDTO.setDoctorsDTOS(patient.getDoctors());

        return patientDTO;
    }

    public PatientDTOList lastPatient () {
        Patient patient = this.patientRepository.findLastRecord();
        PatientDTOList patientDTO = new PatientDTOList();

        patientDTO.setId(patient.getId());
        patientDTO.setFirstName(patient.getFirstName());
        patientDTO.setPatronymic(patient.getPatronymic());
        patientDTO.setLastName(patient.getLastName());
        patientDTO.setDisease(patient.getDisease());
        patientDTO.setAge(patient.getAge());
        patientDTO.setDoctorsDTOS(patient.getDoctors());

        return patientDTO;
    }
    @Transactional
    public void addAndUpdatePatient(PatientDTO patientDTO) {
        Patient patient = new Patient();

        patient.setId(patientDTO.getId());
        patient.setFirstName(patientDTO.getFirstName());
        patient.setPatronymic(patientDTO.getPatronymic());
        patient.setLastName(patientDTO.getLastName());
        patient.setDisease(patientDTO.getDisease());
        patient.setAge(patientDTO.getAge());

        patientRepository.save(patient);
    }

    @Transactional
    public void removePatient(Integer id) {
        patientRepository.deleteById(id);
    }

    @Transactional
    public void handleAdditionReception(Integer doctorId, Integer patientId) throws Exception {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new Exception("Doctor not found"));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new Exception("Patient not found"));

        doctor.getPatients().add(patient);

        patient.getDoctors().add(doctor);

        doctorRepository.save(doctor);
        patientRepository.save(patient);
    }

    @Transactional
    public void handleCancellationOfReception(Integer doctorId, Integer patientId) throws Exception {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new Exception("Doctor not found"));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new Exception("Patient not found"));

        doctor.setPatients(doctor.getPatients().stream().filter((item) -> !item.getId().equals(patientId))
                .collect(Collectors.toList()));

        patient.setDoctors(patient.getDoctors().stream().filter((item) -> !item.getId().equals(doctorId))
                .collect(Collectors.toList()));

        doctorRepository.save(doctor);
        patientRepository.save(patient);
    }
}
