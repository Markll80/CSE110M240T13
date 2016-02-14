package cse110mt13.tritonprofessorraterv1;

import java.util.regex.Pattern;


public class TextParser {

    //e-mail is valid if it fits e-mail pattern and ends with @ucsd.edu or @gmail.ucsd.edu
    public static boolean validEmail(String email) {
        email = email.toLowerCase();
        Pattern EMAIL_ADDRESS_PATTERN = Pattern  //use e-mail
                .compile("[a-zA-Z0-9+._%-+]{1,256}" + "@"
                        + "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" + "(" + "."
                        + "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" + ")+");
        boolean isValid = EMAIL_ADDRESS_PATTERN.matcher(email).matches();
        if(!email.endsWith("@ucsd.edu") || !email.endsWith("@gmail.ucsd.edu"))
            isValid = false;
        return isValid;
    }


    // passwrod requirement: > 6 characters, must have at least one letter and number
    public static boolean validPassword(String password){
        if(password.length() <= 6 )
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


}
