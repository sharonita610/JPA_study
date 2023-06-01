package com.study.jpa.chap05_practice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.jpa.chap05_practice.entity.HashTag;
import com.study.jpa.chap05_practice.entity.Post;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDetailResponseDTO {

    private String title;

    private String author;

    private String content;

    private List<String> hashTags;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime regDate;

    // entity를 DTO 로 반환하는 생성자
    public PostDetailResponseDTO(Post post){
        this.author = post.getWriter();
        this.title = post.getTitle();
        this.regDate = post.getCreateDate();
        this.hashTags = post.getHashTags()
                .stream()
                .map(HashTag::getTagName) // hashtag 의 이름만 꺼내기, mapping
                .collect(toList());
    }
}
