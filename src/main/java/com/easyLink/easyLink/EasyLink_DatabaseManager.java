package com.easyLink.easyLink;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
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
        	
        	Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

            conn.setAutoCommit(false);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Printē visus linkus
    public List<URL> getAllLinks(){

    	List<URL> liste = new ArrayList<URL>();
        String sql = "SELECT * FROM easylink.easylink";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
            	
                link = new URL(rs.getString(1), rs.getString(2));
                liste.add(link);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return liste;
    }


    // Atrod linku pēc id(saīsinātā nosaukuma), ja neatrod, tad atgriež tukšu linku
    public String getLink(String id) {

        try {

            String sql = "SELECT * FROM easylink.easylink WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            rs.next();
            
            return rs.getString(2);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "You`re not lucky. No link found for such Id :(";
    }


    // Ieliek datubāzē jauno linku.
    public boolean insertLink(String id, String full_url){

        sql="INSERT INTO easylink.easylink VALUES(?,?)";

        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2,full_url);

            int res=ps.executeUpdate();

            if(res==0){
                return false;
            }
            conn.commit();
            
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
            return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        }
    
    public boolean deleteLink(String id) {
		// TODO #7 Write an sql statement that deletes teacher from database.
		boolean status = false;
		sql = "DELETE FROM easylink.easylink WHERE id = ?";

		int result = 0;
		try {

			ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            result = ps.executeUpdate();
            if(result==0){
                return false;
            }
            conn.commit();
		} catch (SQLException e) {

			e.printStackTrace();
			
		} finally {
			if (result != 0) {
				status = true;
			}
		}
		return status;
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
