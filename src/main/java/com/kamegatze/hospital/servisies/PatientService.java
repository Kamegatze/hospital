package com.kamegatze.hospital.servisies;


import com.kamegatze.hospital.DTO.PatientDTO;
import com.kamegatze.hospital.DTO.PatientDTOList;
import com.kamegatze.hospital.custom_exceptions.UserNotFoundException;
import com.kamegatze.hospital.models.Patient;
import com.kamegatze.hospital.repositories.DoctorRepository;
import com.kamegatze.hospital.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

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

    private Patient getPatientById(Integer id) throws UserNotFoundException {
        return this.patientRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Patient not found"));
    }

    public PatientDTOList getPatient(Integer id) throws UserNotFoundException {
        Patient patient = getPatientById(id);

        PatientDTOList patientDTO = PatientDTOList.builder()
                .id(patient.getId())
                .lastName(patient.getLastName())
                .firstName(patient.getFirstName())
                .patronymic(patient.getPatronymic())
                .disease(patient.getDisease())
                .age(patient.getAge())
                .build();

        patientDTO.setDoctorsDTOS(patient.getDoctors());

        return patientDTO;
    }

    @Transactional
    public void addAndUpdatePatient(PatientDTO patientDTO) {
        Patient patient = Patient.builder()
                .firstName(patientDTO.getFirstName())
                .lastName(patientDTO.getLastName())
                .patronymic(patientDTO.getPatronymic())
                .age(patientDTO.getAge())
                .disease(patientDTO.getDisease())
                .build();

        patient.setId(patientDTO.getId());

        patientRepository.save(patient);
    }

    @Transactional
    public void removePatient(Integer id) {
        patientRepository.deleteById(id);
    }
}
