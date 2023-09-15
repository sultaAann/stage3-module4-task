package com.mjc.school.controller.impl;

import com.mjc.school.controller.AuthorCommandsController;
import com.mjc.school.controller.BaseController;
import com.mjc.school.service.AuthorCommandsService;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;
import com.mjc.school.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController implements BaseController<AuthorDTORequest, AuthorDTOResponse, Long>, AuthorCommandsController<AuthorDTOResponse, Long> {
    @Autowired
    private BaseService<AuthorDTORequest, AuthorDTOResponse, Long> service;

    @Autowired
    private AuthorCommandsService<AuthorDTOResponse, Long> authorCommandsService;

    @Autowired
    private NewsController newsController;

    @Override
    @GetMapping("/all")
    public List<AuthorDTOResponse> readAll() {
        return service.readAll();
    }

    @Override
    @GetMapping("/{id}")
    public AuthorDTOResponse readById(@PathVariable Long id) throws AuthorIDException, NewsIDException, TagIDException {
        return service.readById(id);
    }

    @Override
    @PostMapping
    public AuthorDTOResponse create(@RequestBody AuthorDTORequest createRequest) throws AuthorNameException, AuthorIDException, TagNameException, TitleOrContentLengthException {
        return service.create(createRequest);
    }

    @Override
    @PutMapping
    public AuthorDTOResponse update(@RequestBody AuthorDTORequest updateRequest) throws AuthorIDException, AuthorNameException, TagNameException, NewsIDException, TagIDException, TitleOrContentLengthException {
        return service.update(updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) throws AuthorIDException, NewsIDException, TagIDException {
        newsController.deleteRelatedNews(id);
        return service.deleteById(id);
    }

    @Override
    public List<AuthorDTOResponse> readAuthorByNewsId(Long id) {
        authorCommandsService.readAuthorByNewsId(id).forEach(System.out::println);
        return authorCommandsService.readAuthorByNewsId(id);
    }
}
