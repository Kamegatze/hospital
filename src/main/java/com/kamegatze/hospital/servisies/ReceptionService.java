package com.kamegatze.hospital.servisies;

import com.kamegatze.hospital.custom_exceptions.UserNotFoundException;
import com.kamegatze.hospital.models.Doctor;
import com.kamegatze.hospital.models.Essence;
import com.kamegatze.hospital.models.Patient;
import com.kamegatze.hospital.repositories.DoctorRepository;
import com.kamegatze.hospital.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReceptionService {

    private final DoctorRepository doctorRepository;

    private final PatientRepository patientRepository;

    private List<Essence> getDoctorAndPatient(Integer doctorId, Integer patientId) throws UserNotFoundException {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new UserNotFoundException("Doctor not found"));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new UserNotFoundException("Patient not found"));

        return List.of(doctor, patient);
    }

    @Transactional
    public void handleAdditionReception(Integer doctorId, Integer patientId) throws UserNotFoundException {
        List<Essence> entities = getDoctorAndPatient(doctorId, patientId);

        Doctor doctor = (Doctor) entities.get(0);

        Patient patient = (Patient) entities.get(1);

        doctor.getPatients().add(patient);

        patient.getDoctors().add(doctor);

        doctorRepository.save(doctor);
        patientRepository.save(patient);
    }

    @Transactional
    public void handleCancellationOfReception(Integer doctorId, Integer patientId) throws UserNotFoundException {
        List<Essence> entities = getDoctorAndPatient(doctorId, patientId);

        Doctor doctor = (Doctor) entities.get(0);

        Patient patient = (Patient) entities.get(1);

        doctor.setPatients(doctor.getPatients().stream().filter((item) -> !item.getId().equals(patientId))
                .collect(Collectors.toList()));

        patient.setDoctors(patient.getDoctors().stream().filter((item) -> !item.getId().equals(doctorId))
                .collect(Collectors.toList()));

        doctorRepository.save(doctor);
        patientRepository.save(patient);
    }
}
