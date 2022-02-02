package sirchardash.piria.museumtour.components.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import sirchardash.piria.museumtour.configurations.EmailConfig;

import java.util.Properties;

@Configuration
public class JavaMailSenderDelegate {

    @Bean
    JavaMailSender emailSender(EmailConfig config) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties props = mailSender.getJavaMailProperties();

        mailSender.setHost(config.getHost());
        mailSender.setPort(config.getPort());
        mailSender.setUsername(config.getUsername());
        mailSender.setPassword(config.getPassword());
        props.put("mail.smtp.auth", config.getAuth());
        props.put("mail.smtp.starttls.enable", config.getEnableStartTls());
        props.put("mail.debug", config.getDebug());

        return mailSender;
    }

}
