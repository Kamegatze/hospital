package com.kamegatze.hospital.servisies;

import com.kamegatze.hospital.DTO.DoctorDTO;
import com.kamegatze.hospital.DTO.DoctorDTOList;
import com.kamegatze.hospital.custom_exceptions.UserNotFoundException;
import com.kamegatze.hospital.repositories.DoctorPatientRepository;
import com.kamegatze.hospital.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kamegatze.hospital.models.Doctor;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final DoctorPatientRepository doctorPatientRepository;

    public List<DoctorDTOList> getAll() {
        DoctorDTOList doctorDTOList = new DoctorDTOList();
        return doctorDTOList.getDoctorDTOList(doctorRepository.findAll());
    }

    private Doctor doctorById(Integer id) throws UserNotFoundException {
        return this.doctorRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Doctor not found on id: " + id));
    }

    public DoctorDTOList getDoctor(Integer id) throws UserNotFoundException {
        Doctor doctor = doctorById(id);

        DoctorDTOList doctorDTOList = DoctorDTOList.builder()
                .id(doctor.getId())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .patronymic(doctor.getPatronymic())
                .post(doctor.getPost())
                .jobTimeBegin(doctor.getJobTimeBegin())
                .jobTimeEnd(doctor.getJobTimeEnd())
                .build();

        doctorDTOList.setPatientsDTOS(doctor.getPatients());

        return doctorDTOList;
    }

    @Transactional
    public Doctor addAndUpdateDoctor(DoctorDTO doctorDTO) {

        Doctor doctor = Doctor.builder()
                .firstName(doctorDTO.getFirstName())
                .lastName(doctorDTO.getLastName())
                .patronymic(doctorDTO.getPatronymic())
                .post(doctorDTO.getPost())
                .jobTimeBegin(doctorDTO.getJobTimeBegin())
                .jobTimeEnd(doctorDTO.getJobTimeEnd())
                .build();

        doctor.setId(doctorDTO.getId());


        return this.doctorRepository.save(doctor);
    }
    @Transactional
    public void deleteDoctor(Integer id) throws UserNotFoundException {

        Doctor doctor = doctorById(id);

        /*
        * remove relationships
        * */

        doctorPatientRepository.deleteByDoctorId(doctor.getId());

        this.doctorRepository.deleteById(id);
    }

}