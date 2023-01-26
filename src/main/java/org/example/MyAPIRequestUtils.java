package org.example;

import com.google.gson.Gson;
import org.example.beans.StudentBean;

public class MyAPIRequestUtils {


    public static final String URL_MY_API = "http://localhost:8080";

    public static void main(String[] args) throws Exception {
        System.out.println(increment(new StudentBean("Toto", 14)));

    }

    /* -------------------------------- */
    // EXO POST
    /* -------------------------------- */

    public static void sendStudent(StudentBean student) throws Exception {
        String json = new Gson().toJson(student);
        RequestUtils.sendPost(URL_MY_API + "/receiveStudent", json);
    }

    public static StudentBean increment(StudentBean studentToSend) throws Exception {

        //Envoie
        String jsonToSend = new Gson().toJson(studentToSend);
        String jsonReceive = RequestUtils.sendPost(URL_MY_API + "/increment", jsonToSend);
        StudentBean studentReceive = new Gson().fromJson(jsonReceive, StudentBean.class);
        return studentReceive;
    }

    /* -------------------------------- */
    // MiniProjet Save Student
    /* -------------------------------- */

    //ENvoie un student au serveur a sauvegarder
    public static void saveStudent(String name, String note) throws Exception {
        RequestUtils.sendGet(URL_MY_API + "/saveStudent?nom=" + name + "&note=" + note);
    }

    public static StudentBean loadStudent() throws Exception {
        String json = RequestUtils.sendGet(URL_MY_API + "/loadStudent");
        StudentBean student = new Gson().fromJson(json, StudentBean.class);

        return student;

    }

    /* -------------------------------- */
    // Exo GET
    /* -------------------------------- */

    public static StudentBean createStudent(String name, int note) throws Exception {

        String json = RequestUtils.sendGet(URL_MY_API + "/createStudent?nom=" + name + "&note=" + note);
        StudentBean student = new Gson().fromJson(json, StudentBean.class);

        return student;

    }

    public static int max(int a, int b) throws Exception {
        String reponse = RequestUtils.sendGet(URL_MY_API + "/max?p1=" + a + "&p2=" + b);

        return Integer.parseInt(reponse);
    }


    public static void test() throws Exception {

        String res = RequestUtils.sendGet(URL_MY_API + "/test");
        System.out.println(res);
    }
}
