/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import dao.UserDao;
import error.UserState;
import model.User;

/**
 *
 * @author ADMIN
 */
public class UserValidator extends AbstractValidtor {

    UserState error;

    public UserValidator() {
        error = new UserState();
    }

    public boolean validUserEmail(String email) {
        boolean result = false;
        String pattern = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        result = valid(pattern,email );
        if (result == true) {
            result = email.length() <= 30;
        }
        return result;
    }

    public boolean validUserName(String userName) {
        String pattern = "[a-zA-Z ]{1,30}";
        return valid(pattern, userName);
    }

    public boolean validPassword(String password) {
        String pattern = "[a-zA-Z0-9]{5,20}";
        return valid(pattern, password);
    }
    public boolean validPhoneNumber(String phoneNum) {
        String pattern = "[0-9]{4,12}";
        return valid(pattern, phoneNum);
    }
    public boolean validAddress(String address) {
        String pattern = "[a-zA-Z0-9 ]{5,50}";
        return valid(pattern, address);
    }

    

    public boolean checkExisted(String email) {
        UserDao userDao = new UserDao();
        boolean existed = false;//false la khong ton tai
        User user = userDao.getUserByEmail(email);
        if (user != null) {
            existed = true;
        }
        return existed;
    }

    public UserState checkUserForLogin(String email, String password) {
        if (!validUserEmail(email)) {
            error.setMessage(UserState.USER_ID_FORMAT_ERROR);
        } else if (!validPassword(password)) {
            error.setMessage(UserState.PASSWORD_FORMAT_ERROR);
        } else {
            error.setMessage(UserState.VALID_SUCCESS);
        }
        return error;
    }

    public UserState checkUserForSignUp(String email, String phone, String name,String password,String address) {
        if (!validUserEmail(email)) {
            error.setMessage(UserState.USER_ID_FORMAT_ERROR);
        } else if (!validUserName(name)) {
            error.setMessage(UserState.USER_NAME_FORMAT_ERROR);
        } else if (!validPassword(password)) {
            error.setMessage(UserState.PASSWORD_FORMAT_ERROR);
        }else if (!validPhoneNumber(phone)) {
            error.setMessage(UserState.PHONE_ERROR);
        }else if (!validAddress(address)) {
            error.setMessage(UserState.ADDRESS_ERROR);
        } else if (checkExisted(email)) {
            error.setMessage(UserState.USER_ID_DUPLICATE);
        } else {
            error.setMessage(UserState.VALID_SUCCESS);
        }
        return error;
    }
   
   
}
