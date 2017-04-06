package com.example.service;


import java.util.List;
import com.example.model.Book;

public interface BookService {
	
	List<Book> findAll();
	Book findOne(Long id);
	void save(Book book);
	void delete(Long id);

}
