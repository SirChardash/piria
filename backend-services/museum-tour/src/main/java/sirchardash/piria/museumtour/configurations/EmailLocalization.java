package sirchardash.piria.museumtour.configurations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "email.localization")
public class EmailLocalization {

    private Template templateEn;
    private Template templateSr;

    public Template get(String locale) {
        return "en".equals(locale)
                ? templateEn
                : "sr".equals(locale)
                ? templateSr
                : null;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Template {

        private String purchaseTitle;
        private String purchaseBody;
        private String reminderTitle;
        private String reminderBody;
        private String passwordResetTitle;
        private String passwordResetBody;

    }

}
