package builder.builder;

import builder.contract.Post;

public class PostBuilder {

    private final Post post = new Post();

    public PostBuilder withId(int id){
        post.setId(id);
        return this;
    }

    public PostBuilder withUserId(int userId){
        post.setUserId(userId);
        return this;
    }

    public PostBuilder withTitle(String title){
        post.setTitle(title);
        return this;
    }

    public PostBuilder withBody(String body){
        post.setBody(body);
        return this;
    }

    public Post build() {
        return post;
    }
}
