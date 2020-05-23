package com.example.bourbon.activities.clement_activities.model;

import androidx.annotation.Keep;

import java.io.Serializable;
@Keep
public class ListUpload implements Serializable {

    public String customerId ;
    public String shopId;
    public String imageUri;
    public String manuel;

    public String getcustomerId() {
        return customerId;
    }

    public void setcustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getshopId() {
        return shopId;
    }

    public void setshopId(String shopId) {
        this.shopId = shopId;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public ListUpload(String customerId, String shopId, String imageUri,String manuel) {
        this.customerId = customerId;
        this.shopId = shopId;
        this.imageUri = imageUri;
        this.manuel = manuel;
    }

    public String getmanuel() {
        return manuel;
    }

    public void setmanuel(String manuel) {
        this.manuel = manuel;
    }

    public ListUpload(){

    }
}
