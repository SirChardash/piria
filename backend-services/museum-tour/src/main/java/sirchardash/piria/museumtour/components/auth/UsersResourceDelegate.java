package sirchardash.piria.museumtour.components.auth;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UsersResourceDelegate {

    @Bean
    UsersResource usersResource(@Value("${user-access.server-url}") String serverUrl,
                                @Value("${user-access.realm}") String realm,
                                @Value("${user-access.username}") String username,
                                @Value("${user-access.password}") String password) {
        Keycloak keycloak = KeycloakBuilder
                .builder()
                .serverUrl(serverUrl)
                .realm("master")
                .username(username)
                .password(password)
                .clientId("admin-cli")
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                .build();

        return keycloak.realm(realm).users();
    }

}
