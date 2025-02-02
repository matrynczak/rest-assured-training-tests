package builder.builder;

import builder.contract.Comment;

public class CommentBuilder {

    private Comment comment = new Comment();

    public CommentBuilder withPostId(int postId) {
        comment.setPostId(postId);
        return  this;
    }

    public CommentBuilder withId(int id) {
        comment.setId(id);
        return this;
    }

    public CommentBuilder withName(String name) {
        comment.setName(name);
        return this;
    }

    public CommentBuilder withEmail(String email) {
        comment.setEmail(email);
        return this;
    }

    public CommentBuilder withBody(String body) {
        comment.setBody(body);
        return this;
    }

    public Comment build() { return comment; }
}
