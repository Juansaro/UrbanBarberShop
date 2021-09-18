/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.utilidades;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.barber.model.Usuario;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public abstract class envioMasivoServicios {

    public static void recuperarCliente(List<String> emailToAddresses) {

        // from email address
        final String username = "senaland066@gmail.com";

        // make sure you put your correct password
        final String password = "sennaland 432";

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        // authentcate using your email and password and on successful
        // create the session
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        String emails = null;

        try {
            // create new message
            Message message = new MimeMessage(session);

            // set the from 'email address'
            message.setFrom(new InternetAddress(username));

            // set email subject
            message.setSubject(emailToAddresses.get(0));

            // set email message
            // this will send html mail to the intended recipients
            // if you do not want to send html mail then you do not need to wrap the message
            // inside html tags
            String content = "<html>\n<body>\n";
            content += "";
            content += "<br/>";
            content += "<h1> Hola, "+ emailToAddresses +" </h1>";
            content += "<br/>";
            content += "Bienvenido a Urban barber shop!";
            content += "<br/>";
            content += "Donde los hombres se vuelven hombres.";
            content += "\n";
            content += "</body>\n</html>";
            message.setContent(content, "text/html");

            // form all emails in a comma separated string
            StringBuilder sb = new StringBuilder();

            int i = 0;
            for (String email : emailToAddresses) {
                sb.append(email);
                i++;
                if (emailToAddresses.size() > i) {
                    sb.append(", ");
                }
            }

            emails = sb.toString();

            // set 'to email address'
            // you can set also CC or TO for recipient type
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(sb.toString()));

            //System.out.println("Sending Email to " + emails + " from " + username + " with Subject - " + emailToAddresses.toString());

            // send the email
            Transport.send(message);

            //System.out.println("Email successfully sent to " + emails);
        } catch (MessagingException e) {
            System.out.println("Email sending failed to " + emails);
            System.out.println(e);
        }
    }

}
