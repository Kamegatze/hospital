package com.kamegatze.hospital.DTO;

import com.kamegatze.hospital.models.Patient;
import lombok.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import com.kamegatze.hospital.models.Doctor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    @Setter
    private int id;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    private String patronymic;
    @Setter
    private String post;
    @Setter
    private Time jobTimeBegin;
    @Setter
    private Time jobTimeEnd;

    private List<PatientDTO> patientDTOS;
    public List<DoctorDTO> getDoctorDTOList(List<Doctor> doctors) {
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for(Doctor doctor: doctors) {
            DoctorDTO doctorDTO = new DoctorDTO(doctor.getId(), doctor.getFirstName(),
                    doctor.getLastName(), doctor.getPatronymic(),
                    doctor.getPost(), doctor.getJobTimeBegin(),
                    doctor.getJobTimeEnd(), null);

            doctorDTO.setPatientDTOS(doctor.getPatients());

            doctorDTOS.add(doctorDTO);
        }
        return doctorDTOS;
    }

    public void setPatientDTOS(List<Patient> patients) {
        List<PatientDTO> patientDTOList = new ArrayList<>();
        for (Patient patient: patients) {
            PatientDTO patientDTO = new PatientDTO(patient.getId(), patient.getFirstName(),
                        patient.getLastName(), patient.getPatronymic(),
                        patient.getDisease(), patient.getAge(), null);
            patientDTOList.add(patientDTO);
        }
        this.patientDTOS = patientDTOList;
    }

}
