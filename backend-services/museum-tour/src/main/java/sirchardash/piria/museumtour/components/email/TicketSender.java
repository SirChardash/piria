package sirchardash.piria.museumtour.components.email;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import sirchardash.piria.museumtour.configurations.EmailLocalization;

import javax.mail.internet.MimeMessage;

@Component
public class TicketSender {

    private final JavaMailSender sender;
    private final EmailLocalization localization;

    @Autowired
    public TicketSender(JavaMailSender sender,
                        EmailLocalization localization) {
        this.sender = sender;
        this.localization = localization;
    }

    @SneakyThrows
    public void send(String ticketId, String emailAddress, String locale) {
        EmailLocalization.Template template = this.localization.get(locale);

        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(template.getBody().replace("{ticketId}", ticketId), true);
        helper.setTo(emailAddress);
        helper.setSubject(template.getTitle());
        helper.setFrom("noreply@bravesmart.com");
        sender.send(mimeMessage);
    }

}
