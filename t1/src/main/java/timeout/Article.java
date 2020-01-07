package timeout;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Article {

    private final Integer id;
    private final String title;

    public Article(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

}
