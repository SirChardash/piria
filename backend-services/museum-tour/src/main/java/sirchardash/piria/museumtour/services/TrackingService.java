package sirchardash.piria.museumtour.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirchardash.piria.museumtour.jpa.TrackingLogRepository;
import sirchardash.piria.museumtour.models.tracking.TrackingLog;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class TrackingService {

    private final TrackingLogRepository repository;

    @Autowired
    TrackingService(TrackingLogRepository repository) {
        this.repository = repository;
    }

    public void save(TrackingLog log) {
        repository.save(new sirchardash.piria.museumtour.jpa.TrackingLog(
                        0,
                        log.getUserId(),
                        Timestamp.from(Instant.ofEpochMilli(log.getTimestamp())),
                        log.getCategory(),
                        log.getSubcategory(),
                        log.getLabel(),
                        log.getValue()
                )
        );
    }

}
