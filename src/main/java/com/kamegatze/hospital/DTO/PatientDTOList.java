package com.kamegatze.hospital.DTO;

import com.kamegatze.hospital.models.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import com.kamegatze.hospital.models.Doctor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTOList {
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
    private int age;
    @Setter
    private List<DoctorDTOList> doctorDTOLists;

    public List<PatientDTOList> getPatientDTOToList(List<Patient> patients) {
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
        this.doctorDTOLists = doctorDTOList;
    }
}
