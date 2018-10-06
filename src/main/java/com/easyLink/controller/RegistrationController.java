package com.easyLink.controller;

import java.sql.SQLException;
import java.util.HashSet;
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

import login.Login;


@RestController
public class RegistrationController {

	@Autowired
	private RegistrationDatabaseManager dbManager;
	
	@Autowired
	private EasyLinkDatabaseManager controller;


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
	
	@RequestMapping(method = RequestMethod.POST, value = "/pass/{username}")
	public boolean getCheckPassword(@RequestBody Login login, HttpServletResponse httpServletResponse,
	@PathVariable(value="username") String username)
			throws ClassNotFoundException, SQLException {

		EasyLinkDatabaseManager manager = new EasyLinkDatabaseManager();
		
			if(login.getPassword().equals(manager.getPassword(login.getUsername()))) {
				return true;
		}
		return false;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{username}/links")
	public Set<URL>  getRegistrationLinks(@PathVariable(value="username") String username, HttpServletResponse httpServletResponse)
			throws ClassNotFoundException, SQLException {
		
		if (dbManager.getRegistration(username) == null) {
			StringBuilder sb = new StringBuilder();

			sb.append("Wrong entry! Try again<br/>\n"); 

			return null;
		} else {

			return dbManager.getRegistrationLinks(username);

		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{username}/links/{id}")
	public String getRegistrationLink(@PathVariable(value="username") String username, 
			@PathVariable String id, HttpServletResponse httpServletResponse)
			throws ClassNotFoundException, SQLException {
		
		if (dbManager.getRegistration(username) == null) {
			StringBuilder sb = new StringBuilder();

			sb.append("Wrong entry! Try again<br/>\n");
 
			return null;
		} else {
			String newId = "("+username+")"+id;

			String link = dbManager.getRegistrationLink(newId);

			if (link.length() > 6
					&& (link.substring(0, 7).equals("http://") || link.substring(0, 8).equals("https://"))) {
				httpServletResponse.setStatus(302);
				httpServletResponse.setHeader("Location", link);

				return "redirect:" + link;

			} else {
				httpServletResponse.setStatus(302);
				httpServletResponse.setHeader("Location", "https://" +link);

				return "redirect:" + link;
			}
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/{username}/links")
	public InsertCheck addLink(@RequestBody URL link, @PathVariable(value = "username") String username) throws ClassNotFoundException, SQLException {

		EasyLinkDatabaseManager manager = new EasyLinkDatabaseManager();
		InsertCheck checker = new InsertCheck();
		if (manager.insertLink("("+username+")"+link.getId(), link.getURL(), username)) {

			checker.setResult(true);

		} else {
			checker.setResult(false);
		}

		return checker;
	}
	
	@RequestMapping(method = RequestMethod.PATCH, value = "/{username}/links/{id}")
	public InsertCheck editLink(@RequestBody URL link, @PathVariable(value = "username") String username, @PathVariable(value = "id") String id) throws ClassNotFoundException, SQLException {

		RegistrationDatabaseManager manager = new RegistrationDatabaseManager();
		EasyLinkDatabaseManager linkManager = new EasyLinkDatabaseManager();
		
		InsertCheck checker = new InsertCheck();
		
		Set<URL> list = new HashSet<>();

		for(URL item : manager.getRegistrationLinks(username)) {

			if(id.equals(item.getId())) {

				if (linkManager.deleteLink(id)) {

					linkManager.insertLink(link.getId(), link.getURL(), username);

					checker.setResult(true);

				} else {
					checker.setResult(false);
				}
			}
			checker.setResult(false);
		}
		return checker;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public InsertCheck addRegistration(@RequestBody Registration registration)

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

	@RequestMapping(method = RequestMethod.DELETE, value = "/user/{username}")
	public String deleteRegistration(@PathVariable String username) throws ClassNotFoundException, SQLException {
		StringBuilder sb = new StringBuilder();
		boolean result = false;
		
		Set<URL> liste = dbManager.getRegistrationLinks(username);
		
		for(URL item : liste) {
			controller.deleteLink("("+username+")"+item.getId());
		}
		
		result = dbManager.deleteRegistration(username);

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
