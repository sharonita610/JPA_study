package com.study.jpa.chap05_practice.dto;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor

public class PageDTO {

    private int page;

    private int size;

    public PageDTO(){
        this.page = 1;
        this.size = 10;
    }
}
