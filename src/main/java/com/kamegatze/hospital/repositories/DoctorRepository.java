package com.kamegatze.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kamegatze.hospital.models.Doctor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

}
