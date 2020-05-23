package com.example.bourbon.activities.harish_activities.model;

public class CovidStatus {
    private String cityName="";
    private int activeCases=0,recovered=0,deaths=0;

    public CovidStatus() {
    }

    public CovidStatus(String cityName, int activeCases, int recovered, int deaths) {
        this.cityName = cityName;
        this.activeCases = activeCases;
        this.recovered = recovered;
        this.deaths = deaths;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getActiveCases() {
        return ""+activeCases;
    }

    public void setActiveCases(int activeCases) {
        this.activeCases = activeCases;
    }

    public String getRecovered() {
        return ""+recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public String getDeaths() {
        return ""+deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
}
