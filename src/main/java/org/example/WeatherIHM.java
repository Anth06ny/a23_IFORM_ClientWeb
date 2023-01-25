package org.example;

import org.example.beans.WeatherBean;

import javax.swing.*;
import java.awt.*;

public class WeatherIHM extends JFrame  {
    private JButton jbPositive;
    private JLabel jlQuestion;
    private JLabel jlMessage;
    private JTextField jtfAnswer;
    private JProgressBar progressBar;

    public WeatherIHM() {
        //Appel du constructeur parent
        super("Weather");

        //construct components
        jbPositive = new JButton("Chercher");
        jlQuestion = new JLabel("Donne une ville : ");
        jtfAnswer = new JTextField(5);
        jlMessage = new JLabel("Ici pour écrire un message");
        progressBar = new JProgressBar();
        progressBar.setVisible(false);
        progressBar.setIndeterminate(true);


        //Conteneur
        JPanel pannel = new JPanel();
        pannel.setPreferredSize(new Dimension(408, 211));
        pannel.setLayout(null);

        //add components
        pannel.add(jbPositive);
        pannel.add(jlQuestion);
        pannel.add(jtfAnswer);
        pannel.add(jlMessage);
        pannel.add(progressBar);

        //set component bounds (only needed by Absolute Positioning)
        jbPositive.setBounds(285, 155, 95, 35);
        jlQuestion.setBounds(25, 15, 410, 70);
        jtfAnswer.setBounds(25, 80, 355, 30);
        jlMessage.setBounds(25, 110, 355, 30);
        progressBar.setBounds(25, 140, 355, 10);

        //Travail Sur la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(pannel);
        pack();
        setVisible(true);

        //clic
        jbPositive.addActionListener(e -> {

            progressBar.setVisible(true);
            new Thread(() -> {
                refreshData();
            }).start();
        });
    }

    public void refreshData(){
        //clic sur le bouton positif
        try {
            WeatherBean res = RequestUtils.loadWeather(jtfAnswer.getText());
            jlMessage.setText("Il fait " + res.getMain().getTemp() + "° à " + res.getName() + " avec un vent de " + res.getWind().getSpeed() + "km/h");

        } catch (Exception ex) {
            ex.printStackTrace();
            jlMessage.setText("Une erreur est survenue : " + ex.getMessage());
        }

        progressBar.setVisible(false);
    }

    public static void main(String[] args) {
        WeatherIHM popup = new WeatherIHM();
    }
}
