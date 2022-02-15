package sirchardash.piria.museumtour.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrackingLogRepository extends JpaRepository<TrackingLog, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT count(DISTINCT (user_id)) " +
                    "FROM tracking_logs " +
                    "WHERE time > NOW() - INTERVAL 5 MINUTE")
    int getActiveUserCount();

    @Query(nativeQuery = true,
            value = "SELECT HOUR(time) AS hour, DAY(time) AS day, count(distinct (user_id)) AS users " +
                    "FROM tracking_logs " +
                    "WHERE time > NOW() - INTERVAL 1 DAY " +
                    "GROUP BY hour, day")
    List<HourlyUserCount> getHourlyUserCount();

}
