package com.easyLink.links;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.easyLink.links.URL;

public class URLTest {

	@Test
	public void testGetters() {
		URL test = new URL("viens", "divi");
		assertEquals("viens", test.getId());
		assertEquals("divi", test.getURL());
	}
	
	@Test
	public void testSetters() {
		URL test = new URL("viens", "divi");
		test.setId("pieci");
		test.setURL("sesi");
		
		assertEquals("pieci", test.getId());
		assertEquals("sesi", test.getURL());
	}
}
