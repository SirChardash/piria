package sirchardash.piria.museumtour.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirchardash.piria.museumtour.jpa.TrackingLogRepository;
import sirchardash.piria.museumtour.models.tracking.TrackingLog;

@Service
public class TrackingService {

    private final TrackingLogRepository repository;

    @Autowired
    TrackingService(TrackingLogRepository repository) {
        this.repository = repository;
    }

    public void saveTrackingLogs(Collection<TrackingLog> trackingLog) {
        repository.saveAll(trackingLog.stream().map(log -> new sirchardash.piria.museumtour.jpa.TrackingLog(
                        0,
                        Timestamp.from(Instant.ofEpochMilli(log.getTimestamp())),
                        log.getCategory(),
                        log.getSubcategory(),
                        log.getLabel(),
                        log.getValue()
                )
        ).collect(Collectors.toList()));

        repository.flush();
    }

}
