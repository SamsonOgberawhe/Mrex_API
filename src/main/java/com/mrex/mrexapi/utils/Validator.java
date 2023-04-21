package com.mrex.mrexapi.utils;

public class Validator {
    public static boolean isEmailValid(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.matches("^\\+?[0-9. ()-]{10,25}$");
    }

    public static boolean isUsernameValid(String username) {
        return username.matches("^[a-zA-Z0-9._-]{3,}$");
    }

    public static boolean isPasswordValid(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }
}
