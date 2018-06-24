package com.oba.utilities;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.oba.utilities.*;


/*NOTES
 * 
 * Step 1: Login to your Gmail account and hit this https://www.google.com/settings/security/lesssecureapps
 * Step 2: If Allow less secure apps: is OFF, click the radio button and make it ON.
 */

public class EMailTestResults {
	
	public static String to_Mail, cc_Mail, mail_Body;

	public static void emailResults(String filename) throws Exception {
		Properties props = new Properties();
		 
		System.out.println("Inside EMailTestResults");
		// this will set host of server- you can change based on your requirement 
		props.put("mail.smtp.host", "smtp.gmail.com");
 
		// set the port of socket factory 
		props.put("mail.smtp.socketFactory.port", "465");
 
		// set socket factory
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
 
		// set the authentication to true
		props.put("mail.smtp.auth", "true");
 
		// set the port of SMTP server
		props.put("mail.smtp.port", "465");
 
		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props,
 
				new javax.mail.Authenticator() {
 
					protected PasswordAuthentication getPasswordAuthentication() {
 
					return new PasswordAuthentication("manav.menon.chengalath@gmail.com", "Krishna9239");
 
					}
 
				});
 
		try {
 
			// Create object of MimeMessage class
			Message message = new MimeMessage(session);
 
			// Set the from address
			message.setFrom(new InternetAddress("manav.menon.chengalath@gmail.com"));
			
			getEmailDetails();
 
			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to_Mail));
			message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc_Mail));
//			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("abhilash.tc@gmail.com"));
//			message.setRecipients(Message.RecipientType.CC,InternetAddress.parse("abhilash.tc@hotmail.com"));
//			message.addRecipients(Message.RecipientType.CC,InternetAddress.parse("abhi_menon@yahoo.com"));
            
            // Add the subject link
			message.setSubject("Regression Test Results");
 
			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();
 
			// Set the body of email
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
//			messageBodyPart1.setText("Hello, \n\nAttached the results for Regression Test as on " + timeStamp + ".  \nWarm Regards,\n\nEcoSystem QA\n\n\n*Note :- This is an Automated Email Message. Please don't reply to this EMail.");
			messageBodyPart1.setText(mail_Body);
 
			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
 
			// Mention the file which you want to send
			/*String currentDir = System.getProperty("user.dir");
			String filename = currentDir + "/TestReport/Login_F_QA_06~06~2018_22-49-04.html";
 */
			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);
 
			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));
 
			// set the file
			messageBodyPart2.setFileName(filename);
 
			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();
 
			// add body part 1
			multipart.addBodyPart(messageBodyPart2);
 
			// add body part 2
			multipart.addBodyPart(messageBodyPart1);
 
			// set the content
			message.setContent(multipart);
 
			// finally send the email
			Transport.send(message);
 
			System.out.println("=====Email Sent=====");
			
 
		} catch (MessagingException e) {
 
			throw new RuntimeException(e);
 
		}
 
	}
	
	
	public static void getEmailDetails( ) throws IOException {
		System.out.println("Inside getEmailDetails");
		to_Mail = GeneralUtilities.getValueOf("eMailDetails.properties", "to_Mail").trim();
		cc_Mail = GeneralUtilities.getValueOf("eMailDetails.properties", "cc_Mail").trim();
		mail_Body = GeneralUtilities.getValueOf("eMailDetails.properties", "mail_Body").trim();
		
		System.out.println("to_Mail ->" + to_Mail);
		System.out.println("cc_Mail ->" + cc_Mail);
		System.out.println("mail_Body ->" + mail_Body);
		
		
		
	}
}
