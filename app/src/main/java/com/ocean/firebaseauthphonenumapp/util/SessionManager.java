package com.ocean.firebaseauthphonenumapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ocean.firebaseauthphonenumapp.MainActivity;


public class SessionManager {
    SharedPreferences sharedprefernce;
    SharedPreferences.Editor editor;

    Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "sharedcheckLogin";

    private static final String User_Id = "userid";

    private static final String IS_LOGIN = "islogin";

    private static final String Email = "email";
    private static final String userPassword = "password";
    private static final String USERNAME = "username";
    private static final String Photo = "Photo";
    private static final String OtherPhoto = "OtherPhoto";
    private static final String firebasebody = " firebasebody";
    private static final String firebasetitle = " firebasetitle";
    private static final String Date = "date";
    private static final String auth_key = "auth_key";
    private static final String designation = "designation";
    private static final String punchIn = "punchIn";
    private static final String punchOut = "punchOut";
    private static final String punchStatus = "punchStatus";

    private static final String contact = "contact";

    private static final String address = "address";
    private static final String work_status = "work_status";
    private static final String checkInStatus = "checkInStatus";
    private static final String latitude = "latitude";
    private static final String longitude = "longitude";
    private static final String AutoStart = "AutoStart";
    private static final String TimerStatus = "TimerStatus";
    private static final String StartStatus = "StartStatus";
    private static final String EditExpense = "EditExpense";
    private static final String EditNew = "EditNew";
    private static final String offlinePunchTime = "offlinePunchTime";
    private static final String visitList = "visitList";
    private static final String role_type = "role_type";
    private static final String token = "token";
    private static final String internetStart = "internetStart";
    private static final String internetEnd = "internetEnd";
    private static final String punchin_address = "punchin_address";
    private static final String punchout_address = "punchout_address";
    private static final String mainAddExpense = "mainAddExpense";

    public void setSharedprefernce(SharedPreferences sharedprefernce) {
        this.sharedprefernce = sharedprefernce;
    }

    //todo: constructor of session manager class
    public SessionManager(Context context) {

        this.context = context;
        sharedprefernce = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        editor = sharedprefernce.edit();

    }

    //todo: logged in boolean function for false
    public Boolean isLogin() {
        return sharedprefernce.getBoolean(IS_LOGIN, false);

    }

    //todo: logged in boolean function for true
    public void setLogin() {
        editor.putBoolean(IS_LOGIN, true);
        editor.commit();
    }


    public void setUserName(String name) {
        editor.putString(USERNAME, name);
        editor.commit();

    }

    public String getUserName() {
        return sharedprefernce.getString(USERNAME, "DEFAULT");
    }


    public  String getMainAddExpense() {
        return sharedprefernce.getString(mainAddExpense, "DEFAULT");
    }

    public void setMainAddExpense(String mainAddExp) {
        editor.putString(mainAddExpense, mainAddExp);
        editor.commit();

    }

    public  String getInternetStart() {
        return sharedprefernce.getString(internetStart, "DEFAULT");
    }
    public void setInternetStart(String internetStat) {
        editor.putString(internetStart, internetStat);
        editor.commit();

    }

    public String getInternetEnd() {
        return sharedprefernce.getString(internetEnd, "DEFAULT");
    }

    public void setInternetEnd(String interneten) {
        editor.putString(internetEnd, interneten);
        editor.commit();

    }


    public  String getEditExpense() {
        return sharedprefernce.getString(EditExpense, "DEFAULT");
    }

    public void setEditExpense(String editExpense) {
        editor.putString(EditExpense, editExpense);
        editor.commit();

    }

    public  String getEditNew() {
        return sharedprefernce.getString( EditNew, "DEFAULT");
    }

    public void setEditNew(String editNew) {
        editor.putString(EditNew, editNew);
        editor.commit();

    }

    public  String getToken() {
        return sharedprefernce.getString( token, "DEFAULT");
    }

    public void setToken(String tokens) {
        editor.putString(token, tokens);
        editor.commit();

    }

    public String getRole_type() {
        return sharedprefernce.getString(role_type, "");
    }


    public void setRole_type(String role_types) {
        editor.putString(role_type, role_types);
        editor.commit();

    }

    public  String getOfflinePunchTime() {
        return sharedprefernce.getString( offlinePunchTime, "DEFAULT");
    }

    public void setOfflinePunchTime(String offlinePunchTimes) {
        editor.putString(offlinePunchTime, offlinePunchTimes);
        editor.commit();

    }

    public String getAutoStart() {
        return sharedprefernce.getString(AutoStart, "2");
    }

    public void setAutoStart(String autoStat) {
        editor.putString(AutoStart, autoStat);
        editor.commit();

    }

    public String getTimerStatus() {
        return sharedprefernce.getString( TimerStatus, "2");
    }

    public void setTimerStatus(String timerStatus) {
        editor.putString(TimerStatus, timerStatus);
        editor.commit();

    }
    public  String getStartStatus() {
        return sharedprefernce.getString(  StartStatus, "2");
    }
    public void setStartStatus(String startStatus) {
        editor.putString(StartStatus, startStatus);
        editor.commit();

    }

