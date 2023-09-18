package com.golovackii.overexposure_of_pets.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckFileForPermission {

    public static boolean checkForJPG(String fileName) {
        Pattern patternJPG = Pattern.compile("\\.jpg$");
        Pattern patternJPEG = Pattern.compile("\\.jpeg$");

        Matcher matcherJPG = patternJPG.matcher(fileName.toLowerCase());
        Matcher matcherJPEG = patternJPEG.matcher(fileName.toLowerCase());

        if(matcherJPG.find() || matcherJPEG.find()) {
            return true;
        }
        return false;
    }

}
