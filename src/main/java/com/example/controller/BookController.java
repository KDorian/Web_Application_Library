package com.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Book;
import com.example.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	//1a - dodanie do modelu "pustego" obiektu ksiazki (ktorej dane bêd¹ wype³niane w formularzu do dodawania/edycji ksi¹zki)
	//1b - przekierowanie na strone zwiazana z dodawaniem/edycja ksiazki
	
	@RequestMapping(value="/book/create", method = RequestMethod.GET)
	public String getCreateBookForm(Model model)
	{
		//przygotowanie "pustego obiektu modelu - ksiazki" do edycji
		model.addAttribute("book", new Book());
		
		return "book-create";  //przekierowanie na strone "book-create.jsp"
	}
	
	//metoda ktora "przygotowuje" formularz do edycji ksiazki
	@RequestMapping(value="/book/edit/{id}", method = RequestMethod.GET)
	public String getEditBookForm(Model model, @PathVariable Long id)
	{
		Book book = bookService.findOne(id);
		model.addAttribute("book", book);
		
		return "book-create";  //przekierowanie na strone "book-create.jsp"
	}
	
	
	//getBooksPage - przekierowuje na strone z lista wszystkich ksiazek (books.jsp)
	//(ustawic odpowiednio model)
	

	
}