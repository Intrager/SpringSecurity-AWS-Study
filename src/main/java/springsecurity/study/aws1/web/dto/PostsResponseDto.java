package springsecurity.study.aws1.web.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import springsecurity.study.aws1.domain.posts.Posts;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
