package it.tesoro.monprovv.service;


import it.tesoro.monprovv.dto.Mail;
import it.tesoro.monprovv.exception.MailException;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("mailService")
public class MailService {

	private static Logger logger = Logger.getLogger(MailService.class);

	@Value("#{config['email.on']}")
	private String emailSwitch;

	@Value("#{config['email.smtp.server']}")
	private String smtpServer;

	@Value("#{config['email.smtp.port']}")
	private String smtpPort;

	@Value("#{config['email.smtp.user']}")
	private String smtpUser;

	@Value("#{config['email.smtp.pwd']}")
	private String smtpPassword;

	@Value("#{config['email.from.address']}")
	private String emailFromAddress;

	@Value("#{config['email.from.name']}")
	private String emailFromName;
	
	@Value("#{config['email.smtp.authreq']}")
	private String smtpAuthReq;

	public void eseguiInvioMail(Mail mail) throws MailException {

		try {
			if (emailSwitch.equalsIgnoreCase("S")) {
				Properties props = new Properties();

				props.put("mail.smtp.auth", smtpAuthReq);
				props.put("mail.smtp.ssl.enable", false);
				props.put("mail.smtp.starttls.enable", false);
				
				if (mail.isHtmlFormat()) {
					sendEmailHtml(mail,props);
				} else {
					sendEmail(mail,props);
				}
			} else {
				logger.debug("Funzione Invio mail disattivata");
			}
		} catch (Exception e) {
			logger.error(e);
			throw new MailException(e);
		}
		
	}

	private void sendEmail(Mail mail, Properties props) throws MessagingException, UnsupportedEncodingException {

		Session session = Session.getInstance(props, null);
		session.setDebug(true);
		MimeMessage msg = new MimeMessage(session);
		msg.setText(mail.getContent());
		msg.setSubject(mail.getSubject());
		msg.setFrom(new InternetAddress(emailFromAddress, emailFromName));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getDestinatario()));
		msg.saveChanges();
		
		// invio 
		Transport transport = session.getTransport("smtp");
		transport.connect(smtpServer, Integer.parseInt(smtpPort), smtpUser, smtpPassword);
        transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
        transport.close();
	}

	private void sendEmailHtml(Mail mail, Properties props) throws MessagingException, UnsupportedEncodingException {

		Session session = Session.getInstance(props, null);
		MimeMessage msg = new MimeMessage(session);
		msg.setSubject(mail.getSubject());
		msg.setFrom(new InternetAddress(emailFromAddress, emailFromName));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getDestinatario()));
		String html = mail.getContent().toString();
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(html, "text/html; charset=utf-8");
		MimeMultipart multipart = new MimeMultipart("related");
		multipart.addBodyPart(messageBodyPart);
		msg.setContent(multipart);
		msg.saveChanges();
		
		// invio 
		Transport transport = session.getTransport("smtp");
		transport.connect(smtpServer, Integer.parseInt(smtpPort), smtpUser, smtpPassword);
		transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
        transport.close();
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getSmtpUser() {
		return smtpUser;
	}

	public void setSmtpUser(String smtpUser) {
		this.smtpUser = smtpUser;
	}

	public String getSmtpPassword() {
		return smtpPassword;
	}

	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	public String getSmtpAuthReq() {
		return smtpAuthReq;
	}

	public void setSmtpAuthReq(String smtpAuthReq) {
		this.smtpAuthReq = smtpAuthReq;
	}
}
