package com.example.bourbon.activities.arumugam_activities;

import java.io.Serializable;
import java.util.Arrays;

public class HospitalDetails implements Serializable {

    /** hospitalName variable */
    private String hospitalName;
    /** ratting variable */
    private String rating;
    /** openingHours variable */
    private String openingHours;
    /** address variable */
    private String address;
    /** geometry - latitude and longitude array */
    private double[] locationlatlng;

    /**
     * getHospitalName method to get hospital name
     * @return hospitalName
     * */
    public String getHospitalName() {
        return hospitalName;
    }

    /**
     * setHospitalName method to set hospital name
     * @param hospitalName
     * */
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    /**
     * getRating
     * @return rating
     * */
    public String getRating() {
        return rating;
    }

    /**
     * setRating
     * @param rating
     * */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * getOpeningHours
     * @return openingHours
     * */
    public String getOpeningHours() {
        return openingHours;
    }

    /**
     * setOpeningHours
     * @param openingHours
     * */
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    /**
     * getAddress method to get hospital address
     * @return address
     * */
    public String getAddress() {
        return address;
    }

    /**
     * setAddress method to set hospital address
     * @param address
     * */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * getGeometry to get latitude longitude array
     * return locationlatlng
     * */
    public double[] getLocationlatlng() {
        return locationlatlng;
    }

    /**
     * setGeometry method to set latitude and longitude of location
     * @param latlng
     * */
    public void setLocationlatlng(double[] latlng) {
        this.locationlatlng = latlng;
    }

    /**
     * override method toString to return full details about data
     * @return string
     * */
    @Override
    public String toString() {
        return "NearbyHospitalsDetail{" +
                ", hospitalName='" + hospitalName + '\'' +
                ", rating=" + rating +
                ", openingHours='" + openingHours + '\'' +
                ", address='" + address + '\'' +
                ", geometry=" + Arrays.toString(locationlatlng) +
                '}';
    }
}
