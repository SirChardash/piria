package sirchardash.piria.museumtour.components.email;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import sirchardash.piria.museumtour.configurations.EmailLocalization;

import javax.mail.internet.MimeMessage;

@Component
public class Reminder {

    private final JavaMailSender sender;
    private final EmailLocalization localization;

    @Autowired
    public Reminder(JavaMailSender sender,
                    EmailLocalization localization) {
        this.sender = sender;
        this.localization = localization;
    }

    @SneakyThrows
    public void remind(String locale, String address) {
        EmailLocalization.Template template = localization.get(locale);

        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(template.getReminderBody(), true);
        helper.setTo(address);
        helper.setSubject(template.getReminderTitle());
        helper.setFrom("noreply@bravesmart.com");

        sender.send(mimeMessage);
    }

}
