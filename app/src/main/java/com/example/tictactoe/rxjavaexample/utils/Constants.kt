package com.example.tictactoe.rxjavaexample.utils

class Constants {

    companion object{
        var WEATHER_API_KEY = "edc223ecd75045bf97662923190606"

        var BASE_URL = "https://api.apixu.com/v1/current.json?key=edc223ecd75045bf97662923190606&q=Paris"

        var DB_NAME:String = "contacts.db"
        var DB_VERSION = 1;

        //Constants for table and columns
        val CONTACT_ID = "_id"
        val CONTACT_NAME = "contactName"
        val CONTACT_PHONE = "contactPhone"
        val CONTACT_CREATED_ON = "contactCreationTimeStamp"

        val ALL_COLUMNS = arrayOf(CONTACT_ID, CONTACT_NAME, CONTACT_PHONE, CONTACT_CREATED_ON)
    }
}