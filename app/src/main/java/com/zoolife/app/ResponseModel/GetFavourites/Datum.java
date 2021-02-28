package com.zoolife.app.ResponseModel.GetFavourites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

@SerializedName("id")
@Expose
private String id;
@SerializedName("itemId")
@Expose
private String itemId;
@SerializedName("userId")
@Expose
private String userId;
@SerializedName("co")
@Expose
private String co;
@SerializedName("uo")
@Expose
private String uo;
@SerializedName("itemTitle")
@Expose
private String itemTitle;
@SerializedName("itemDesc")
@Expose
private String itemDesc;
@SerializedName("country")
@Expose
private String country;
@SerializedName("city")
@Expose
private String city;
@SerializedName("area")
@Expose
private String area;
@SerializedName("lat")
@Expose
private String lat;
@SerializedName("lng")
@Expose
private String lng;
@SerializedName("imgUrl")
@Expose
private String itemImage;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getItemId() {
return itemId;
}

public void setItemId(String itemId) {
this.itemId = itemId;
}

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getCo() {
return co;
}

public void setCo(String co) {
this.co = co;
}

public String getUo() {
return uo;
}

public void setUo(String uo) {
this.uo = uo;
}

public String getItemTitle() {
return itemTitle;
}

public void setItemTitle(String itemTitle) {
this.itemTitle = itemTitle;
}

public String getItemDesc() {
return itemDesc;
}

public void setItemDesc(String itemDesc) {
this.itemDesc = itemDesc;
}

public String getCountry() {
return country;
}

public void setCountry(String country) {
this.country = country;
}

public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

public String getArea() {
return area;
}

public void setArea(String area) {
this.area = area;
}

public String getLat() {
return lat;
}

public void setLat(String lat) {
this.lat = lat;
}

public String getLng() {
return lng;
}

public void setLng(String lng) {
this.lng = lng;
}

public String getItemImage() {
return itemImage;
}

public void setItemImage(String itemImage) {
this.itemImage = itemImage;
}

}