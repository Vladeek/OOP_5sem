package com.example.lab6;

import java.io.Serializable;

public class Application implements Serializable {

    private String appName;
    private String version;
    private String devName;
    private String appType;
    private String pubName;
    private float appRate;
    private String mail;
    private String number;

    public Application(String appName, String version, String devName, String appType, String pubName, float appRate, String mail, String number) {
        this.appName = appName;
        this.version = version;
        this.devName = devName;
        this.appType = appType;
        this.pubName = pubName;
        this.appRate = appRate;
        this.mail = mail;
        this.number = number;
    }

    public Application() {
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public float getAppRate() {
        return appRate;
    }

    public void setAppRate(float appRate) {
        this.appRate = appRate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String pubName) {
        this.number = pubName;
    }

    @Override
    public String toString() {
        return
                "Название приложения: " + appName + "\n" +
                "Версия: " + version + "\n" +
                "Разработчик: " + devName + "\n" +
                "Тип приложение: " + appType + "\n" +
                "Издатель: " + pubName + "\n" +
                "Email: " + mail + "\n" +
                "Номер: " + number + "\n" +
                "Рейтинг: " + appRate + "\n\n";
    }
}
