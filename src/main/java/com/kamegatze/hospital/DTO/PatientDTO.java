package com.kamegatze.hospital.DTO;

import com.kamegatze.hospital.models.Patient;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import com.kamegatze.hospital.models.Doctor;
@Getter
public class PatientDTO {
    @Setter
    private int id;
    @Setter
    private String name;
    @Setter
    private String disease;
    @Setter
    private long age;
    @Setter
    private List<DoctorDTO> doctorDTOS;

    public List<PatientDTO> getPatientDTOToList(List<Patient> patients) {
        List<PatientDTO> patientDTOS = new ArrayList<>();
        for (Patient patient: patients) {
            PatientDTO patientDTO = new PatientDTO(patient.getId(), patient.getName(),
                    patient.getDisease(), patient.getAge());
            patientDTOS.add(patientDTO);
        }
        return patientDTOS;
    }


    public void setDoctorsDTOS(List<Doctor> doctors) {
        List<DoctorDTO> doctorDTOList = new ArrayList<>();
        for (Doctor doctor: doctors) {
            DoctorDTO doctorDTO = new DoctorDTO(doctor.getId(), doctor.getName(),
                        doctor.getPost(), doctor.getJobTimeBegin(), doctor.getJobTimeEnd());
            doctorDTOList.add(doctorDTO);
        }
        this.doctorDTOS = doctorDTOList;
    }
    public PatientDTO() {
    }

    public PatientDTO(int id, String name, String disease, long age) {
        this.id = id;
        this.name = name;
        this.disease = disease;
        this.age = age;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", disease='" + disease + '\'' +
                ", age=" + age +
                '}';
    }
}
