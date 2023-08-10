package com.kamegatze.hospital.servisies;

import com.kamegatze.hospital.DTO.DoctorDTO;
import com.kamegatze.hospital.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kamegatze.hospital.models.Doctor;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<DoctorDTO> getAll() {
        DoctorDTO doctorDTO = new DoctorDTO();
        return doctorDTO.getDoctorDTOList(doctorRepository.findAll());
    }

    public DoctorDTO getDoctor(int id) {
        Doctor doctor = this.doctorRepository.getReferenceById(id);
        //форматирование данных под нужный формат
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO = doctorDTO.getDoctorDTOList(List.of(doctor)).get(0);
        doctorDTO.setPatientDTOS(doctor.getPatients());

        return doctorDTO;
    }

    public Doctor lastDoctor () {
        return this.doctorRepository.findLastRecord();
    }
    @Transactional
    public void addDoctor(Doctor doctor) {
        this.doctorRepository.save(doctor);
    }
    @Transactional
    public void updateDoctor(Doctor doctor) {
        Doctor doctorTemp = this.doctorRepository.getReferenceById(doctor.getId());
        doctor.setPatients(doctorTemp.getPatients());
        this.doctorRepository.save(doctor);
    }
    @Transactional
    public void deleteDoctor(Doctor doctor) {
        this.doctorRepository.delete(doctor);
    }

    public DoctorDTO findDoctorByPost(String post) throws Exception {
        Doctor doctor = doctorRepository.findDoctorByPost(post).orElseThrow(() -> new Exception("These doctors does not exist"));

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctor.getId());
        doctorDTO.setName(doctor.getName());
        doctorDTO.setPost(doctor.getPost());
        doctorDTO.setJobTimeBegin(doctor.getJobTimeBegin());
        doctorDTO.setJobTimeEnd(doctor.getJobTimeEnd());
        doctorDTO.setPatientDTOS(doctor.getPatients());

        return doctorDTO;
    }
}