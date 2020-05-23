package com.example.bourbon.activities.clement_activities.model;

public class ProductDetails {
    private String districtName,emergencyNo,landLineNo;

    void init(){
        districtName="";
        emergencyNo="";
        landLineNo="";
    }

    public ProductDetails(String districtName,String emergencyNo,String landLineNo){

        this.districtName=districtName;
        this.emergencyNo=emergencyNo;
        this.landLineNo=landLineNo;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getEmergencyNo() {
        return emergencyNo;
    }

    public void setEmergencyNo(String emergencyNo) {
        this.emergencyNo = emergencyNo;
    }

    public String getLandLineNo() {
        return landLineNo;
    }

    public void setLandLineNo(String landLineNo) {
        this.landLineNo = landLineNo;
    }
}
