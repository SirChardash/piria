package sirchardash.piria.museumtour.components.email;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class TicketSender {

    private final JavaMailSender sender;

    @Autowired
    public TicketSender(JavaMailSender sender) {
        this.sender = sender;
    }

    @SneakyThrows
    public void send(String ticketId, String emailAddress, String locale) {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText("Asaaaaaa! " + ticketId, true);
        helper.setTo(emailAddress);
        helper.setSubject("Your ticket 'ere");
        helper.setFrom("no-reply@joineureka.com");
        sender.send(mimeMessage);
    }

}
