package builder.contract;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {

    private int id;

    private String title;

    private String body;

    private int userId;
}
