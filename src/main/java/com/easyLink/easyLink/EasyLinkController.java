package com.easyLink.easyLink;

import java.util.List;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class EasyLinkController {

	@Autowired
	private EasyLink_DatabaseManager dbManager;
	
//	@Autowired
//	private RegistrationService regService;
	
//	@RequestMapping(value = "/", produces = "text/html;charset=UTF-8")
//	public String sayHi(@RequestParam(value = "id", required = false) String id,
//			@RequestParam(value = "URL", required = false) String URL, HttpServletResponse response) {
//		
//		StringBuilder sb = new StringBuilder();
//		
//		if (id == null && URL == null) {
//			
//			sb.append("Hi, welcome in EasyLink\n");
//			sb.append("<form action=''>\n");
//			sb.append("Your link name: <input type='text' name='id' value=''><br/>\n");
//			sb.append("Your URL:<input type='text' name='URL' value=''><br/>\n");
//			sb.append("<input type='submit' value='Insert My URL'></form><br/>\n");
//			
//			sb.append("<a href='/links'>See all links</a>\n");
//			// Following is also redundant because status is OK by default:
//			response.setStatus(HttpServletResponse.SC_OK);
//			return sb.toString();
//			
//		}else if (id.trim().isEmpty() || URL.trim().isEmpty()) {
//
//			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//
//			sb.append("Wrong entry! Try again<br/>\n");
//			sb.append("<a href='/'>Back</a>\n");
//
//			return sb.toString();
//
//		}else {
//
////			linkManager = new LinkManager();
////
////			linkManager.addLink(id, URL);
//			response.setStatus(HttpServletResponse.SC_OK);
//			sb.append("Done! You can find your link at: localhost:8080/"+id+" <br/>\n");
//			sb.append("<a href='/links'>Back</a>\n");
//
//			return sb.toString();
//		}
//		
//	}

	@RequestMapping("/links")
	public List<URL> getAllLinks() throws ClassNotFoundException {
		StringBuilder sb = new StringBuilder();
		dbManager = new EasyLink_DatabaseManager();
		
		return dbManager.getAllLinks();
	}

//	@RequestMapping("/links/{id}")
//	public Link getLinkID(@PathVariable String id) {
//		return linkManager.getLink(id);
//	}

	@RequestMapping(method = RequestMethod.GET, value = "/links/{id}")
	public String getLink(@PathVariable String id, HttpServletResponse httpServletResponse) {
		
		if(dbManager.getLink(id)==null) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("Wrong entry! Try again<br/>\n");
			sb.append("<a href='/links'>Back</a>\n");
			
			return sb.toString();
		}else {


			 httpServletResponse.setStatus(302); 
			httpServletResponse.setHeader("Location", "http://"+dbManager.getLink(id));
			
			return "redirect:" + dbManager.getLink(id);
		}
		
	}

	/*
	 * redirekts 2 varianti First:
	 * 
	 * @RequestMapping(value = "/redirect", method = RequestMethod.GET) public void
	 * method(HttpServletResponse httpServletResponse) {
	 * httpServletResponse.setHeader("Location", projectUrl);
	 * httpServletResponse.setStatus(302); }
	 * 
	 * Second:
	 * 
	 * @RequestMapping(value = "/redirect", method = RequestMethod.GET) public
	 * ModelAndView method() { return new ModelAndView("redirect:" + projectUrl);
	 * 
	 * }
	 */

	// @RequestMapping(method=RequestMethod.POST, value="/links")
	// public void addLink(@RequestBody Link link) {
	// linkManager.addLink(link);

	@RequestMapping(method = RequestMethod.POST, value = "/links")
