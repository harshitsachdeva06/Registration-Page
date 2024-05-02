package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.entities.User;
import com.project.repository.UserRepository;
import com.project.helper.Message;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	public UserRepository userRepository;
	
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title","Home-Smart Contact Manager");
		return "home";
	}
	
	
	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title","About-Smart Contact Manager");
		return "about";
	}
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title","Register-Smart Contact Manager");
		model.addAttribute("user",new User());
		return "signup";
	}
	
	//Handler For Registering user
	
	@RequestMapping(value="/do_register",method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user,@RequestParam(value="agreement",defaultValue = "false") boolean agreement, Model model,HttpSession session) {
		
		try {
			if(!agreement) {
				System.out.println("You have not agreed to the terms and condition");
				throw new Exception("You have not agreed to the terms and condition");
			}
			
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			
			System.out.println("Agreement "+agreement);
			System.out.println("USER "+user);
			
			User result = this.userRepository.save(user);
			
			model.addAttribute("user",new User());
			session.setAttribute("message", new Message("Successfully Registered !!","success"));
			return "signup";
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message", new Message("something went wrong"+e.getMessage(),"alert-danger"));
			return "signup";
		}
		
	}
	
	
}
	
// Testing DB connection and if data is getting entered in our USER table
	
	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@GetMapping("/test")
//	@ResponseBody
//	public String test() {
//		User user = new User();
//		user.setName("Harshit Sachdeva");
//		user.setEmail("harshit@dev.io");
//		Contact contact= new Contact();
//		user.getContacts().add(contact);
//		userRepository.save(user);
//		
//		return "working";
//	}

