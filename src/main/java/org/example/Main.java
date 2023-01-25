package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        try {
            String res = RequestUtils.sendGet("https://www.google.fr");
            System.out.println(res);
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : ");
            e.printStackTrace();
        }

        System.out.println("Fin");
    }
}