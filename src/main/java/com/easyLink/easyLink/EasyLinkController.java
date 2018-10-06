package com.easyLink.easyLink;

//import java.util.List;
//
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
//
//import com.easyLink.database.EasyLinkDatabaseManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
////
////import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * This is web controller for our application. It handles HTTP requests. If you enter "http://localhost:8080/links/" in Web Browser address bar,
// * you will see what currently database stores.
// * Example:    (JSON)
// * {
// * ..
// * {
// * "id": name for link given by user,
// * "full_url": links full address,
// * },
// * ..
// * }
// * For our API, you can try tool, such as the Firefox RESTClient, or Chrome's POSTMAN to test the REST integration.
// * Using them you can check mapping for HTTP GET requests, add new urls in database with HTTP POST, or DELETE some records from database.
// * To GET, POST or DELETE something you must choose in corresponded request method in REST integration testing tool
// * and enter following address "http://localhost:8080/links/{id}" where {id} is needed links name.
// *
// * @author Kristaps, Raivis, Martins, Arturs
// */
//
//@RestController
//public class EasyLinkController {
//
//	@Autowired
//	private EasyLinkDatabaseManager dbManager;
////	@Autowired
////	private URLService urlService;
//
////	@RequestMapping(value = "/", produces = "text/html;charset=UTF-8")
////	public String sayHi(@RequestParam(value = "id", required = false) String id,
////			@RequestParam(value = "URL", required = false) String URL, HttpServletResponse response) {
////
////		StringBuilder sb = new StringBuilder();
////
////		if (id == null && URL == null) {
////
////			sb.append("Hi, welcome in EasyLink\n");
////			sb.append("<form action=''>\n");
////			sb.append("Your link name: <input type='text' name='id' value=''><br/>\n");
////			sb.append("Your URL:<input type='text' name='URL' value=''><br/>\n");
////			sb.append("<input type='submit' value='Insert My URL'></form><br/>\n");
////
////			// Following is also redundant because status is OK by default:
////			response.setStatus(HttpServletResponse.SC_OK);
////			return sb.toString();
////
////		}else if (id.trim().isEmpty() || URL.trim().isEmpty()) {
////
////			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
////
////			sb.append("Wrong entry! Try again<br/>\n");
////			sb.append("<a href='/'>Back</a>\n");
////
////			return sb.toString();
////
////		}else {
////
//////			linkManager = new LinkManager();
//////
//////			linkManager.addLink(id, URL);
////			response.setStatus(HttpServletResponse.SC_OK);
////			sb.append("Done! You can find your link at: localhost:8080/"+id+" <br/>\n");
////			sb.append("<a href='/links'>Back</a>\n");
////
////			return sb.toString();
////		}
////
////	}
//
//	@RequestMapping("/links")
//	public List<URL> getAllLinks() throws ClassNotFoundException {
//		dbManager = new EasyLink_DatabaseManager();
//		return dbManager.getAllLinks();
//	}
//
////	@RequestMapping("/links/{id}")
////	public Link getLinkID(@PathVariable String id) {
////		return linkManager.getLink(id);
////	}
//
//	@RequestMapping(method = RequestMethod.GET, value = "/links/{id}")
//	public String getLink(@PathVariable String id, HttpServletResponse httpServletResponse) {
//
//		if(dbManager.getLink(id)==null) {
//			StringBuilder sb = new StringBuilder();
//
//			sb.append("Wrong entry! Try again<br/>\n");
//			sb.append("<a href='/links'>Back</a>\n");
//
//			return sb.toString();
//		}else {
//
//			 httpServletResponse.setStatus(302);
//			httpServletResponse.setHeader("Location", "http://"+dbManager.getLink(id));
//
//			return "redirect:" + dbManager.getLink(id);
//		}
//
//	}
//
//	/*
//	 * redirekts 2 varianti First:
//	 *
//	 * @RequestMapping(value = "/redirect", method = RequestMethod.GET) public void
//	 * method(HttpServletResponse httpServletResponse) {
//	 * httpServletResponse.setHeader("Location", projectUrl);
//	 * httpServletResponse.setStatus(302); }
//	 *
//	 * Second:
//	 *
//	 * @RequestMapping(value = "/redirect", method = RequestMethod.GET) public
//	 * ModelAndView method() { return new ModelAndView("redirect:" + projectUrl);
//	 *
//	 * }
//	 */
//
//	// @RequestMapping(method=RequestMethod.POST, value="/links")
//	// public void addLink(@RequestBody Link link) {
//	// linkManager.addLink(link);
//
//	@RequestMapping(method = RequestMethod.POST, value = "/links")
////	@ResponseBody
//	public String addLink(@RequestBody URL link) {
////	(@RequestParam(value = "id", required = false) String id,
////			@RequestParam(value = "URL", required = false) String URL, HttpServletResponse response) {
////
////		if (id == null && URL == null) {
////
////			StringBuilder sb = new StringBuilder();
////			sb.append("<form action=''>\n");
////			sb.append("Name: <input type='text' name='name' value=''><br/>\n");
////			sb.append("Surname:<input type='text' name='surname' value=''><br/>\n");
////			sb.append("<input type='submit' value='Insert'></form><br/>\n");
////			sb.append("<a href='/'>Back</a>\n");
////
////			response.setStatus(HttpServletResponse.SC_OK);
////
////			return sb.toString();
////
////		} else if (id.trim().isEmpty() || URL.trim().isEmpty()) {
////
////			StringBuilder sb = new StringBuilder();
////			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
////
////			sb.append("false<br/>\n");
////			sb.append("<a href='/'>Back</a>\n");
////
////			return sb.toString();
////
////		} else {
//
////			linkManager = new URLService();
//			StringBuilder sb = new StringBuilder();
//			dbManager.insertLink(link.getId(), link.getURL());
////			linkManager.addLink(id, URL);
////			response.setStatus(HttpServletResponse.SC_OK);
//			sb.append("true<br/>\n");
//			sb.append("<a href='/links'>Back</a>\n");
//
//			return sb.toString();
////		}
//
//	}
//
////	@RequestMapping(method = RequestMethod.PUT, value = "/links/{id}")
////	public void updateLink(@RequestBody URL link, @PathVariable String id) {
////		linkManager.updateLink(id, link);
////	}
////
//	@RequestMapping(method = RequestMethod.DELETE, value = "/links/{id}")
//	public String deleteLink(@PathVariable String id) {
//		StringBuilder sb = new StringBuilder();
//		boolean result = false;
//		result = dbManager.deleteLink(id);
//
//		if(result) {
//			sb.append("true<br/>\n");
//			sb.append("<a href='/links'>Back</a>\n");
//
//			return sb.toString();
//
//		}else {
//			sb.append("false<br/>\n");
//			sb.append("<a href='/links'>Back</a>\n");
//
//			return sb.toString();
//		}
//
//	}
//
//
//}
