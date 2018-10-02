package com.easyLink.registration;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.easyLink.links.URL; 

@Entity
public class Registration {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)   //makes new object Link in database ??
		private String username;
		private String password;
		private String email;
		
		@OneToMany(fetch = FetchType.EAGER)
		@JoinColumn(name = "id")
		private Set<URL> URLList;
		
		
		public Registration() {

		}

		public Registration(String username, String password, String email) {
			this.username = username;
			this.password = password;
			this.email=email;
			this.URLList=new HashSet<>();
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Set<URL> getList() {
			return URLList;
		}

		public void addToList(URL url) {
			this.URLList.add(url);
		}


}