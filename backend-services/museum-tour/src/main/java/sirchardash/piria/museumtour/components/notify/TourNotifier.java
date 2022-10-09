package sirchardash.piria.museumtour.components.notify;

import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sirchardash.piria.museumtour.components.email.Reminder;
import sirchardash.piria.museumtour.jpa.VirtualTourAttendance;
import sirchardash.piria.museumtour.jpa.VirtualTourAttendanceRepository;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TourNotifier {

    private final Reminder reminder;
    private final UsersResource usersResource;
    private final VirtualTourAttendanceRepository repository;

    @Autowired
    public TourNotifier(Reminder reminder,
                        RealmResource usersResource,
                        VirtualTourAttendanceRepository repository) {
        this.reminder = reminder;
        this.usersResource = usersResource.users();
        this.repository = repository;
    }

    public void sendNotifications() {
        repository.findAll().stream()
                .filter(TourNotifier::isOneHourBeforeStart)
                .filter(attendance -> !attendance.isNotifiedForStart())
                .peek(this::sendTourStartNotification)
                .forEach(repository::save);

        repository.findAll().stream()
                .filter(TourNotifier::isFiveMinutesBeforeEnd)
                .filter(attendance -> !attendance.isNotifiedForEnd())
                .peek(this::sendTourEndNotification)
                .forEach(repository::save);
    }

    private static boolean isFiveMinutesBeforeEnd(VirtualTourAttendance attendance) {
        long difference = Duration.between(LocalDateTime.now(), attendance.getTour().getEndTime()).getSeconds();
        System.out.println(attendance.getId() + "#" + difference);
        return difference > 0 && difference <= 300;
    }

    private static boolean isOneHourBeforeStart(VirtualTourAttendance attendance) {
        long difference = Duration.between(LocalDateTime.now(), attendance.getTour().getStartTime()).getSeconds();
        return difference > 0 && difference <= 3600;
    }

    private void sendTourStartNotification(VirtualTourAttendance attendance) {
        UserRepresentation user = usersResource.get(attendance.getUserId()).toRepresentation();
        String locale = user.firstAttribute("locale") != null
                ? user.firstAttribute("locale")
                : "en";

        reminder.remindForEnd(locale, user.getEmail());
        attendance.setNotifiedForStart(true);
    }

    private void sendTourEndNotification(VirtualTourAttendance attendance) {
        UserRepresentation user = usersResource.get(attendance.getUserId()).toRepresentation();
        String locale = user.firstAttribute("locale") != null
                ? user.firstAttribute("locale")
                : "en";

        reminder.remindForEnd(locale, user.getEmail());
        attendance.setNotifiedForEnd(true);
    }

}
