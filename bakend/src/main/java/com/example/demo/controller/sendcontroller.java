package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EmailSenderService;
import com.example.demo.entities.email;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/mail")
public class sendcontroller {
	@Autowired
	private EmailSenderService senderService;
	 @PostMapping("/send")
	public void sendMail(@RequestBody email email) {
			senderService.sendEmail(email.getAdresse(), email.getSubject(), email.getBody());
		}

}
