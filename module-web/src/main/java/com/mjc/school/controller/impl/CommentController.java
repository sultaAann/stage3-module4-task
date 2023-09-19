package com.mjc.school.controller.impl;

import com.mjc.school.controller.CommentCommandsController;
import com.mjc.school.service.CommentCommandsService;
import com.mjc.school.service.dto.CommentDTORequest;
import com.mjc.school.service.dto.CommentDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController implements CommentCommandsController {

    private final CommentCommandsService service;
    @Autowired
    public CommentController(CommentCommandsService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/all")
    public List<CommentDTOResponse> readAll() {
        return service.readAll();
    }

    @Override
    @GetMapping("/{id}")
    public CommentDTOResponse readById(@PathVariable Long id) {
        return service.readById(id);
    }

    @Override
    @PostMapping
    public CommentDTOResponse create(@RequestBody CommentDTORequest createRequest) {
        return service.create(createRequest);
    }

    @Override
    @PutMapping
    public CommentDTOResponse update(@RequestBody CommentDTORequest updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @Override
    public List<CommentDTOResponse> readCommentsByNewsId(Long id) {
        return service.readCommentsByNewsId(id);
    }
}
