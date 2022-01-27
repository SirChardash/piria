package sirchardash.piria.museumtour.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedEntry {

    private String title;
    private String description;
    private String imageUrl;
    private String articleUrl;

}
