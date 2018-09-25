package com.easyLink.easyLink;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkManager {

//	@Autowired
//	private LinkRepository linkRepository;
	
	private List<LinkObject> links = new ArrayList<>(Arrays.asList(
			new LinkObject("links", "www.google.com"), 
			new LinkObject("odo", "odo.lv"),
			new LinkObject("ss", "www.youtube.com"))
			);

	public List<LinkObject> getAllLinks() {
		return links;
//		List<Link> liste = new ArrayList<Link>();
//		linkRepository.findAll()
//		.forEach(liste::add);
//		return liste;
	}

	public LinkObject getLink(String id) {
		return links.stream().filter(l -> l.getId().equals(id)).findFirst().get();
//		return linkRepository.findById(id).get();
	}

	public void addLink(LinkObject link) {
		link = new LinkObject(link.getId(), link.getURL());
		links.add(link);
//		linkRepository.save(link);

	}
	
	public void addLink(String id, String URL) {
		LinkObject link = new LinkObject(id, URL);
		links.add(link);
//		linkRepository.save(link);
	}

	public void updateLink(String id, LinkObject link) {
		for(LinkObject item: links) {
			if(item.getId().equals(id)) {
				item = link;
				return;
			}
		}
		
//		linkRepository.save(link);
		
	}

	public void deleteLink(String id) {
		links.removeIf(l -> l.getId().equals(id));
//		linkRepository.deleteById(id);
	}
	
}
