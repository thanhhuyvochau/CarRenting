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
public class CarState {

    final public static String NONE_ACTION = "Need to select option to search.";
    final public static String FIELD_NULL = "Need to fill information to search in text box.";
    final public static String DATE_PRESENT_ERROR = "Date need to be more than date of present.";
    final public static String DATE_OUT_IN_INVALID = "Check in must be less than check out.";
    final public static String RENT_SUCCESS = "You have rented successful!";
     final public static String RENT_FAIL = "Your car you want to rent, they was rent before!";
     final public static String PAY_EMPTY = "There are nothing to pay!";
    private String message;

    public CarState() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
