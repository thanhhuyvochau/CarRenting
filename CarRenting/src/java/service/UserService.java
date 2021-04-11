/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDao;
import error.UserState;
import java.util.Random;
import model.User;
import org.apache.log4j.Logger;
import ultis.MailSending;
import validation.UserValidator;

/**
 *
 * @author ADMIN
 */
public class UserService {
 final static Logger log = Logger.getLogger(UserService.class.getName());
    private UserDao userDao;
    private UserValidator valider;

    public UserService() {
        userDao = new UserDao();
        valider = new UserValidator();
    }

    public User login(String email, String password) {

        User user = userDao.authenticateUser(email, password);
        return user;
    }

    public UserState register(User user) {
        UserState state = new UserState();
        state = valider.checkUserForSignUp(user.getEmail(), user.getPhone(), user.getName(), user.getPassword(), user.getAddress());
        if (state.getMessage().equals(UserState.VALID_SUCCESS)) {
            String verifyCode = generateVerifyCode();
            user.setVerifyCode(verifyCode);
            boolean registerResult = userDao.registerUser(user);
            if (registerResult) {
                state.setMessage(UserState.SUCCESS);
                String subjec = "Car Renting";
                String content = "Your verify code is: ";
                MailSending.sendMail(user.getEmail(), content,  subjec+user.getVerifyCode());
            }
        }
        return state;
    }

    public String generateVerifyCode() {
        String code = "";
        Random generator = new Random();
        Integer codeInt = new Integer(0);
        while (codeInt < 100000) {
            codeInt = generator.nextInt(900000) + 100000;
        }
        
        code = codeInt.toString().substring(0, 6);
        return code;
    }

    public UserState verifyUser(String email, String verifyCode) {
        UserState state = new UserState();
        User user = userDao.getUserForVerify(email, verifyCode);
        if (user != null) {
            userDao.verifyUser(email, verifyCode);
            state.setMessage(UserState.SUCCESS);
        } else {
            state.setMessage(UserState.VERIFY_FAIL);
        }
        return state;
    }

    public UserState resendMail(String email) {
         UserState state = new UserState();
        try {         
            User user = userDao.getUserByEmail(email);
            String verifyCode = user.getVerifyCode().trim();
            MailSending.sendMail(user.getEmail().trim(), "Car Renting", "Your verify code is:"+verifyCode);
             state.setMessage(UserState.RESEND_SUCCESS);
        } catch (Exception e) {
            //log
            log.info(e.toString());
            state.setMessage(UserState.RESEND_FAIL);
        }
        return state;
    }
}
