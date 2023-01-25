package org.example;

import com.google.gson.Gson;
import org.example.beans.StudentBean;

public class MyAPIRequestUtils {

    private static final String URL_MY_API = "http://localhost:8080";

    public static void main(String[] args) throws Exception {

//        int max = max(7,8);
//        System.out.println("max=" + max);

        StudentBean studentBean = createStudent("Toto", 12);

        System.out.println(studentBean.getName() + " a eu " + studentBean.getNote());

    }

    public static StudentBean createStudent(String name , int note) throws Exception{
        String json = RequestUtils.sendGet("http://localhost:8080/createStudent?nom=" + name + "&note=" + note);
        StudentBean student = new Gson().fromJson(json, StudentBean.class);

        return student;

    }

    public static int max(int a, int b) throws Exception{
        String reponse = RequestUtils.sendGet("http://localhost:8080/max?p1=" + a + "&p2=" + b);

         return Integer.parseInt(reponse);
    }



    public static void test() throws Exception {

        String res = RequestUtils.sendGet(URL_MY_API + "/test");
        System.out.println(res);
    }
}
