package com.kamegatze.hospital.servisies;

import com.kamegatze.hospital.DTO.DoctorDTOList;
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

    public List<DoctorDTOList> getAll() {
        DoctorDTOList doctorDTOList = new DoctorDTOList();
        return doctorDTOList.getDoctorDTOList(doctorRepository.findAll());
    }

    public DoctorDTOList getDoctor(int id) {
        Doctor doctor = this.doctorRepository.getReferenceById(id);
        //форматирование данных под нужный формат
        DoctorDTOList doctorDTOList = new DoctorDTOList();
        doctorDTOList = doctorDTOList.getDoctorDTOList(List.of(doctor)).get(0);
        doctorDTOList.setPatientDTOLists(doctor.getPatients());

        return doctorDTOList;
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
    public void deleteDoctor(Integer id) {
        this.doctorRepository.deleteById(id);
    }

    public DoctorDTOList findDoctorByPost(String post) throws Exception {
        Doctor doctor = doctorRepository.findDoctorByPost(post).orElseThrow(() -> new Exception("These doctors does not exist"));

        DoctorDTOList doctorDTOList = new DoctorDTOList();
        doctorDTOList.setId(doctor.getId());
        doctorDTOList.setFirstName(doctor.getFirstName());
        doctorDTOList.setLastName(doctor.getLastName());
        doctorDTOList.setPatronymic(doctor.getPatronymic());
        doctorDTOList.setPost(doctor.getPost());
        doctorDTOList.setJobTimeBegin(doctor.getJobTimeBegin());
        doctorDTOList.setJobTimeEnd(doctor.getJobTimeEnd());
        doctorDTOList.setPatientDTOLists(doctor.getPatients());

        return doctorDTOList;
    }
}