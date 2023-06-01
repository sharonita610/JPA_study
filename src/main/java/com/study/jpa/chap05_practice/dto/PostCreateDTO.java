package com.study.jpa.chap05_practice.dto;

import com.study.jpa.chap05_practice.entity.Post;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class PostCreateDTO {

    @NotBlank
    @Size(min= 2, max = 5)
    private String writer;

    @NotBlank
    @Size(min= 1, max = 20)
    private String title;

    private String content;

    private List<String> hashTags;

    //dto를 entity로
    public Post toEntity(){
        return Post.builder()
                .writer(this.writer)
                .content(this.content)
                .title(this.title)
                .build();
    }



}
