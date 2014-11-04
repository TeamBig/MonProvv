package it.tesoro.monprovv.dto;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator{
	
	
	private PasswordAuthentication passwordAutentication = null;

	public MailAuthenticator(String user, String password) {
		passwordAutentication = new PasswordAuthentication(user, password);
	}

	public PasswordAuthentication getPasswordAutentication() {
		return passwordAutentication;
	}
}
