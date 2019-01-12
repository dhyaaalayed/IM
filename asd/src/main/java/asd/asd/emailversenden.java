package asd.asd;



import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class emailversenden implements JavaDelegate {
	public void execute(DelegateExecution execution) throws Exception {

		String anrede = (String) execution.getVariable("anrede");
		String vorname = (String) execution.getVariable("vorname");
		String nachname = (String) execution.getVariable("nachname");
		String studiengang = (String) execution.getVariable("Studiengang");
		String email = (String) execution.getVariable("email");

		String mailtext = "Sehr geehrte(r) " + anrede + " " + vorname + " " + nachname + ",\n"
				+ "\n  vielen Dank für Ihre Bewerbung durch Uni-Assist.\n "
				+ "\n  Wir müssten leider Ihre Bewerbung für den Studiengang" + studiengang + " stornieren,"
				+ "\n  da weder die Zahlung noch die erforderlichen Unterlagen bei uns nach ablauf der 3-Wochen-Frist eingegangen sind "
				+ "\n\n  Sollte weiter Interesse bestehen, bewrben Sie sich erneut innerhalb der Bewerbungsfrist an."
				+ "\n  Mit freundlichen Grüßen,\n  Ihr Uni-Assist-Team." + "\n_____________________"

				+ "\n Unsere Postadresse:" + "\n uni-assist e.V." + "\n 11507 Berlin" + "\n GERMANY";

		String subject = "hallo Stornierung Ihrer Bewerbung wegen Fristablauf";
		sendEmail(mailtext, subject, email);
	}

	public void sendEmail(String mailtext, String subject, String toEmail) throws EmailException {
		// https://anleitungen.rz.htw-berlin.de/de/email/e-mail_programm/

		MultiPartEmail email = new MultiPartEmail();
		email.setCharset("utf-8");
		email.setSSL(true);
		email.setSmtpPort(587);
		email.setHostName("mail.gmx.net");
		email.setAuthentication("camunda9@gmx.de", "papilon-44");
		email.addTo(toEmail);
		email.setFrom("camunda9@gmx.de");
		email.setSubject(subject);
		email.setMsg(mailtext);
		email.send();

	}


}
