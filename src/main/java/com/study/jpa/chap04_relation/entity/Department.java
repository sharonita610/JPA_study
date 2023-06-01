package com.study.jpa.chap04_relation.entity;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
// 연관관계에서 양방향 다 제거를 해줘야한다
@ToString(exclude = {"employees"})

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Entity
@Table(name = "tbl_dept")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private long id;

    @Column(name = "dept_name", nullable = false)
    private String name;

    // 양방향 매핑에서는 상대방 엔터티의 갱신에 관여 할 수 없다
    // 단순히 읽기(조회) 전용으로만 사용해야함
    // mappedBy에는 상대방 엔터티의 조인되는 필드명을 써줌
    @OneToMany(mappedBy = "department") // 부서가 하나고 사원이 여러명이라서 one to many
    private List<Employee> employees = new ArrayList<>();

}
