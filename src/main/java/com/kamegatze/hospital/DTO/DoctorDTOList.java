package com.kamegatze.hospital.DTO;

import com.kamegatze.hospital.models.Patient;
import lombok.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import com.kamegatze.hospital.models.Doctor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTOList {
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

    private List<PatientDTOList> patientDTOLists;
    public List<DoctorDTOList> getDoctorDTOList(List<Doctor> doctors) {
        List<DoctorDTOList> doctorDTOLists = new ArrayList<>();
        for(Doctor doctor: doctors) {
            DoctorDTOList doctorDTOList = new DoctorDTOList(doctor.getId(), doctor.getFirstName(),
                    doctor.getLastName(), doctor.getPatronymic(),
                    doctor.getPost(), doctor.getJobTimeBegin(),
                    doctor.getJobTimeEnd(), null);

            doctorDTOList.setPatientDTOLists(doctor.getPatients());

            doctorDTOLists.add(doctorDTOList);
        }
        return doctorDTOLists;
    }

    public void setPatientDTOLists(List<Patient> patients) {
        List<PatientDTOList> patientDTOList = new ArrayList<>();
        for (Patient patient: patients) {
            PatientDTOList patientDTO = new PatientDTOList(patient.getId(), patient.getFirstName(),
                        patient.getLastName(), patient.getPatronymic(),
                        patient.getDisease(), patient.getAge(), null);
            patientDTOList.add(patientDTO);
        }
        this.patientDTOLists = patientDTOList;
    }

}
