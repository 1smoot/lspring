package com.kaypro.lspring2;

import java.util.Locale;

import okjsp.UserDAO;
import okjsp.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

	@RequestMapping(value = "/register.gs", method = RequestMethod.GET)
	public String register(Locale locale, Model model) {
		return "register";
	}

	@RequestMapping(value = "/register.gs", method = RequestMethod.POST)
	public String registerFinished(Locale locale,
			@ModelAttribute("user") User user, Model model) {
		// check passwd and confirmPassword
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			model.addAttribute("msg", "password not matched!");
			model.addAttribute("user", user);
			return "register";
		}
		// check id duplication
		// save()
		UserDAO.save(user);
		// forward finish page
		return "redirect:registerFinished.gs";
	}
	
	@RequestMapping(value = "/registerFinished.gs")
	public String registerFinished(Model model) {
		return "registerFinished";
	}
}
