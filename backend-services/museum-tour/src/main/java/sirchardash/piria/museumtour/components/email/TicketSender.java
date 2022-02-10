package sirchardash.piria.museumtour.components.email;

import lombok.SneakyThrows;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import sirchardash.piria.museumtour.configurations.EmailLocalization;
import sirchardash.piria.museumtour.jpa.Museum;
import sirchardash.piria.museumtour.jpa.VirtualTour;
import sirchardash.piria.museumtour.jpa.VirtualTourAttendance;

import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

@Component
public class TicketSender {

    private final JavaMailSender sender;
    private final EmailLocalization localization;
    private final TicketGenerator ticketGenerator;

    @Autowired
    public TicketSender(JavaMailSender sender,
                        EmailLocalization localization,
                        TicketGenerator ticketGenerator) {
        this.sender = sender;
        this.localization = localization;
        this.ticketGenerator = ticketGenerator;
    }

    @SneakyThrows
    public void send(String locale,
                     AccessToken user,
                     Museum museum,
                     VirtualTour tour,
                     VirtualTourAttendance attendance) {
        ByteArrayDataSource ticket = new ByteArrayDataSource(ticketGenerator.generatePdf(
                locale,
                user,
                museum,
                tour,
                attendance
        ), "application/pdf");
        EmailLocalization.Template template = this.localization.get(locale);

        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        helper.setText(template.getBody(), true);
        helper.addAttachment("ticket.pdf", ticket);
        helper.setTo(user.getEmail());
        helper.setSubject(template.getTitle());
        helper.setFrom("noreply@bravesmart.com");

        sender.send(mimeMessage);
    }

}
