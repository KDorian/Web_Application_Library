package com.example.service;


import java.util.List;

import com.example.model.Book;
import com.example.model.Rent;
import com.example.model.User;

public interface RentService {
	void createRent(User user, Book book);
	List<Rent> findByUserOrderByCreatedDateDesc(User user);
	List<Rent> findAll();
}
