package sirchardash.piria.museumtour.components.auth;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;
import sirchardash.piria.museumtour.models.User;

@Component
public class UserFactory {

    public User toPublicView(UserRepresentation user) {
        return new User(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

}
