package com.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.entity.ContactForm;

@Controller
public class MainController {
	
    @Autowired
    private JavaMailSender javaMailSender;
 
	
	@GetMapping("/home")
	public String home(Model model) {
		
		model.addAttribute("title","Home");
		
		return "home";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title","about");
		return "about";
	}
	
	@GetMapping("/service")
	public String service(Model model) {
		model.addAttribute("title","service");
		return "service";
	}
	
	@GetMapping("/work")
	public String work(Model model) {
		model.addAttribute("title","work");
		return "work";
	}
	
	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("title","contact");
	    model.addAttribute("successMessage", model.getAttribute("successMessage")); // Add this line

		return "contact";
	}
	
	@PostMapping("/submit")
	public String submitContact(ContactForm form,Model model,RedirectAttributes redirectAttributes)
	{
		sendEmail(form);
	    //model.addAttribute("successMessage", "Email sent successfully!"); // Add this line
        redirectAttributes.addFlashAttribute("successMessage", "Email sent successfully!");

		return "redirect:/contact";
	}
	
	private void sendEmail(ContactForm form) {
		
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("rajabomma22@gmail.com"); // Replace with your email address
        message.setSubject("New contact Submission");
        message.setText(
                "Name: " + form.getName() + "\n" +
                "Email: " + form.getEmail() + "\n" +
                "Message: " + form.getMessage()
        );

        // Send the email
        javaMailSender.send(message);

	}

}
