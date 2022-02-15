package sirchardash.piria.museumtour.services;

import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirchardash.piria.museumtour.components.auth.UserFactory;
import sirchardash.piria.museumtour.components.email.ResetPasswordSender;
import sirchardash.piria.museumtour.components.random.RandomString;
import sirchardash.piria.museumtour.jpa.HourlyUserCount;
import sirchardash.piria.museumtour.jpa.TrackingLog;
import sirchardash.piria.museumtour.jpa.TrackingLogRepository;
import sirchardash.piria.museumtour.models.User;
import sirchardash.piria.museumtour.models.UserCount;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class UserService {

    private final RealmResource keycloak;
    private final UserFactory factory;
    private final RandomString randomString;
    private final ResetPasswordSender email;
    private final TrackingLogRepository trackingLogRepository;

    @Autowired
    public UserService(RealmResource keycloak,
                       UserFactory factory,
                       RandomString randomString, ResetPasswordSender email,
                       TrackingLogRepository trackingLogRepository) {
        this.keycloak = keycloak;
        this.factory = factory;
        this.randomString = randomString;
        this.email = email;
        this.trackingLogRepository = trackingLogRepository;
    }

    public List<User> getAllUsers() {
        return keycloak.users().list().stream()
                .filter(this::isRegularUser)
                .map(factory::toPublicView)
                .collect(Collectors.toList());
    }

    public List<UserCount> activeUsersPerHours() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);
        int currentHour = today.getHour();

        Map<String, List<HourlyUserCount>> userCount = trackingLogRepository.getHourlyUserCount().stream()
                .collect(Collectors.groupingBy(entry -> entry.getHour() + "#" + entry.getDay()));

        return Stream.concat(
                IntStream.range(currentHour, 24)
                        .mapToObj(hour -> userCount.containsKey(hour + "#" + yesterday.getDayOfMonth())
                                ? new UserCount(hour + ":00", userCount.get(hour + "#" + yesterday.getDayOfMonth()).get(0).getUsers())
                                : new UserCount(hour + ":00", 0)),
                IntStream.range(0, currentHour + 1)
                        .mapToObj(hour -> userCount.containsKey(hour + "#" + today.getDayOfMonth())
                                ? new UserCount(hour + ":00", userCount.get(hour + "#" + today.getDayOfMonth()).get(0).getUsers())
                                : new UserCount(hour + ":00", 0))
        ).collect(Collectors.toList());
    }

    public int activeUsers() {
        return trackingLogRepository.getActiveUserCount();
    }

    public List<TrackingLog> logs() {
        return trackingLogRepository.findAll();
    }

    public void resetPassword(String userId) {
        changePassword(userId, true);
    }

    public void disable(String userId) {
        changePassword(userId, false);
    }

    private void changePassword(String userId, boolean temporary) {
        String newPassword = randomString.generate(30);
        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setTemporary(temporary);
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setValue(newPassword);

        UserResource user = keycloak.users().get(userId);
        user.resetPassword(credentials);

        String locale = user.toRepresentation().firstAttribute("locale") != null
                ? user.toRepresentation().firstAttribute("locale")
                : "en";
        if (temporary) {
            email.send(newPassword, locale, user.toRepresentation().getEmail());
        }
    }

    private boolean isRegularUser(UserRepresentation user) {
        List<String> roles = keycloak.users().get(user.getId()).roles().realmLevel().listEffective().stream()
                .map(RoleRepresentation::getName)
                .collect(Collectors.toList());

        return !roles.contains("admin") && roles.contains("user");
    }

}
