package org.example.beans;

public class WeatherBean {
    private TempBean main;
    private String name;
    private WindBean wind;

    public TempBean getMain() {
        return main;
    }

    public void setMain(TempBean main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WindBean getWind() {
        return wind;
    }

    public void setWind(WindBean wind) {
        this.wind = wind;
    }
}
