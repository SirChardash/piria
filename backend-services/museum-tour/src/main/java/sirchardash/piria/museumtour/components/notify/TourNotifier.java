package sirchardash.piria.museumtour.components.notify;

import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sirchardash.piria.museumtour.components.email.Reminder;
import sirchardash.piria.museumtour.jpa.VirtualTourAttendance;
import sirchardash.piria.museumtour.jpa.VirtualTourAttendanceRepository;

@Component
public class TourNotifier {

    private final Reminder reminder;
    private final UsersResource usersResource;
    private final VirtualTourAttendanceRepository repository;

    @Autowired
    public TourNotifier(Reminder reminder,
                        UsersResource usersResource,
                        VirtualTourAttendanceRepository repository) {
        this.reminder = reminder;
        this.usersResource = usersResource;
        this.repository = repository;
    }

    public void sendNotifications() {
        repository.findAll().stream()
                .filter(attendance -> !attendance.isNotified())
                .peek(this::sendNotification)
                .forEach(repository::save);
    }

    private void sendNotification(VirtualTourAttendance attendance) {
        UserRepresentation user = usersResource.get(attendance.getUserId()).toRepresentation();
        String locale = user.firstAttribute("locale") != null
                ? user.firstAttribute("locale")
                : "en";

        reminder.remind(locale, user.getEmail());
        attendance.setNotified(true);
    }

}
