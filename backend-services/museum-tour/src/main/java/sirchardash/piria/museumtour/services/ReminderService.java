package sirchardash.piria.museumtour.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sirchardash.piria.museumtour.components.notify.TourNotifier;

@Service
class ReminderService {

    private final TourNotifier tourNotifier;

    @Autowired
    public ReminderService(TourNotifier tourNotifier) {
        this.tourNotifier = tourNotifier;
    }

    @Scheduled(cron = "0 */5 * * * *")
    void notifyOneHourBefore() {
        tourNotifier.sendNotifications();
    }

}
