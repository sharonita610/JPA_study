package com.study.jpa.chap01_basic.repository;

import com.study.jpa.chap01_basic.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.study.jpa.chap01_basic.entity.Product.Category.ELECTRONIC;
import static com.study.jpa.chap01_basic.entity.Product.Category.FASHION;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // test 처리후 롤백
//@Rollback(false)
class ProductRepositoryTest {


    @Autowired
    ProductRepository productRepository;

    @BeforeEach
        // 테스트 돌리기 전에 실행
    void insertDummyData() {
        //given
        Product p1 = Product.builder()
                .name("아이폰")
                .category(ELECTRONIC)
                .price(10000000)
                .build();
        Product p2 = Product.builder()
                .name("탕수육")
                .category(Product.Category.FOOD)
                .price(20000)
                .build();
        Product p3 = Product.builder()
                .name("구두")
                .category(Product.Category.FASHION)
                .price(300000)
                .build();
        Product p4 = Product.builder()
                .name("쓰레기")
                .category(Product.Category.FOOD)
                .build();

        // when
        Product save1 = productRepository.save(p1);
        Product save2 = productRepository.save(p2);
        Product save3 = productRepository.save(p3);
        Product save4 = productRepository.save(p4);

    }

    @Test
    @DisplayName("상품 4개를 데이터베이스에 저장해야 한다")
    void testSave() {
        //given
        Product product = Product.builder()
                .name("정장")
                .category(FASHION)
                .price(120000)
                .build();


        // when

        Product saved = productRepository.save(product);

        // then
        assertNotNull(saved);

    }

    @Test
    @DisplayName("id가 2번인 데이터를 삭제한다")
    void testRemove() {

        long id = 2L;
        productRepository.deleteById(id);


    }

    @Test
    @DisplayName("상품 전체 조회를 하면 4개가 나온다")
    void testFindAll() {

        List<Product> productList = productRepository.findAll();

        productList.forEach(System.out::println);

        assertEquals(4, productList.size());


    }

    @Test
    @DisplayName("3번 상품을 조회하면 상품명이 '구두' 여야한다")
    void testFindOne() {

        // given
        long id = 3L;

        // when
        Optional<Product> product = productRepository.findById(id);

        // then
        product.ifPresent(p -> {
            assertEquals("구두", p.getName());
        });


        Product product1 = product.get();
        assertNotNull(product1);

        System.out.println("product1 = " + product1);
    }

    @Test
    @DisplayName("2번 상품의 이름고 가격을 변경해야한다.")
    void testModify() {

        // given
        long id = 2L;
        String newName = "짜장면";
        int newPrice = 6000;

        // when
        // jpa에서 update는 따로 메서드를 지원하지 않고
        // 조회 한 후 setter 로 변경하면 자동  update 된다.

        // 객체스럽게 db를 다룸
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(p->{
            p.setName(newName);
            p.setPrice(newPrice);

            productRepository.save(p);
        });

        // then
        assertTrue(product.isPresent());

        Product p = product.get();
        assertEquals("짜장면", p.getName());
    }


}