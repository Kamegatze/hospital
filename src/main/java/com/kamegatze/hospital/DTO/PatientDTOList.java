package com.kamegatze.hospital.DTO;

import com.kamegatze.hospital.models.Patient;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import com.kamegatze.hospital.models.Doctor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTOList {
    
    private int id;
    
    private String firstName;
    
    private String lastName;
    
    private String patronymic;
    
    private String disease;
    
    private int age;
    
    private List<DoctorDTOList> doctorDTOS;

    public List<PatientDTOList> getPatientDTOList(List<Patient> patients) {
        List<PatientDTOList> patientDTOLists = new ArrayList<>();
        for (Patient patient: patients) {
            PatientDTOList patientDTOList = new PatientDTOList(patient.getId(), patient.getFirstName(),
                    patient.getLastName(), patient.getPatronymic(),
                    patient.getDisease(), patient.getAge(), null);

            patientDTOList.setDoctorsDTOS(patient.getDoctors());

            patientDTOLists.add(patientDTOList);
        }
        return patientDTOLists;
    }


    public void setDoctorsDTOS(List<Doctor> doctors) {
        List<DoctorDTOList> doctorDTOList = new ArrayList<>();
        for (Doctor doctor: doctors) {
            DoctorDTOList doctorDTO = new DoctorDTOList(doctor.getId(), doctor.getFirstName(),
                        doctor.getLastName(), doctor.getPatronymic(),
                        doctor.getPost(), doctor.getJobTimeBegin(),
                    doctor.getJobTimeEnd(), null);
            doctorDTOList.add(doctorDTO);
        }
        this.doctorDTOS = doctorDTOList;
    }
}
