/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthconnectjava.services;

import healthconnectjava.GUI.ListCoachForAdminFXMLController;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author wiki
 */
public class SendEmailMailtrap {
    
    private static String Username = "6a28c8ac213dc4";
    private static String Password = "eb3cef54fb119e";
    
    public static void getSendEmail() throws UnknownHostException {
        
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.protokls", "TLSv1.2");
        properties.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        properties.put("mail.smtp.port", "2525");
        
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Username, Password);
            }
        });
        
        session.setDebug(true);
        
        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("HealthConnect@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ListCoachForAdminFXMLController.getCoEmail()));
            message.setSubject("Vérification du compte");
            message.setText("Votre compte a été vérifié avec succès");

            Transport.send(message);
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
}
