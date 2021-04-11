/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package error;

/**
 *
 * @author ADMIN
 */
public class UserState {

    final public static String VALID_SUCCESS = "Passed";
    final public static String SUCCESS = "Success";
    final public static String USER_ID_FORMAT_ERROR = "Email Is Invalid (Must Less 30 Character And Not Containt Special Character)";
    final public static String PHONE_ERROR = "Phone Number Is Number And From 4 To 12 Digits";
    final public static String ADDRESS_ERROR = "Address Is Not Contain Special Character And From 5 To 50 Char";
    final public static String PASSWORD_FORMAT_ERROR = "Password Not Correct The Format:(a-z)(0-9) From 5-20 Character";
    final public static String USER_NAME_FORMAT_ERROR = "User Name Not Empty Or Not Contains Special Character(1-50)";
    final public static String USER_ID_DUPLICATE = "Email was existed, try another!";
    final public static String LOGIN_FAIL = "Might be user id or password incorect";
    final public static String VERIFY_FAIL = "Verify code is not correct! Try again.";
    final public static String RESEND_FAIL = "Resend verify code fail !";
    final public static String RESEND_SUCCESS = "We sent verify code for you!";
    private String message;

    public UserState() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
