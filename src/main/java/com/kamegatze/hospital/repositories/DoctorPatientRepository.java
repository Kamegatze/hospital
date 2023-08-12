package com.kamegatze.hospital.repositories;

import com.kamegatze.hospital.models.DoctorPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorPatientRepository extends JpaRepository<DoctorPatient, Integer> {

    @Modifying
    @Query(value = "delete from doctor_patient where doctor_id = ?1", nativeQuery = true)
    void deleteByDoctorId(Integer doctorId);

    @Modifying
    @Query(value = "delete from doctor_patient where patient_id = ?1", nativeQuery = true)
    void deleteByPatientId(Integer patientId);
}
