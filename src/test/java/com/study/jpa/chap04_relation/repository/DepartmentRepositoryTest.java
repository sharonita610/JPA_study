package com.study.jpa.chap04_relation.repository;

import com.study.jpa.chap04_relation.entity.Department;
import com.study.jpa.chap04_relation.entity.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class DepartmentRepositoryTest {


    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @DisplayName("특정 부서를 조회하면 해당 부서원들도 함께 조회되어야한다")
    void testFindDept(){

        // given
        Long id = 2L;


        // when
        Department department = departmentRepository.findById(id).orElseThrow();

        // then
        System.out.println("\n\n\n\n\n");
        System.out.println("department = " + department);
        System.out.println("department = " + department.getEmployees());
        System.out.println("\n\n\n\n\n");

    }

    @Test
    @DisplayName("Lazy로딩과 Eager로딩의 차이")
    void testLazyAndEager(){
        // 3번 사원을 조회하고 싶은데 굳이 부서 정보는 필요 없다.
        // given

        Long id = 3L;


        // when
        Employee employee = employeeRepository.findById(id).orElseThrow();


        // then
        System.out.println("\n\n\n\n\n");
//        System.out.println("employee = " + employee);
        System.out.println("employee = " + employee.getDepartment());
        System.out.println("\n\n\n\n\n");
    }



    @Test
    @DisplayName("양방향 연관관계에서 연관데이터의 수정")
    void testChangeDetp(){
        // 3번 사원의 부서를 2번 부서에서 1번 부서로 변경해야한다
        // given

        Long id = 3L;


        // when
        Employee foundEmp = employeeRepository.findById(id).orElseThrow();

        Department newDept = departmentRepository.findById(1L).orElseThrow();

        foundEmp.setDepartment(newDept);

        // 지금은 문제가 없지만 원래는 departmentRepo 쪽에도 넣어줬어야한다.
//        newDept.getEmployees().add(foundEmp);

        employeeRepository.save(foundEmp);

        // then


        // 1번 부서 정보를 조회해서 모든 사원을 보겠다.
        Department foundDept = departmentRepository.findById(1L).orElseThrow();

        System.out.println("\n\n\n\n");
        foundDept.getEmployees().forEach(System.out::println);
        System.out.println("\n\n\n\n");

    }

    @Test
    @DisplayName("N+1 문제 발생 예시")
    void testNPlus1Ex() {

        List<Department> departments = departmentRepository.findAll();

        departments.forEach(dept -> {
            System.out.println("\n\n======= 사원 리스트 =======");

            List<Employee> employees = dept.getEmployees();
            System.out.println(employees);

            System.out.println("\n\n");
        });

    }

    @Test
    @DisplayName("N+1 문제 해결 예시")
    void testNPlus1Solution() {

        List<Department> departments = departmentRepository.findAllIncludeEmployees();

        departments.forEach(dept -> {
            System.out.println("\n\n======= 사원 리스트 =======");

            List<Employee> employees = dept.getEmployees();
            System.out.println(employees);

            System.out.println("\n\n");
        });

    }




}