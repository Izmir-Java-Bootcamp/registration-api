package com.kodluyoruz.registrationapi.repository;


import com.kodluyoruz.registrationapi.model.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
}
