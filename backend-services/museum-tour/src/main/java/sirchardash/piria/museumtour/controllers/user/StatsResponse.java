package sirchardash.piria.museumtour.controllers.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sirchardash.piria.museumtour.models.UserCount;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsResponse {

    private List<UserCount> userActivityPastDay;
    private int activeUsers;

}
