package com.kamegatze.hospital.repositories;

import com.kamegatze.hospital.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Query(value = "select * from patient order by id desc limit 1;", nativeQuery = true)
    Patient findLastRecord();
}
