package com.zoolife.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    private static SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setIsLogin(Boolean isLogin) {

        prefs.edit().putBoolean("isLogin", isLogin).commit();
    }


    public static String getAuth_token() {
        return prefs.getString("auth_token", "");
    }


    public void setAuthToken(String auth_token) {
        prefs.edit().putString("auth_token", auth_token).commit();
    }


    public void setLogin(boolean login) {
        prefs.edit().putBoolean("isLogin", login).commit();
    }

    public boolean isLogin() {
        boolean isLogin = prefs.getBoolean("isLogin", false);
        return isLogin;
    }
    public void setComplex(boolean isComplex) {
        prefs.edit().putBoolean("isComplex", isComplex).commit();
    }

    public boolean isComplex() {
        boolean isComplex = prefs.getBoolean("isComplex", false);
        return isComplex;
    }
    public void setAppartment(boolean Appartment) {
        prefs.edit().putBoolean("Appartment", Appartment).commit();
    }

    public boolean isAppartment() {
        boolean Appartment = prefs.getBoolean("Appartment", false);
        return Appartment;
    }

    public void setSpouse(boolean spouse) {
        prefs.edit().putBoolean("spouse", spouse).commit();
    }

    public boolean isSpouse() {
        boolean spouse = prefs.getBoolean("spouse", false);
        return spouse;
    }
    public void setAnySpouse(boolean spouse) {
        prefs.edit().putBoolean("anySpouse", spouse).commit();
    }

    public boolean isAnySpouse() {
        boolean spouse = prefs.getBoolean("anySpouse", false);
        return spouse;
    }
    public void setEmergency(boolean emergency) {
        prefs.edit().putBoolean("emergency", emergency).commit();
    }

    public boolean isEmergency() {
        boolean emergency = prefs.getBoolean("emergency", false);
        return emergency;
    }


    public void clear(){
        prefs.edit().clear().commit();
    }


    public void setFirstName(String firstName) {
        prefs.edit().putString("first_name", firstName).commit();
    }
    public String getFirstName() {

        return   prefs.getString("first_name", "");

    }

    public void setMiddleName(String middleName) {
        prefs.edit().putString("middleName", middleName).commit();
    }
    public String getMiddleName() {
        return prefs.getString("middleName", "");
    }

    public void setLastName(String lastName) {
        prefs.edit().putString("lastName", lastName).commit();
    }

    public String getLastName() {
        return prefs.getString("lastName", "");
    }



    public void setPhone(String phone) {
        prefs.edit().putString("phone", phone).commit();
    }

    public String getPhone() {
        return prefs.getString("phone", "");
    }



    public void setImage(String image) {
        prefs.edit().putString("image", image).commit();
    }
    public String getImage() {
        return prefs.getString("image", "");
    }




    public void setAccessKey(String accessKey) {
        prefs.edit().putString("accessKey",accessKey).commit();
    }


    public String getAccessKey() {
        return prefs.getString("accessKey", "");
    }

    public void setComplexId(String complexId) {
        prefs.edit().putString("complexId",complexId).commit();
    }


    public String getComplexId() {
        return prefs.getString("complexId", "");
    }

    public void setAppartmentId(String appartmentId) {
        prefs.edit().putString("appartmentId",appartmentId).commit();
    }


    public String getAppartmentId() {
        return prefs.getString("appartmentId", "");
    }


    public void setName(String name) {
        prefs.edit().putString("name",name).commit();
    }


    public String getName() {
        return prefs.getString("name", "");
    }


    public void setTitle(String title) {
        prefs.edit().putString("title",title).commit();
    }


    public String getTitle() {
        return prefs.getString("title", "");
    }

    public void setEmail(String title) {
        prefs.edit().putString("email",title).commit();
    }


    public String getEmail() {
        return prefs.getString("email", "");
    }


    public void setSurName(String surName) {
        prefs.edit().putString("surName",surName).commit();
    }


    public String getSurName() {
        return prefs.getString("surName", "");
    }

    public void setFullName(String fullName) {
        prefs.edit().putString("fullName",fullName).commit();
    }


    public String getFullName() {
        return prefs.getString("fullName", "");
    }




    public void setUsername(String username) {
        prefs.edit().putString("username",username).commit();
    }


    public String getUsername() {
        return prefs.getString("username", "");
    }


    public void setLanguage(String language) {
        prefs.edit().putString("language",language).commit();
    }


    public String getLanguage() {
        return prefs.getString("language", "");
    }


    public void setUserId(String id) {
        prefs.edit().putString("userId",id).commit();
    }


    public String getUserId() {
        return prefs.getString("userId", "");
    }


    public void setCountryId(String countryId) {
        prefs.edit().putString("CountryId",countryId).commit();
    }


    public String getCountryId() {
        return prefs.getString("countryId", "");
    }

    public void setCityId(String cityId) {
        prefs.edit().putString("cityId",cityId).commit();
    }


    public String getCityId() {
        return prefs.getString("cityId", "");
    }


    public void setCity(String city) {
        prefs.edit().putString("city",city).commit();
    }


    public String getCity() {
        return prefs.getString("city", "");
    }

    public void setCountry(String country) {
        prefs.edit().putString("Country",country).commit();
    }


    public String getCountry() {
        return prefs.getString("country", "");
    }

    public void setYear(String year) {
        prefs.edit().putString("year",year).commit();
    }


    public String getYear() {
        return prefs.getString("year", "");
    }


    public void setMonth(String month) {
        prefs.edit().putString("month",month).commit();
    }


    public String getMonth() {
        return prefs.getString("month", "");
    }

    public void setDay(String day) {
        prefs.edit().putString("day",day).commit();
    }


    public String getDay() {
        return prefs.getString("day", "");
    }

    public boolean isChat() {
        return prefs.getBoolean("isChat", false);
    }

    public void setOnChat(boolean b) {
        prefs.edit().putBoolean("isChat", b).apply();
    }

    public String getChatGroupId() {
        return prefs.getString("chatId", "");
    }

    public void setChatGroupId(String s) {
        if (s == null) s = "";
        prefs.edit().putString("chatId", s).apply();
    }
}