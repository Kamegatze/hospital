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

import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReceptionService {

    private final DoctorRepository doctorRepository;

    private final PatientRepository patientRepository;

    private static Logger log = Logger.getLogger(PatientService.class.getName());

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

        log.info("begin add relationship");

        doctor.getPatients().add(patient);

        patient.getDoctors().add(doctor);

        log.info("end add relationship");

        log.warning("begin save doctor and patient");

        doctorRepository.save(doctor);
        patientRepository.save(patient);

        log.info("begin save doctor and patient");
    }

    @Transactional
    public void handleCancellationOfReception(Integer doctorId, Integer patientId) throws UserNotFoundException {
        List<Essence> entities = getDoctorAndPatient(doctorId, patientId);

        Doctor doctor = (Doctor) entities.get(0);

        Patient patient = (Patient) entities.get(1);

        log.info("begin delete relationship");

        doctor.setPatients(doctor.getPatients().stream().filter((item) -> !item.getId().equals(patientId))
                .collect(Collectors.toList()));

        patient.setDoctors(patient.getDoctors().stream().filter((item) -> !item.getId().equals(doctorId))
                .collect(Collectors.toList()));

        log.info("end delete relationship");

        log.warning("begin save doctor and patient");

        doctorRepository.save(doctor);
        patientRepository.save(patient);

        log.info("end save doctor and patient");
    }
}
