package cse110mt13.tritonprofessorraterv1;

import android.util.Log;

import java.util.regex.Pattern;


public class TextParser {

    //e-mail is valid if it fits e-mail pattern and ends with @ucsd.edu or @gmail.ucsd.edu
    public static boolean validEmail(String email) {
        if(email.isEmpty())
            return false;
        email = email.toLowerCase();
        Pattern EMAIL_ADDRESS_PATTERN = Pattern  //use e-mail
                .compile("[a-zA-Z0-9+._%-+]{1,256}" + "@"
                        + "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" + "(" + "."
                        + "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" + ")+");
        boolean isValid = EMAIL_ADDRESS_PATTERN.matcher(email).matches();
        if(!email.endsWith("@ucsd.edu") || !email.endsWith("@gmail.ucsd.edu")) {
            isValid = false;
            Log.d("Login", "email doesn't end with ucsd.edu");
        }
        return isValid;
    }


    // passwrod requirement: > 6 and < 16 characters, must have at least one letter and number
    public static boolean validPassword(String password){
        if(password.length() <= 6 || password.length() >=16 || password.isEmpty() )
            return false;
        boolean containsLetter = false;
        boolean containsNumber = false;
        password = password.toLowerCase();
        for(int i =0; i<password.length(); ++i){
            if(password.charAt(i)>= 48 || password.charAt(i) <= 57){
                containsNumber = true;
                break;
            }
        }
        for(int i =0; i<password.length(); ++i){
            if(password.charAt(i)>97 || password.charAt(i) <= 122){
                containsLetter = true;
                break;
            }
        }
        return (containsLetter && containsNumber);
    }


    //use this method to convert any prof/course names to proper capitalization
    public static String convertToUpperCase(String lowerCase){
        String convertedName;
        if(lowerCase.matches(".*\\d.*")){
            convertedName = lowerCase.toUpperCase();
        }
        else{
            String[] words = lowerCase.split(" ");
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < words.length; i++){
                builder.append(Character.toUpperCase(words[i].charAt(0)));
                builder.append(words[i].substring(1));
                if(i < words.length - 1){
                    builder.append(' ');
                }
            }
            convertedName = builder.toString();
        }
        return convertedName;
    }
}
