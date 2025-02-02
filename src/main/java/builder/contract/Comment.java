package builder.contract;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    private int postId;

    private int id;

    private String name;

    private String email;

    private String body;
}
