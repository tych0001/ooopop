package com.example.demo.controller;



import javax.validation.Valid;

import com.example.demo.entity.Blog;
import com.example.demo.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/blogs/")
public class BlogController {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GetMapping("create")
    public String showSignUpForm(Blog blog) {
        return "addBlog";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("blogs", blogRepository.findAll());
        return "Home";
    }

    @PostMapping("add")
    public String addBlog(@Valid Blog blog, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addBlog";
        }

        blogRepository.save(blog);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("blog", blog);
        return "updateBlog";
    }

    @PostMapping("update/{id}")
    public String updateBlog(@PathVariable("id") long id, @Valid Blog blog, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            blog.setId(id);
            return "updateBlog";
        }

        blogRepository.save(blog);
        model.addAttribute("blogs", blogRepository.findAll());
        return "Home";
    }

    @GetMapping("delete/{id}")
    public String deleteBlog(@PathVariable("id") long id, Model model) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid blog Id:" + id));
        blogRepository.delete(blog);
        model.addAttribute("Blog", blogRepository.findAll());
        return "Home";
    }


    @GetMapping("userlist")
    public String showuser(Model model) {
        model.addAttribute("blogs", blogRepository.findAll());
        return "homeuser";
    }
}