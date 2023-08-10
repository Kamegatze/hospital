package com.kamegatze.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kamegatze.hospital.models.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    @Query(value = "select * from doctor order by id desc limit 1;", nativeQuery = true)
    Doctor findLastRecord();

    Optional<Doctor> findDoctorByPost(String post);
}
