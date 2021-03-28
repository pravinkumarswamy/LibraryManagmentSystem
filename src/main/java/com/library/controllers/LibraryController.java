package com.library.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.library.dao.LibraryDTO;
//import com.casestudy.models.Employee;
import com.library.entities.Admin;
import com.library.entities.Library;
import com.library.services.AdminServices;
import com.library.services.LibraryServices;

@Controller
//@Validated
public class LibraryController {
	@Autowired
	private AdminServices adminservice;
	@Autowired
	private LibraryServices libraryservice;
	
	@GetMapping("/login")
	public String index(@ModelAttribute("admin")Admin admin,Model model) {
//		model.addAttribute("admin", new Admin());
		model.addAttribute("admin", new Admin());
		return "login";
	}

	@PostMapping("/homepage")
	public String login(@ModelAttribute("admin")Admin admin,Model model) {
//		model.addAttribute("admin", new Admin());
//		System.out.println(admin);
		Admin adm=adminservice.ValidateAdmin(admin.getUsername(), admin.getPassword());
//		session.setAttribute("message", admin.getUsername());?
//		LibraryDTO dto=new LibraryDTO();
//		dto.setLibrary(libraryservice.getAllLibrary());
//		model.addAttribute("library", dto);
		List<Library> library=libraryservice.getAllLibrary();
//		System.out.println(library);
		model.addAttribute("library", library);
//		model.addAttribute("libinsert", new Library());
//		System.out.println(libinsert);
		
		return "LibraryHomePage";
		
	}
	

	//Controller for deleting
	@GetMapping("/delete-record")
	public String delete(@RequestParam("student_id")int student_id,Model model) {
//		System.out.println(name_of_the_stud);
		libraryservice.deleteRecord(student_id);
		List<Library> library=libraryservice.getAllLibrary();
		model.addAttribute("library", library);
		
		return "LibraryHomePage";
	}
	//Controller for inserting
	@PostMapping(value="/insert_operation")
	public ModelAndView insert(Library library1) {
		ModelAndView mav=new ModelAndView();
		System.out.println(library1);
		libraryservice.insertRecord(library1);
		List<Library> library=libraryservice.getAllLibrary();
		mav.addObject("library", library);
		mav.setViewName("LibraryHomePage");
		return mav;
	}
	//Controller for updating
	@GetMapping(value="/update-record")
	public ModelAndView update(Library library) {
		ModelAndView mav=new ModelAndView();
		System.out.println(library);
		mav.addObject("library", library);
		mav.setViewName("UpdateRecord");
		return mav;
	}
	
	@PostMapping(value="/after-update")
	public ModelAndView afterUpdate(Library library1) {
		ModelAndView mav=new ModelAndView();
		System.out.println(library1);
//		libraryservice.insertRecord(library1);
		libraryservice.updateRecord(library1);
		List<Library> library=libraryservice.getAllLibrary();
		mav.addObject("library", library);
		mav.setViewName("LibraryHomePage");
		return mav;
		
	}
}
