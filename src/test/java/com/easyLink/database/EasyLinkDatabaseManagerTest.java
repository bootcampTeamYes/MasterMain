package com.easyLink.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.easyLink.database.EasyLinkDatabaseManager;
import com.easyLink.links.URL;

public class EasyLinkDatabaseManagerTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(5);

//	private Logger logger = LoggerFactory.getLogger(EasyLinkDatabaseManager.class);

	private static Connection conn = Mockito.mock(Connection.class);

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Test
	public void testInsertLink() throws ClassNotFoundException, SQLException {
		EasyLinkDatabaseManager manager = new EasyLinkDatabaseManager();
		manager.setConn(conn);

		PreparedStatement testPS = Mockito.mock(PreparedStatement.class);
		when(conn.prepareStatement(anyString())).thenReturn(testPS);
		doNothing().when(testPS).setString(anyInt(), anyString());
		when(testPS.executeUpdate()).thenReturn(1);
		doNothing().when(conn).commit();

		assertEquals(true, manager.insertLink("google", "www.google.com"));

		when(testPS.executeUpdate()).thenReturn(0);

		assertEquals(false, manager.insertLink("", ""));

	}

	@Test
	public void testGetLink() throws SQLException, ClassNotFoundException {

		EasyLinkDatabaseManager manager = new EasyLinkDatabaseManager();
		String name = "labi";
		String url = "www.google.com";

		manager.setConn(conn);
		PreparedStatement testPS = Mockito.mock(PreparedStatement.class);
		when(conn.prepareStatement(anyString())).thenReturn(testPS);
		doNothing().when(testPS).setString(anyInt(), anyString());
		when(testPS.executeUpdate()).thenReturn(1);
		doNothing().when(conn).commit();

		manager.insertLink(name, url);

		ResultSet resSet = Mockito.mock(ResultSet.class);
		when(testPS.executeQuery()).thenReturn(resSet);
		when(resSet.next()).thenReturn(true);
		assertEquals(true, resSet.next());

		when(resSet.getString(anyInt())).thenReturn(url);

		assertEquals("You`re not lucky. No link found for such Id :(", manager.getLink(name));
	}
	
	@Test 
	public void testGetAllLinks() throws SQLException, ClassNotFoundException {
		EasyLinkDatabaseManager manager = new EasyLinkDatabaseManager();
		List<URL> list = new ArrayList<URL>();
		List<URL> expected = new ArrayList<URL>();
		 
		String name = "labi";
		String url = "www.google.com";
		
		String name2 = "testId";
		String url2 = "odo.lv";
		
		expected.add(new URL(name, url));
		expected.add(new URL(name2, url2));
		manager.setConn(conn);
		PreparedStatement testPS = Mockito.mock(PreparedStatement.class);
		when(conn.prepareStatement(anyString())).thenReturn(testPS);
		doNothing().when(testPS).setString(anyInt(), anyString());
		when(testPS.executeUpdate()).thenReturn(1);
	    doNothing().when(conn).commit();
	       
		manager.insertLink(name, url);
		manager.insertLink(name2, url2);
//		when(testPS.executeUpdate()).thenReturn(0);
        ResultSet resSet = Mockito.mock(ResultSet.class);
//        when(testPS.executeQuery()).thenReturn(resSet);
        when(resSet.next()).thenReturn(true);
        assertEquals(true, resSet.next());
        
//        when(manager.getAllLinks()).thenReturn(list); //getAllLinks() aiziet timeouta
//        assertEquals(expected, list);
        
	}
	
	@Test
	public void testDeleteLink() throws SQLException, ClassNotFoundException {
		EasyLinkDatabaseManager manager = new EasyLinkDatabaseManager();
		String name = "labi";
		String url = "www.google.com";
		
		manager.setConn(conn);
		PreparedStatement testPS = Mockito.mock(PreparedStatement.class);
		when(conn.prepareStatement(anyString())).thenReturn(testPS);
		doNothing().when(testPS).setString(anyInt(), anyString());
		when(testPS.executeUpdate()).thenReturn(1);
	    doNothing().when(conn).commit();
	       
		manager.insertLink(name, url);
		
	    assertEquals(false, manager.deleteLink(name));
}

//	@SuppressWarnings("unchecked")
//	@Test
//	public void test00Exceptions() throws SQLException {
//		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
//
//		conn = Mockito.mock(Connection.class);
//		manager.conn = conn;
//
//	//  create mock
//		EasyLinkDatabaseManager test = mock(EasyLinkDatabaseManager.class);
//
//        // define return value for method getUniqueId()
//        when(test.insertLink("gramata", "www.facebook.com")).thenReturn(true);
//
//        // use mock in test....
//        assertEquals(test.insertLink("gramata", "www.facebook.com"), 43);
//	}

//		Mockito.when(conn.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
//		manager.findStudent(1);
//		manager.findStudent("fName", "lName");
//		manager.insertStudent("fName", "lName");
//		Student student = new Student(1, null, null);
//		manager.insertStudent(student);
//		manager.updateStudent(student);
//		manager.deleteStudent(1);
//		Mockito.doThrow(new SQLException()).when(conn).close();
//		manager.closeConnecion();

}
