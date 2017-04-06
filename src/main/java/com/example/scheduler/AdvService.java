package com.example.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.service.EmailService;
import com.example.service.UserService;

/*Co jakis czas wysylac wiadomosc z reklama do wszystkich uzytkownikow (ktorzy maja konto)
 * 
 */
@Service
public class AdvService {
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	private final static String FROM_EMAIL = "akbroh88@gmail.com";
	
	@Scheduled(fixedDelay = 10000) 
	public void sendEmails(){
		List<User> listUsers = userService.findAll();
		
		if(listUsers == null) {
			return;
		}
		
		for(User user : listUsers) {
			emailService.sendEmail(FROM_EMAIL, user.getEmail(), "Biblioteczny spam", "Jestesmy najlepsi!!!");
		}
	}
	
}
