package healthconnectjava.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.mail.*;

import java.util.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import java.net.Authenticator;
//import java.net.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author LENOVO
 */
public class Emailsender_2 {

    private static final String USERNAME = "youssef.benlahouel@esprit.tn";
    private static final String PASSWORD = "223AMT1847";

    public static void sendEmail_add(String toEmail,String message ) {

        String subject = "Paiement avec suuces  ";


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session;
        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(USERNAME));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            emailMessage.setSubject(subject);
            emailMessage.setText(message);
            Transport.send(emailMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendEmail_mod(String toEmail,String subject) {

        //String subject = "Trippie modify Cov_voiturage ";
        String message = "confirmation email";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session;
        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(USERNAME));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            emailMessage.setSubject(subject);
            emailMessage.setText(message);

            Transport.send(emailMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}