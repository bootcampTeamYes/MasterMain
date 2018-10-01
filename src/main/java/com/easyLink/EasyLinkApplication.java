package com.easyLink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** 
 * This application allows you to shorten your links and name them by specified Id.
 * To run the program successfully it is necessary to have MySQL database server installed on your machine.
 * Database Username must be set to: "root" and password must be set to "abcd1234". 
 * User interface can be found entering "http://localhost:8080/" in your Web Browser address bar.
 * 
 * 
 * @author Kristaps
 *
 */

@SpringBootApplication
public class EasyLinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyLinkApplication.class, args);
	}
}

