package com.easyLink.controller;

import java.sql.SQLException;
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

import com.easyLink.database.EasyLinkDatabaseManager;
import com.easyLink.database.InsertCheck;
import com.easyLink.links.URL;

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
	private EasyLinkDatabaseManager dbManager;

//	@Autowired
//	private RegistrationService regService;

	@RequestMapping("/links")
	public List<URL> getAllLinks() throws ClassNotFoundException, SQLException {

		dbManager = new EasyLinkDatabaseManager();
		return dbManager.getAllLinks();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/links/{id}")
	public String getLink(@PathVariable String id, HttpServletResponse httpServletResponse) throws ClassNotFoundException, SQLException {

		if (dbManager.getLink(id) == null) {
			StringBuilder sb = new StringBuilder();

			sb.append("Wrong entry! Try again<br/>\n");

			return sb.toString();
		} else {

			String link = dbManager.getLink(id);

			if (link.length() > 6
					&& (link.substring(0, 6).equals("http://") || link.substring(0, 7).equals("https://"))) {
				httpServletResponse.setStatus(302);
				httpServletResponse.setHeader("Location", link);

				return "redirect:" + link;

			} else {
				httpServletResponse.setStatus(302);
				httpServletResponse.setHeader("Location", "https://" + link);

				return "redirect:" + link;
			}

		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/links")
	public InsertCheck addLink(@RequestBody URL link) throws ClassNotFoundException, SQLException {

		StringBuilder sb = new StringBuilder();
		InsertCheck checker = new InsertCheck();

		if(dbManager.insertLink(link.getId(), link.getURL())) {
			sb.append("Link inserted in database!");
			checker.setResult(true);
			
		}else {
			sb.append("Double entry or error");
			checker.setResult(false);
		} 

		return checker;

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/links/{id}")
	public String deleteLink(@PathVariable String id) {
		StringBuilder sb = new StringBuilder();
		boolean result = false;
		result = dbManager.deleteLink(id);

		if (result) {
			sb.append("true<br/>\n");
			sb.append("<a href='/links'>Back</a>\n");

			return sb.toString();

		} else {
			sb.append("false<br/>\n");
			sb.append("<a href='/links'>Back</a>\n");

			return sb.toString();
		}

	}
}

//	//registracijas metodes

//	@RequestMapping(value = "/user/registration", method = RequestMethod.GET)
//	public String showRegistrationForm(WebRequest request, Model model) {
//	    Registration user = new Registration();
//	    model.addAttribute(user.getUsername(), user);
//	    return "registration";
//	}

/*
 * //method for saving registered user // @RequestMapping(method =
 * RequestMethod.POST, value = "/user") // public String saveUser(@RequestParam
 * String username, @RequestParam String password, @RequestParam String email )
 * { // Registration user = new Registration(username, password, email); //
 * regService.saveRegistration(user); // return "User saved!"; // } //
 * // @RequestMapping(method = RequestMethod.GET, value = "/users")
 * //// @ResponseBody // public String getUsers(@RequestBody URL link) { //
 * StringBuilder sb = new StringBuilder(); // dbManager = new
 * EasyLink_DatabaseManager(); // // return regService. //// } // //}
 * 
 * 
 * //method to addlink to registered User
 * 
 * @RequestMapping(method = RequestMethod.POST, value = "/user/links") public
 * String addLinktoUser(@RequestBody URL link) { StringBuilder sb = new
 * StringBuilder(); Registration reg = new Registration();
 * 
 * reg.addToList(new URL(link.getId(), link.getURL())); //
 * dbManager.insertLink(link.getId(), link.getURL()); // linkManager.addLink(id,
 * URL); // response.setStatus(HttpServletResponse.SC_OK);
 * sb.append("true<br/>\n"); sb.append("<a href='/links'>Back</a>\n");
 * 
 * return sb.toString(); // }
 * 
 * }
 * 
 * @RequestMapping(method = RequestMethod.GET, value = "/links/{id}") public
 * String getUserLink(@PathVariable String id, HttpServletResponse
 * httpServletResponse) {
 * 
 * if(dbManager.getLink(id)==null) { StringBuilder sb = new StringBuilder();
 * 
 * sb.append("Wrong entry! Try again<br/>\n");
 * sb.append("<a href='/links'>Back</a>\n");
 * 
 * return sb.toString(); }else {
 * 
 * httpServletResponse.setStatus(302); httpServletResponse.setHeader("Location",
 * "http://"+dbManager.getLink(id));
 * 
 * return "redirect:" + dbManager.getLink(id); }
 * 
 * } }
 */