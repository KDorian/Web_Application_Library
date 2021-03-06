package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Rent;
import com.example.model.User;



@Repository
public interface RentDao extends JpaRepository<Rent, Long> {
	//zwraca liste wypozyczen danego uzytkownika (zadanego jako argument)
	//posortowana malejaco pod wzgledem daty wypozyczen
	
	//createdDate
	List<Rent> findByUserOrderByCreatedDateDesc(User user);
}
