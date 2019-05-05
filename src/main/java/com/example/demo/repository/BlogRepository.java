package com.example.demo.repository;


import java.util.List;

import com.example.demo.entity.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BlogRepository extends CrudRepository <Blog, Long> {
    List<Blog> findByTitle(String title);
}