package sirchardash.piria.museumtour.components.email;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import sirchardash.piria.museumtour.configurations.EmailLocalization;

import javax.mail.internet.MimeMessage;

@Component
public class ResetPasswordSender {

    private final JavaMailSender sender;
    private final EmailLocalization localization;

    @Autowired
    public ResetPasswordSender(JavaMailSender sender, EmailLocalization localization) {
        this.sender = sender;
        this.localization = localization;
    }

    @SneakyThrows
    public void send(String newPassword, String locale, String address) {
        EmailLocalization.Template template = localization.get(locale);
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(template.getPasswordResetBody().replace("{password}", newPassword), true);
        helper.setTo(address);
        helper.setSubject(template.getPasswordResetTitle());
        helper.setFrom("noreply@bravesmart.com");

        sender.send(mimeMessage);
    }

}
