package com.study.jpa.chap01_basic.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter @Getter
@ToString @EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tbl_product")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private long id;

    // not null 제약조건 걸기
    @Column(name = "prod_name", nullable = false,  length = 30)
    private String name;

    // default 주고싶을때
    private int price = 0;

    @Enumerated(EnumType.STRING)
    private Category category;

    @CreationTimestamp
    @Column(updatable = false) // 변경 불가하게
    private LocalDateTime createDate;

    @UpdateTimestamp // update문이 발동하면
    private LocalDateTime updatedDate;







    public enum Category{

        FOOD, FASHION, ELECTRONIC
    }

}
