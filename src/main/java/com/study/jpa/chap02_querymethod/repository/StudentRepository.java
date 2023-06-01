package com.study.jpa.chap02_querymethod.repository;


import com.study.jpa.chap02_querymethod.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface StudentRepository  extends JpaRepository<Student, String> {


    List<Student> findByName(String name);

    List<Student> findByCityAndMajor(String city, String major);

    List<Student> findByMajorContaining(String major);

    // 네이티브 퀴리도 사용 가능하다
    @Query(value = "select * from tbl_student where stu_name = :nm ", nativeQuery = true)
    Student findNameWithSQL(@Param("nm") String name);

    // JPQL
    // select 별칭 from 엔터티 클래스명 as 별칭
    // where 별칭.필등명 = ?
    // native-sql : SELECT * FROM tbl_student
    //                    WHERE stu_name = ?

    // JPQL : SELECT st FROM Student AS st
    //             WHERE st.name = ?


    // 도시 이름으로 학생 조회
//    @Query(value = "SELECT  * FROM TBL_STUDENT WHERE CITY = ?1", nativeQuery = true)
    @Query("SELECT s FROM Student s where s.city = ?1")
    Student getByCityWithJPQL(String city);



    @Query("select st from Student st where st.name like %:nm%")
    List<Student> searchByNameWithJPQL(@Param("nm") String name);



    // JPQL로 수정 삭제 쿼리
    @Modifying
    @Query("delete from Student s where s.name = ?1")
    void deleteByNameWithJPQL(String name);
}
