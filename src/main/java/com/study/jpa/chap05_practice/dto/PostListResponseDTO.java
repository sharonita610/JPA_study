package com.study.jpa.chap05_practice.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostListResponseDTO {


    private int count; // 총 게시물 수
    private PageResponseDTO pageInfo; // 페이지 렌더링 정보

    // entity 는 dto에서 절대 사용하지 말 것, 차라리 같은 dto를 하나 더 만들어라
    private List<PostDetailResponseDTO> posts; // 게시물 렌더링 정보


}
