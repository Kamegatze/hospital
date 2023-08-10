package com.kamegatze.hospital.DTO;

import com.kamegatze.hospital.models.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import com.kamegatze.hospital.models.Doctor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    @Setter
    private int id;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    private String patronymic;
    @Setter
    private String disease;
    @Setter
    private long age;
    @Setter
    private List<DoctorDTO> doctorDTOS;

    public List<PatientDTO> getPatientDTOToList(List<Patient> patients) {
        List<PatientDTO> patientDTOS = new ArrayList<>();
        for (Patient patient: patients) {
            PatientDTO patientDTO = new PatientDTO(patient.getId(), patient.getFirstName(),
                    patient.getLastName(), patient.getPatronymic(),
                    patient.getDisease(), patient.getAge(), null);

            patientDTO.setDoctorsDTOS(patient.getDoctors());

            patientDTOS.add(patientDTO);
        }
        return patientDTOS;
    }


    public void setDoctorsDTOS(List<Doctor> doctors) {
        List<DoctorDTO> doctorDTOList = new ArrayList<>();
        for (Doctor doctor: doctors) {
            DoctorDTO doctorDTO = new DoctorDTO(doctor.getId(), doctor.getFirstName(),
                        doctor.getLastName(), doctor.getPatronymic(),
                        doctor.getPost(), doctor.getJobTimeBegin(),
                    doctor.getJobTimeEnd(), null);
            doctorDTOList.add(doctorDTO);
        }
        this.doctorDTOS = doctorDTOList;
    }
}
