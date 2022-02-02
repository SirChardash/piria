package sirchardash.piria.museumtour.components.auth;

import org.keycloak.TokenVerifier;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Component;

@Component
public class TokenParser {

    public AccessToken fromHeader(String authorizationHeader) throws VerificationException {
        TokenVerifier<AccessToken> verifier = TokenVerifier.create(
                authorizationHeader.replace("Bearer ", ""),
                AccessToken.class
        );

        return verifier.getToken();
    }

}
