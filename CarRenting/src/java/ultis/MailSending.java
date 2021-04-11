/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultis;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ADMIN
 */
public class MailSending {
 static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MailSending.class.getName()); 
    public static void sendMail(String recepient,String subject,String content) {
        Properties propertie = new Properties();
        propertie.put("mail.smtp.auth", "true");
        propertie.put("mail.smtp.starttls.enable", "true");
        propertie.put("mail.smtp.host", "smtp.gmail.com");
        propertie.put("mail.smtp.port", "587");
        propertie.put("mail.smtp.user","acygfam2008@gmail.com");
        final String myEmail = "acygfam2008@gmail.com";
        final String password = "010203Huy";

        Session session;
        session = Session.getDefaultInstance(propertie, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, password);
            }

        });
        Message message = prepareMessage(session,myEmail, recepient,subject, content);
        try {
            Transport.send(message);
            System.out.println("Send Successful");
        } catch (MessagingException ex) {
          log.info(ex.toString());
        }
    }

    private static Message prepareMessage(Session session,String myEmail,String recepient,String subject,String content) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
            message.setSubject(subject);
             message.setText(content);
             return message;
        } catch (Exception e) {
             log.info(e.toString());
        }
        return null;
    }
    
}
