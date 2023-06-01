package com.study.jpa.chap03_pagination.repository;

import com.study.jpa.chap02_querymethod.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentPageRepository extends JpaRepository<Student, String> {

    // 학생 조건없이 전체조회 페이징(기본기능이라 안만들어도 됨)
    Page<Student> findAll(Pageable pageable);

    // 학생의 이름에 특정당어가 포함된걸 조회 + 페이징(이건 만들어야됨)
    // 파라미터 갯수가 몇개가 됐든 페이저블은 맨 마지막에 넣어야한다.
    Page<Student> findByNameContaining(String name, Pageable pageable);






}
