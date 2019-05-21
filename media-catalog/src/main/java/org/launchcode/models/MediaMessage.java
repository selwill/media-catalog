package org.launchcode.models;

import java.util.HashMap;


public class MediaMessage {

    public static HashMap<String, String> getLanguages() {
        HashMap<String, String> languages = new HashMap<>();
        languages.put("fr", "French");
        languages.put("sp", "Spanish");
        languages.put("gr", "German");
        languages.put("it", "Italian");
        languages.put("hi", "Hindi");

        return languages;
    }

    public static String createMessage(String language, String name) {
        String greeting = "Hello";
        switch (language) {
            case "fr":
                greeting = "Bonjour ";
                break;
            case "sp":
                greeting = "Hola ";
                break;
            case "gr":
                greeting = "Guten Tag ";
                break;
            case "it":
                greeting = "Ciao ";
                break;
            case "hi":
                greeting = "Namaste ";
                break;
        }

        return "<h1>" + greeting + name + "! Welcome to the future home of the Untitled Media Catalog site!</h1>";
    }
}
