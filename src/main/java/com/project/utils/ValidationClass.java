package com.project.utils;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationClass {

    public static boolean validateTokenIndex(Set<String> token) {
        boolean status = false;
        String regex = "^[a-zA-Z0-9]+$";

        Pattern pattern = Pattern.compile(regex);

        for (String term : token) {
            Matcher matcher = pattern.matcher(term);
            if (matcher.matches()) {
                status = true;
            } else {
                status = false;
                break;
            }
        }
        return status;
    }

    public static boolean validateToken(ArrayList<String> token) {
        boolean status = false;
        String regex = "^[a-zA-Z0-9]+$";

        Pattern pattern = Pattern.compile(regex);

        for (String term : token) {
            Matcher matcher = pattern.matcher(term);
            if (matcher.matches()) {
                status = true;
            } else {
                status = false;
                break;
            }
        }
        return status;
    }

    public static boolean validateID(int id) {
        if (id > 0) {
            return true;
        } else {
            System.out.println("Dont put negative values");
            return false;
        }
    }
}
