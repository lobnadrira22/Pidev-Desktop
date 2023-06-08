
        package healthconnectjava.services;

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
 * @author user
 */
        public class EmailSender_1 {



        private static final String USERNAME = "oubeidallah.hanini@esprit.tn";
        private static final String PASSWORD = "223AMT2702";

        public static void sendEmail_add(String toEmail,String message ) {

        String subject = "Health Connect ";


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // ajout de cette ligne pour résoudre les problèmes de certification

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