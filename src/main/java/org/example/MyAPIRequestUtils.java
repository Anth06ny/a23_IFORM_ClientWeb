package org.example;

import com.google.gson.Gson;
import org.example.beans.StudentBean;

public class MyAPIRequestUtils {

    private static final String URL_MY_API = "http://localhost:8080";

    public static void main(String[] args) throws Exception {

//        int max = max(7,8);
//        System.out.println("max=" + max);

//        StudentBean studentBean = createStudent("Toto", 12);
//
//        System.out.println(studentBean.getName() + " a eu " + studentBean.getNote());

        saveStudent("bb", "12");

        StudentBean studentBean = loadStudent();
        System.out.println(studentBean.getName() + " " + studentBean.getNote()) ;

    }

    /* -------------------------------- */
    // MiniProjet Save Student
    /* -------------------------------- */

    //ENvoie un student au serveur a sauvegarder
    public static void saveStudent(String name, String note) throws Exception {
        RequestUtils.sendGet(URL_MY_API + "/saveStudent?nom=" + name + "&note=" + note);
    }

    public static StudentBean loadStudent() throws Exception{
        String json = RequestUtils.sendGet(URL_MY_API + "/loadStudent");
        StudentBean student = new Gson().fromJson(json, StudentBean.class);

        return student;

    }

    /* -------------------------------- */
    // Exo
    /* -------------------------------- */

    public static StudentBean createStudent(String name , int note) throws Exception{
        String json = RequestUtils.sendGet(URL_MY_API + "/createStudent?nom=" + name + "&note=" + note);
        StudentBean student = new Gson().fromJson(json, StudentBean.class);

        return student;

    }

    public static int max(int a, int b) throws Exception{
        String reponse = RequestUtils.sendGet(URL_MY_API + "/max?p1=" + a + "&p2=" + b);

         return Integer.parseInt(reponse);
    }



    public static void test() throws Exception {

        String res = RequestUtils.sendGet(URL_MY_API + "/test");
        System.out.println(res);
    }
}
