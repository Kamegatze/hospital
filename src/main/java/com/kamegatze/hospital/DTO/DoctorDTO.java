package com.kamegatze.hospital.DTO;

import com.kamegatze.hospital.models.Patient;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import com.kamegatze.hospital.models.Doctor;

@Getter
public class DoctorDTO {
    @Setter
    private int id;
    @Setter
    private String name;
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
            DoctorDTO doctorDTO = new DoctorDTO(doctor.getId(), doctor.getName(),
                    doctor.getPost(), doctor.getJobTimeBegin(),
                    doctor.getJobTimeEnd());
            doctorDTOS.add(doctorDTO);
        }
        return doctorDTOS;
    }

    public void setPatientDTOS(List<Patient> patients) {
        List<PatientDTO> patientDTOList = new ArrayList<>();
        for (Patient patient: patients) {
            PatientDTO patientDTO = new PatientDTO(patient.getId(), patient.getName(),
                        patient.getDisease(), patient.getAge());
            patientDTOList.add(patientDTO);
        }
        this.patientDTOS = patientDTOList;
    }

    public DoctorDTO(int id, String name, String post, Time jobTimeBegin, Time jobTimeEnd) {
        this.id = id;
        this.name = name;
        this.post = post;
        this.jobTimeBegin = jobTimeBegin;
        this.jobTimeEnd = jobTimeEnd;
    }

    public DoctorDTO() {
    }
}
