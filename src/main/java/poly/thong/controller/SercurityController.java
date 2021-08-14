package poly.thong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import poly.thong.entity.Account;

@Controller
public class SercurityController {
	@RequestMapping("/sercurity/login/form")
	public  String loginForm(Model model) {
		model.addAttribute("message","Please login");
		return "sercurity/login";
	}
	
	@RequestMapping("/sercurity/login/success")
	public  String loginSuccess(Model model) {
		model.addAttribute("message","login successfully");
		return "sercurity/login";
	}
	
	
		
	
	@RequestMapping("/sercurity/login/error")
	public  String loginError(Model model) {
		model.addAttribute("message","Information invalid");
		return "sercurity/login";
	}
	
	@RequestMapping("/sercurity/unauthoried")
	public  String loginUnauthoried(Model model) {
		model.addAttribute("message","You dont have permission to access");
		return "sercurity/login";
	}
	
	@RequestMapping("/sercurity/logoff/success")
	public  String logoffSuccess(Model model) {
		model.addAttribute("message","logged out");
		return "sercurity/login";
	}
	
}