    public String getVisitList() {
        return sharedprefernce.getString(visitList, "No Scheduled Visits");
    }

    public void setVisitList(String visitLists) {
        editor.putString(visitList, visitLists);
        editor.commit();

    }

    public String getPunchin_address() {
        return sharedprefernce.getString(punchin_address, "DEFAULT");
    }

    public void setPunchin_address(String punchin_addres) {
        editor.putString(punchin_address, punchin_addres);
        editor.commit();

    }


    public String getPunchout_address() {
        return sharedprefernce.getString(punchout_address, "DEFAULT");
    }

    public void setPunchout_address(String punchout_addres) {
        editor.putString(punchout_address, punchout_addres);
        editor.commit();

    }

    public String getLatitude() {
        return sharedprefernce.getString(latitude, "DEFAULT");
    }

    public void setLatitude(String latitudes) {
        editor.putString(latitude, latitudes);
        editor.commit();

    }

    public String getLongitude() {
        return sharedprefernce.getString(longitude, "DEFAULT");
    }

    public void setLongitude(String longitudes) {
        editor.putString(longitude, longitudes);
        editor.commit();

    }

    public String getCheckInStatus() {
        return sharedprefernce.getString(checkInStatus, "DEFAULT");
    }


    public void setCheckInStatus(String checkInStatuss) {
        editor.putString(checkInStatus, checkInStatuss);
        editor.commit();

    }

    public String getWork_status() {
        return sharedprefernce.getString(work_status, "DEFAULT");
    }

    public void setWork_status(String work_statuss) {
        editor.putString(work_status, work_statuss);
        editor.commit();

    }

    public String getPunchIn() {
        return sharedprefernce.getString(punchIn, "DEFAULT");
    }

    public void setPunchIn(String punchin) {
        editor.putString(punchIn, punchin);
        editor.commit();

    }


    public String getPunchOut() {
        return sharedprefernce.getString(punchOut, "DEFAULT");
    }

    public void setPunchOut(String punchout) {
        editor.putString(punchOut, punchout);
        editor.commit();

    }

    public String getAddress() {
        return sharedprefernce.getString(address, "DEFAULT");
    }

    public void setAddress(String addrs) {
        editor.putString(address, addrs);
        editor.commit();

    }

    public String getPunchStatus() {
        return sharedprefernce.getString(punchStatus, "DEFAULT");
    }

    public void setPunchStatus(String punchstatus) {
        editor.putString(punchStatus, punchstatus);
        editor.commit();

    }

    public String getDate() {
        return sharedprefernce.getString(Date, "DEFAULT");
    }

    public void setDate(String date) {
        editor.putString(Date, date);
        editor.commit();

    }


    public String getDesignation() {
        return sharedprefernce.getString(designation, "DEFAULT");
    }

    public void setDesignation(String designations) {
        editor.putString(designation, designations);
        editor.commit();

    }

    //Todo: for phone number
    public String getContact() {
        return sharedprefernce.getString(contact, "DEFAULT");
    }

    public void setContact(String contacts) {
        editor.putString(contact, contacts);
        editor.commit();

    }

    public String getAuth_key() {
        return sharedprefernce.getString(auth_key, "DEFAULT");
    }

    public void setAuth_key(String auth_keys) {
        editor.putString(auth_key, auth_keys);
        editor.commit();

    }

    //todo: firebase stuff
    public String getFirebasebody() {
        return sharedprefernce.getString(firebasebody, "DEFAULT");
    }


    public void setFirebasebody(String fbody) {
        editor.putString(firebasebody, fbody);
        editor.commit();

    }

    public String getFirebasetitle() {
        return sharedprefernce.getString(firebasetitle, "DEFAULT");
    }

    public void setFirebasetitle(String ftitle) {
        editor.putString(firebasetitle, ftitle);
        editor.commit();

    }


    public String getEmail() {
        return sharedprefernce.getString(Email, "DEFAULT");
    }

    public void setEmail(String email) {
        editor.putString(Email, email);
        editor.commit();

    }


//    Bill values


    public void setUserID(String id) {

        editor.putString(User_Id, id);
        editor.commit();


    }

    public String getUserID() {

        return sharedprefernce.getString(User_Id, "DEFAULT");
    }
//userPassword

    public void setUserPassword(String userPass) {
        editor.putString(userPassword, userPass);
        editor.commit();

    }


    public String getUserPassword() {
        return sharedprefernce.getString(userPassword, "DEFAULT");
    }

    public String getPhoto() {
        return sharedprefernce.getString(Photo, "DEFAULT");
    }

    public void setPhoto(String photo) {
        editor.putString(Photo, photo);
        editor.commit();

    }

    public  String getOtherPhoto() {
        return sharedprefernce.getString( OtherPhoto, "DEFAULT");
    }

    public void setOtherPhoto(String otherPhoto) {
        editor.putString(OtherPhoto, otherPhoto);
        editor.commit();

    }

    public boolean isFirstTime()
    {
//        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        sharedprefernce = PreferenceManager.getDefaultSharedPreferences(context);
        boolean ranBefore = sharedprefernce.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            editor = sharedprefernce.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }


    //todo: logout user function
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }


}
