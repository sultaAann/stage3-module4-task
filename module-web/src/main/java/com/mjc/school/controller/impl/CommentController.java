package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.CommentCommandsController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.CommentCommandsService;
import com.mjc.school.service.dto.CommentDTORequest;
import com.mjc.school.service.dto.CommentDTOResponse;
import com.mjc.school.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController implements BaseController<CommentDTORequest, CommentDTOResponse, Long>, CommentCommandsController<CommentDTOResponse, Long> {
    @Autowired
    private BaseService<CommentDTORequest, CommentDTOResponse, Long> service;

    @Autowired
    private CommentCommandsService<CommentDTOResponse, Long> commentCommandsService;

    @Autowired
    private NewsController newsController;

    @Override
    @GetMapping("/all")
    public List<CommentDTOResponse> readAll() {
        return service.readAll();
    }

    @Override
    @GetMapping("/{id}")
    public CommentDTOResponse readById(@PathVariable Long id) throws AuthorIDException, NewsIDException, TagIDException {
        return service.readById(id);
    }

    @Override
    @PostMapping
    public CommentDTOResponse create(@RequestBody CommentDTORequest createRequest) throws AuthorNameException, AuthorIDException, TagNameException, TitleOrContentLengthException {
        return service.create(createRequest);
    }

    @Override
    @PutMapping
    public CommentDTOResponse update(@RequestBody CommentDTORequest updateRequest) throws AuthorIDException, AuthorNameException, TagNameException, NewsIDException, TagIDException, TitleOrContentLengthException {
        return service.update(updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) throws AuthorIDException, NewsIDException, TagIDException {
        newsController.deleteRelatedNews(id);
        return service.deleteById(id);
    }

    @Override
    public List<CommentDTOResponse> readCommentsByNewsId(Long id) {
        return null;
    }
}
