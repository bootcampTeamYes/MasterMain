package com.easyLink.easyLink;

import java.sql.*;

public class EasyLink_DatabaseManager {
    URL link=new URL();
    PreparedStatement ps;
    ResultSet rs;
    String sql=null;
    protected Connection conn;

    // Veido savienojumu ar datubāzi
    public EasyLink_DatabaseManager() throws ClassNotFoundException {
        final String USERNAME = "root";
        final String PASSWORD = "abcd1234";
        final String CONN_STRING = "jdbc:mysql://localhost/?autoReconnect=true&useSSL=false";

        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

            conn.setAutoCommit(false);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Printē visus linkus
    public void printAllLinks(){

        String sql = "SELECT * FROM easylink.easylink";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1)+" - "+rs.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Atrod linku pēc id(saīsinātā nosaukuma), ja neatrod, tad atgriež tukšu linku
    public URL findLink(String id) {

        try {

            String sql = "SELECT * FROM easylink.easylink WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            rs.next();
            return new URL(rs.getString(1), rs.getString(2));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new URL("null", "null");
    }


    // Ieliek datubāzē jauno linku.
    public boolean insertLink(String id, String full_url){

        sql="INSERT INTO easylink.easylink VALUES(?,?)";

        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2,full_url);
            System.out.println(id + " " + full_url);
            int res=ps.executeUpdate();
            System.out.println(res);
            if(res==0){
                return false;
            }
            if(checkDuplicateID(id)) {
                conn.commit();
                return true;
            }
            System.out.println("hi");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }


    // Ja atrod duplikatu, tad izmet false.
    public boolean checkDuplicateID(String id){
        sql="SELECT id FROM easylink.easylink WHERE id=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,id);
            rs=ps.executeQuery();

            if(rs.next()){
            return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
        }
    
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Hello world!");
		EasyLink_DatabaseManager db = new EasyLink_DatabaseManager();
		System.out.println(db.findLink("mySQL"));
	}
    
}

//package com.easyLink.easyLink;
//
//import java.sql.*;
//
//public class EasyLink_DatabaseManager {
//    Linker link=new Linker();
//    PreparedStatement ps;
//    ResultSet rs;
//    String sql=null;
//    protected Connection conn;
//
//    // Veido savienojumu ar datubāzi
//    public EasyLink_DatabaseManager() throws ClassNotFoundException {
//        final String USERNAME = "admin";
//        final String PASSWORD = "Lotiopaf1";
//        final String CONN_STRING = "jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false";
//
//        try {
//            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
//
//            conn.setAutoCommit(false);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    // Printē visus linkus
//    public void printAllLinks(){
//
//        String sql = "SELECT * FROM easylink.easylink";
//
//        try {
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                System.out.println(rs.getString(1)+" - "+rs.getString(2));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    // Atrod linku pēc id(saīsinātā nosaukuma), ja neatrod, tad atgriež tukšu linku
//    public Linker findLink(String id) {
//
//        try {
//
//            String sql = "SELECT * FROM easylink.easylink WHERE id=?";
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, id);
//            rs = ps.executeQuery();
//
//            rs.next();
//            return new Linker(rs.getString(1), rs.getString(2));
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return new Linker("null", "null");
//    }
//
//
//    // Ieliek datubāzē jauno linku.
//    public boolean insertLink(String id, String full_url){
//
//        sql="INSERT INTO easylink.easylink VALUES(?,?)";
//
//        try {
//            ps=conn.prepareStatement(sql);
//            ps.setString(1,id);
//            ps.setString(2,full_url);
//            System.out.println(id + " " + full_url);
//            int res=ps.executeUpdate();
//            System.out.println(res);
//            if(res==0){
//                return false;
//            }
//            if(checkDuplicateID(id)) {
//                conn.commit();
//                return true;
//            }
//            System.out.println("hi");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return false;
//    }
//
//
//    // Ja atrod duplikatu, tad izmet false.
//    public boolean checkDuplicateID(String id){
//        sql="SELECT id FROM easylink.easylink WHERE id=?";
//        try {
//            ps=conn.prepareStatement(sql);
//            ps.setString(1,id);
//            rs=ps.executeQuery();
//
//            if(rs.next()){
//            return false;
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
//}
//>>>>>>> a720ef0a3c3dec50c524b5994d4a24bd173309a5
