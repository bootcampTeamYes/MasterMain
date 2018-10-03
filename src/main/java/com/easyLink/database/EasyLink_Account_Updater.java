package com.easyLink.database;

import com.easyLink.registration.Registration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EasyLink_Account_Updater {
    private Set<Registration> set;
    private List<Registration> list;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql=null;
    Registration reg=new Registration();
    protected Connection conn;

    public EasyLink_Account_Updater() throws ClassNotFoundException {
        final String USERNAME = "admin"; // root
        final String PASSWORD = "Lotiopaf1"; //abcd1234
        final String CONN_STRING = "jdbc:mysql://localhost/?autoReconnect=true&useSSL=false";

        try {

            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

            conn.setAutoCommit(false);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Registration> showUser_ID() throws SQLException {
        String sql = "SELECT username FROM easylink.easylink_acc";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        List<Registration> regList=new ArrayList<Registration>();
        while(rs.next()) {
            reg=new Registration(rs.getString(1));
            System.out.println(rs.getString(1) + " ");
            regList.add(reg);
        }
        return regList;
    }

    public List<Registration> showRegUser_ID() throws SQLException {
        String sql = "SELECT username FROM easylink.easylink_reg_data";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        List<Registration> regList=new ArrayList<Registration>();
        while(rs.next()) {
            reg=new Registration(rs.getString(1));
            System.out.println(rs.getString(1) + " ");
            regList.add(reg);
        }
        return regList;
    }

//    public Set<Registration> getAllAcc() throws SQLException {
//        set=new HashSet<Registration>();
//                sql = "SELECT username,password,email FROM easylink.easylink_acc";
//        ps=conn.prepareStatement(sql);
//        rs=ps.executeQuery();
//int i=0;
//        while(rs.next()){
//
//            reg=new Registration(rs.getString(1));
//            set.add(reg);
//            System.out.println(i+" "+rs.getString(1));
////            reg=new Registration(rs.getString(1),rs.getString(2),rs.getString(3));
////            set.add(reg);
////            System.out.println(i+" "+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
//            i++;
//        }

//        for(int j=0;j<set.size();j++){
//            System.out.println(j);
//        }

//        return set;
//    }

//    public void insertInReg(Set set){
//        ArrayList<Set<Registration>> arrayList=new ArrayList<>();
//        arrayList.addAll(set);
//        for (int i=0;i<arrayList.size();i++){
//            System.out.println(arrayList.get(i));
//        }
//
//    }

    public void updateTable(){

        sql = "SELECT username,password,email FROM easylink.easylink_acc";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("----");
            System.out.println("Started while loop!");
            while (rs.next()) {
                String username = rs.getString(1);
                System.out.println("username: " + username);
                String password = rs.getString(2);
                System.out.println("password: " + password);
                String email = rs.getString(3);
                System.out.println("email: " + email);
//                System.out.println("Started while loop!");
                if (!checkDuplicateUsername(username)) {
                    System.out.println("in IF!");
                    updateRegTable(username, password, email);
                }

              }
            System.out.println("Finished while loop!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateRegTable(String username, String password, String email) throws SQLException {
        sql = "INSERT INTO easylink.easylink_reg_data(username,password,email) VALUES (?,?,?)";
        System.out.println("1");
        ps=conn.prepareStatement(sql);
        ps.setString(1,username);
        ps.setString(2,password);
        ps.setString(3,email);
        System.out.println(username+" "+password+" "+email);
        int res = ps.executeUpdate();
        System.out.println(username);
        if(res==0) {
            System.out.println("res==0");
        }else if(!checkDuplicateUsername(username)) {
            conn.commit();
            System.out.println("commited: " +username +" "+ password +" "+ email );
        }else{
            System.out.println("duplicate");
        }
    }

    public boolean insertRegLink(String username, String password, String email, String id, String full_url){

        sql="INSERT INTO easylink.easylink_acc VALUES(?,?,?,?,?)";

        try {
            ps=conn.prepareStatement(sql);

            ps.setString(1,username);
            ps.setString(2,password);
            ps.setString(3,email);
            ps.setString(4,id);
            ps.setString(5,full_url);

            if(!checkDuplicateUsername(username) && !checkDuplicateID(id)) {


                System.out.println(username + " " + password + " " + email + " " + id + " " + full_url);
                int res = ps.executeUpdate();
                System.out.println(res);
                if (res == 0) {
                    return false;
                } else if (!checkDuplicateUsername(username) && !checkDuplicateID(id)) {
                    System.out.println("in easylink_acc else if! ");
                    conn.commit();
                    return true;
                } else {
                    System.out.println("Cannot input data");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean checkDuplicateUsername(String username){
        sql="SELECT username FROM easylink.easylink_reg_data WHERE username=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            rs=ps.executeQuery();
        while(rs.next()) {
            System.out.println("rs.getString: "+ rs.getString(1) +"\n" +
                    "username : " + username);
            if(rs.getString(1).equals(username)) {
                return true;
            }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkDuplicateID(String id){
        sql="SELECT id FROM easylink.easylink_acc WHERE id=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,id);
            rs=ps.executeQuery();

            while(rs.next()) {
                System.out.println("rs.getString: "+ rs.getString(1) +"\n" +
                        "id : " + id);
                if(rs.getString(1).equals(id)) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


public static void main(String []args) throws ClassNotFoundException, SQLException {
    System.out.println("HI there");

        EasyLink_Account_Updater account_updater = new EasyLink_Account_Updater();
//        account_updater.showUser_ID();
//    System.out.println("------------------");
//        account_updater.showRegUser_ID();
    System.out.println(account_updater.checkDuplicateID("insertid1"));
//        account_updater.insertRegLink("insertuser33222","insertpassword111","insertemail111","insertid3322222","insertfullurl111");
        account_updater.updateTable();
//        account_updater.updateRegTable("user6","pass6","email6");
//        account_updater.insertInReg(account_updater.getAllAcc());

    System.out.println("BYE there");
}
}
