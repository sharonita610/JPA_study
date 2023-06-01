package com.study.jpa.chap01_basic.repository;


import com.study.jpa.chap01_basic.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// <entity, pktype>
public interface ProductRepository extends JpaRepository<Product, Long> {



}
