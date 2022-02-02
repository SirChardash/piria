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
@ConfigurationProperties(prefix = "email.sender")
public class EmailConfig {

    private String host;
    private int port;
    private String username;
    private String password;
    private String protocol;
    private String auth;
    private String enableStartTls;
    private String debug;

}
