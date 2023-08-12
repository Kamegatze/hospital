package com.kamegatze.hospital.DTO;

import com.kamegatze.hospital.models.Patient;
import lombok.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import com.kamegatze.hospital.models.Doctor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTOList {
    
    private int id;
    
    private String firstName;
    
    private String lastName;
    
    private String patronymic;
    
    private String post;
    
    private LocalTime jobTimeBegin;
    
    private LocalTime jobTimeEnd;

    private List<PatientDTOList> patientDTOS;
    public List<DoctorDTOList> getDoctorDTOList(List<Doctor> doctors) {
        List<DoctorDTOList> doctorDTOLists = new ArrayList<>();
        for(Doctor doctor: doctors) {
            DoctorDTOList doctorDTOList = new DoctorDTOList(doctor.getId(), doctor.getFirstName(),
                    doctor.getLastName(), doctor.getPatronymic(),
                    doctor.getPost(), doctor.getJobTimeBegin(),
                    doctor.getJobTimeEnd(), null);

            doctorDTOList.setPatientsDTOS(doctor.getPatients());

            doctorDTOLists.add(doctorDTOList);
        }
        return doctorDTOLists;
    }

    public void setPatientsDTOS(List<Patient> patients) {
        List<PatientDTOList> patientDTOList = new ArrayList<>();
        for (Patient patient: patients) {
            PatientDTOList patientDTO = new PatientDTOList(patient.getId(), patient.getFirstName(),
                        patient.getLastName(), patient.getPatronymic(),
                        patient.getDisease(), patient.getAge(), null);
            patientDTOList.add(patientDTO);
        }
        this.patientDTOS = patientDTOList;
    }

}
