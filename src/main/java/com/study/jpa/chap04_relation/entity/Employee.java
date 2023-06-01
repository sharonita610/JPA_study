package com.study.jpa.chap04_relation.entity;

import lombok.*;
import javax.persistence.*;

@Setter
@Getter
// jpa 연관관계 매핑에서는 연관관계 데이터는 ToString 에서 제외해야 한다
@ToString(exclude = {"department"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Entity
@Table(name = "tbl_emp")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private long id;

    @Column(name = "emp_name", nullable = false)
    private String name;

    // Eager : 항상 무조건 조인을 수행
    // Lazy : 필요한 경우에만 조인을 수행 (실무) // 항상 LAZY로 해줘야한다.**
    @ManyToOne(fetch = FetchType.LAZY)// 다대 일 관계
    @JoinColumn(name = "dept_id") // table 의 fk 이름을 적어준다
    private Department department;


}