//	@ResponseBody
	public String addLink(@RequestBody URL link) {
//	(@RequestParam(value = "id", required = false) String id,
//			@RequestParam(value = "URL", required = false) String URL, HttpServletResponse response) {
//		
//		if (id == null && URL == null) {
//
//			StringBuilder sb = new StringBuilder();
//			sb.append("<form action=''>\n");
//			sb.append("Name: <input type='text' name='name' value=''><br/>\n");
//			sb.append("Surname:<input type='text' name='surname' value=''><br/>\n");
//			sb.append("<input type='submit' value='Insert'></form><br/>\n");
//			sb.append("<a href='/'>Back</a>\n");
//
//			response.setStatus(HttpServletResponse.SC_OK);
//
//			return sb.toString();
//
//		} else if (id.trim().isEmpty() || URL.trim().isEmpty()) {
//
//			StringBuilder sb = new StringBuilder();
//			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//
//			sb.append("false<br/>\n");
//			sb.append("<a href='/'>Back</a>\n");
//
//			return sb.toString();
//
//		} else {

//			linkManager = new URLService();
			StringBuilder sb = new StringBuilder();
			dbManager.insertLink(link.getId(), link.getURL());
//			linkManager.addLink(id, URL);
//			response.setStatus(HttpServletResponse.SC_OK);
			sb.append("true<br/>\n");
			sb.append("<a href='/links'>Back</a>\n");

			return sb.toString();
//		}

	}

//	@RequestMapping(method = RequestMethod.PUT, value = "/links/{id}")
//	public void updateLink(@RequestBody URL link, @PathVariable String id) {
//		linkManager.updateLink(id, link);
//	}
//
	@RequestMapping(method = RequestMethod.DELETE, value = "/links/{id}")
	public String deleteLink(@PathVariable String id) {
		StringBuilder sb = new StringBuilder();
		boolean result = false;
		result = dbManager.deleteLink(id);
		
		if(result) {
			sb.append("true<br/>\n");
			sb.append("<a href='/links'>Back</a>\n");
			
			return sb.toString();
			
		}else {
			sb.append("false<br/>\n");
			sb.append("<a href='/links'>Back</a>\n");
			
			return sb.toString();
		}
		 
	}
	
//	//registracijas metodes
//	@RequestMapping(value = "/user/registration", method = RequestMethod.GET)
//	public String showRegistrationForm(WebRequest request, Model model) {
//	    Registration user = new Registration();
//	    model.addAttribute(user.getUsername(), user);
//	    return "registration";
//	}

}
/*		//method for saving registered user
//	@RequestMapping(method = RequestMethod.POST, value = "/user")
//	public String saveUser(@RequestParam String username, @RequestParam String password, @RequestParam String email ) {
//		Registration user = new Registration(username, password, email);
//		regService.saveRegistration(user);
//		return "User saved!";
//	}
//	
//	@RequestMapping(method = RequestMethod.GET, value = "/users")
////	@ResponseBody
//	public String getUsers(@RequestBody URL link) {
//		StringBuilder sb = new StringBuilder();
//		dbManager = new EasyLink_DatabaseManager();
//		
//		return regService.
////	}
//
//}
	
	
		//method to addlink to registered User
	@RequestMapping(method = RequestMethod.POST, value = "/user/links")
	public String addLinktoUser(@RequestBody URL link) {
		StringBuilder sb = new StringBuilder();
		Registration reg = new Registration();
		
		reg.addToList(new URL(link.getId(), link.getURL()));
//		dbManager.insertLink(link.getId(), link.getURL());
//		linkManager.addLink(id, URL);
//		response.setStatus(HttpServletResponse.SC_OK);
		sb.append("true<br/>\n");
		sb.append("<a href='/links'>Back</a>\n");

		return sb.toString();
//	}

}
	
	@RequestMapping(method = RequestMethod.GET, value = "/links/{id}")
	public String getUserLink(@PathVariable String id, HttpServletResponse httpServletResponse) {
		
		if(dbManager.getLink(id)==null) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("Wrong entry! Try again<br/>\n");
			sb.append("<a href='/links'>Back</a>\n");
			
			return sb.toString();
		}else {

			 httpServletResponse.setStatus(302); 
			httpServletResponse.setHeader("Location", "http://"+dbManager.getLink(id));
			
			return "redirect:" + dbManager.getLink(id);
		}
		
	}
}*/