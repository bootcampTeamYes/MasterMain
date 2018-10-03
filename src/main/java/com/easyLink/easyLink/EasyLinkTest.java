package com.easyLink.easyLink;

////import static jtm.TestUtils.executeCmd;
////import static org.junit.Assert.assertEquals;
////import static org.junit.Assert.assertNotEquals;
////import static org.junit.Assert.assertNull;
////import static org.junit.Assert.assertTrue;
////
////import org.junit.runners.MethodSorters;
////import org.junit.After;
////import org.junit.AfterClass;
////import org.junit.Before;
////import org.junit.BeforeClass;
////import org.junit.FixMethodOrder;
////import org.junit.Test;
////import java.util.List;
//
//
//public class EasyLinkTest {
//
//    public static EasyLink_DatabaseManager dbm;
//
//    public static void main(Stirng[] args) {
//
//    }
//}
//
//
//
//
////@FixMethodOrder(MethodSorters.NAME_ASCENDING)
////public class EasyLinkTest {
////
////    public static EasyLink_DatabaseManager dbManager;
////
////    public static void main(String[] args) {
////        EasyLinkTest dbUnitTest = new EasyLinkTest();
////        dbUnitTest.test();
////    }
////
////    /**
////     * @throws java.lang.Exception
////     */
////    @BeforeClass
////    public static void beforeClass() throws Exception {
//////		resetDatabase();
//////		manager = new StudentManager();
////
////    }
////
////    /**
////     * @throws java.lang.Exception
////     */
////    @AfterClass
////    public static void afterClass() throws Exception {
//////		manager.conn.commit();
//////		manager.conn.close();
////
////    }
////
////    /**
////     * @throws java.lang.Exception
////     */
////    @Before
////    public void setUp() throws Exception {
////        resetDatabase();
////        manager = new StudentManager();
////    }
////
////    public static void resetDatabase() {
////        String workspace = System.getProperty("user.dir");
////        executeCmd("mysql -h 127.0.0.1 --protocol=tcp -s -uroot -pabcd1234 < " + workspace
////                + "/src/main/java/jtm/activity13/database.sql 2>/dev/null");
////    }
////
////    /**
////     * @throws java.lang.Exception
////     */
////    @After
////    public void tearDown() throws Exception {
////        if (manager.conn != null) {
////            manager.conn.commit();
////        }
////    }
////
////    @Test
////    public final void testStudentManager() {
////        assertNotEquals("Connection is not initialized", null, manager.conn);
////    }
////
////    @Test
////    public final void testFindStudentById() {
////        Student result = manager.findStudent(1);
////        assertEquals("incorrect name", result.getFirstName(), "Anna");
////        assertNotEquals("incorrect name", result.getFirstName(), "Ann");
////        result = manager.findStudent(3);
////        assertEquals("incorrect lastname", result.getLastName(), "Uno");
////        assertNotEquals("incorrect lastname", result.getLastName(), "no");
////    }
////
////    @Test
////    public final void testFindStudentByNameSurname() {
////        List<Student> result = manager.findStudent("Anna", "Tress");
////        assertEquals("incorrect array size", result.size(), 1);
////        assertEquals("incorrect ID", result.get(0).getID(), 1);
////    }
////
////    @Test
////    public final void testInsertStudentNameSurname() {
////        // resetDatabase();
////        manager.insertStudent("Kyle", "Broflovski");
////        assertEquals("Insertion error", manager.findStudent("Kyle", "Broflovski").get(0).getFirstName(), "Kyle");
////        assertEquals("Insertion error", manager.findStudent("Kyle", "Broflovski").get(0).getLastName(), "Broflovski");
////    }
////
////    @Test
////    public final void testInsertStudentObject() {
////        Student newStudent = new Student(4, "Putin", "Putout");
////        manager.insertStudent(newStudent);
////        Student result = manager.findStudent(4);
////        assertEquals("Name Insertion Error", result.getFirstName(), newStudent.getFirstName());
////        assertEquals("ID Insertion Error", result.getID(), newStudent.getID());
////        assertEquals("Surname Insertion Error", result.getLastName(), newStudent.getLastName());
////    }
////
////    @Test
////    public final void testUpdateStudent() {
////        Student before = manager.findStudent(1);
////        manager.updateStudent(new Student(1, "Lol", "Kek"));
////        assertNotEquals("Update Student Error", manager.findStudent(1).getFirstName(), before.getFirstName());
////    }
////
////    @Test
////    public final void testDeleteStudent() {
////        Student toBeDeletedSt = new Student(4, "To_be", "Deleted");
////        manager.insertStudent(toBeDeletedSt);
////        manager.deleteStudent(4);
////        try {
////            assertNotEquals("Deletion Error", manager.findStudent(4).getFirstName(), toBeDeletedSt.getFirstName());
////        } catch (Exception e) {
////            assertTrue(true);
////        }
////    }
////
////    @Test
////    public final void testCloseConnecion() {
////        if (manager != null)
////            manager.closeConnecion();
////        assertNull("Connection is not closed correctly.", manager.conn);
////
////    }
////
////}