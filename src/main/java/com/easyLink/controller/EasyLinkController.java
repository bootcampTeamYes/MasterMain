package com.easyLink.controller;

import java.sql.SQLException;
import java.util.List;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.easyLink.database.EasyLinkDatabaseManager;
import com.easyLink.database.InsertCheck;
import com.easyLink.links.URL;
import javax.servlet.http.HttpServletResponse;

/**
 * This is web controller for our application. It handles HTTP requests. If you enter "http://localhost:8080/links/" in Web Browser address bar,
 * you will see what currently database stores.
 * Example:    (JSON)
 * {
 * ..
 * {
 * "id": name for link given by user,
 * "full_url": links full address,
 * },
 * ..
 * }
 * For our API, you can try tool, such as the Firefox RESTClient, or Chrome's POSTMAN to test the REST integration.
 * Using them you can check mapping for HTTP GET requests, add new urls in database with HTTP POST, or DELETE some records from database.
 * To GET, POST or DELETE something you must choose in corresponded request method in REST integration testing tool
 * and enter following address "http://localhost:8080/links/{id}" where {id} is needed links name.
 *
 * @author Kristaps, Raivis, Martins, Arturs
 */

@RestController
public class EasyLinkController {

	@Autowired
	private EasyLinkDatabaseManager dbManager;

	@RequestMapping("/links")
	public List<URL> getAllLinks() throws ClassNotFoundException, SQLException {

		dbManager = new EasyLinkDatabaseManager();
		return dbManager.getAllLinks();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/links/{id}")
	public String getLink(@PathVariable String id, HttpServletResponse httpServletResponse)
			throws ClassNotFoundException, SQLException {

		if (dbManager.getLink(id) == null) {
			StringBuilder sb = new StringBuilder();

			sb.append("Wrong entry! Try again<br/>\n");

			return sb.toString();
		} else {

			String link = dbManager.getLink(id);

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

	@RequestMapping(method = RequestMethod.POST, value = "/links")
	public InsertCheck addLink(@RequestBody URL link) throws ClassNotFoundException, SQLException {

		StringBuilder sb = new StringBuilder();
		InsertCheck checker = new InsertCheck();

		if (dbManager.insertLink(link.getId(), link.getURL(), null)) {
			sb.append("Link inserted in database!");
			checker.setResult(true);

		} else {
			sb.append("Double entry or error");
			checker.setResult(false);
		}
 
		return checker;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/links/{id}")
	public String deleteLink(@PathVariable String id) throws ClassNotFoundException, SQLException {
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