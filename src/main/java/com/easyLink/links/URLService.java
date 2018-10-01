package com.easyLink.links;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class URLService {

//	@Autowired
//	private LinkRepository linkRepository;

	private List<URL> links = new ArrayList<>(Arrays.asList(
			new URL("links", "www.google.com"),
			new URL("odo", "odo.lv"), 
			new URL("ss", "www.youtube.com")));

	public List<URL> getAllLinks() {
		return links;
	}

	public URL getLink(String id) {
		return links.stream().filter(l -> l.getId().equals(id)).findFirst().get();
	}

	public void addLink(URL link) {
		link = new URL(link.getId(), link.getURL());
		links.add(link);
	}

	public void addLink(String id, String url) {
		URL link = new URL(id, url);
		links.add(link);
	}

	public void updateLink(String id, URL link) {
		for (URL item : links) {
			if (item.getId().equals(id)) {
				item = link;
				return;
			}
		}
	}

	public void deleteLink(String id) {
		links.removeIf(l -> l.getId().equals(id));
	}

}
