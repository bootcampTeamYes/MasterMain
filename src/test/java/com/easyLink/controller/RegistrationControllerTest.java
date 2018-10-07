package com.easyLink.controller;

import com.easyLink.database.EasyLinkDatabaseManager;
import com.easyLink.database.RegistrationDatabaseManager;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class RegistrationControllerTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    private Logger logger = LoggerFactory.getLogger(EasyLinkDatabaseManager.class);

    private static Connection conn = Mockito.mock(Connection.class);

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void getRegistrationLinks() throws SQLException, ClassNotFoundException {
        RegistrationDatabaseManager controller = new RegistrationDatabaseManager();
        controller.setConn(conn);

        String username="username";
        String name = "google?";
        String url = "www.google.com";

        String username2="username2";
        String name2 = " ";
        String url2 = "www.yandex.com";

        PreparedStatement testPS = Mockito.mock(PreparedStatement.class);
        when(conn.prepareStatement(anyString())).thenReturn(testPS);
        doNothing().when(testPS).setString(anyInt(), anyString());
        when(testPS.executeUpdate()).thenReturn(1);
        doNothing().when(conn).commit();

        ResultSet resSet = Mockito.mock(ResultSet.class);
        when(testPS.executeQuery()).thenReturn(resSet);
        when(resSet.next()).thenReturn(true);
        when(resSet.getString(anyInt())).thenReturn("redirect:" + url);
        assertTrue(resSet.next());

        controller.insertRegistration(username,name, url);
        assertEquals("redirect:" + url, controller.getRegistrationLink(name));

        when(resSet.getString(anyInt())).thenReturn("Wrong entry! Try again\n");
        controller.insertRegistration(username2,name2, url2);

        assertEquals("Wrong entry! Try again\n", controller.getRegistrationLink(name2));
    }
}