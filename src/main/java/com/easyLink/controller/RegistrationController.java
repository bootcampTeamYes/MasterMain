package com.easyLink.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easyLink.database.EasyLinkDatabaseManager;
import com.easyLink.database.InsertCheck;
import com.easyLink.database.RegistrationDatabaseManager;
import com.easyLink.links.URL;
import com.easyLink.registration.Registration;


@RestController
public class RegistrationController {

	@Autowired
	private RegistrationDatabaseManager dbManager;


	@RequestMapping("/register")
	public List<Registration> getAllRegistrations() throws ClassNotFoundException, SQLException {

		dbManager = new RegistrationDatabaseManager();
		return dbManager.getAllRegistrations();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/{username}")
	public Registration getRegistration(@PathVariable String username, HttpServletResponse httpServletResponse)
			throws ClassNotFoundException, SQLException {

		if (dbManager.getRegistration(username) == null) {
			StringBuilder sb = new StringBuilder();

			sb.append("Wrong entry! Try again<br/>\n");

			return null;
		} else {

			Registration link = dbManager.getRegistration(username);
			for( URL item : dbManager.getRegistrationLinks(username)) {
				link.addToList(item);
			}

			return link;

		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{username}/links")
	public Set<URL>  getRegistrationLinks(@PathVariable(value="username") String username, HttpServletResponse httpServletResponse)
			throws ClassNotFoundException, SQLException {
		
		System.out.println("Registration name: "+username);
		if (dbManager.getRegistration(username) == null) {
			StringBuilder sb = new StringBuilder();

			sb.append("Wrong entry! Try again<br/>\n");

			return null;
		} else {
			System.out.println("ieiet elsa meklet");
//			Registration link = dbManager.getRegistrationLinks(username);
//
//			StringBuilder sb = new StringBuilder();

			return dbManager.getRegistrationLinks(username);
				
//			sb.append("<form action=''>\n");
//			sb.append("Name: <input type='text' name='name' value=''><br/>\n");
//			sb.append("Surname:<input type='text' name='surname' value=''><br/>\n");
//			sb.append("<input type='submit' value='Find'><br/>\n");
//
//			return sb.toString();

		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/{username}/links")
	public InsertCheck addLink(@RequestBody URL link, @PathVariable(value = "username") String username) throws ClassNotFoundException, SQLException {

		EasyLinkDatabaseManager manager = new EasyLinkDatabaseManager();
		StringBuilder sb = new StringBuilder();
		InsertCheck checker = new InsertCheck();

		System.out.println("username: "+username);
		if (manager.insertLink(link.getId(), link.getURL(), username)) {
			
			sb.append("Link inserted in database!");
			checker.setResult(true);

		} else {
			sb.append("Double entry or error");
			checker.setResult(false);
		}

		return checker;

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public InsertCheck addRegistration(@RequestBody Registration registration)
//	(@RequestParam(value = "username", required = true) String username, 
//			@RequestParam(value = "password", required = true) String password,
//			@RequestParam(value = "email", required = true) String email,
//			@RequestParam(value = "id", required = true) String id,
//			@RequestParam(value = "link", required = true) String link)  
					throws ClassNotFoundException, SQLException {

		StringBuilder sb = new StringBuilder();
		InsertCheck checker = new InsertCheck();
;
		
		if(dbManager.insertRegistration(registration.getUsername(), registration.getPassword(), registration.getEmail())) {
			checker.setResult(true);
		} else {
			checker.setResult(false);
		}

		return checker;

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{username}/links/{id}")
	public String deleteRegistration(@PathVariable String id) {
		StringBuilder sb = new StringBuilder();
		boolean result = false;
		result = dbManager.deleteRegistration(id);

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
