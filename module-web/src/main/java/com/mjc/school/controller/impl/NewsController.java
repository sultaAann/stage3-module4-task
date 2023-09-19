package com.mjc.school.controller.impl;

import com.mjc.school.controller.NewsCommandsController;
import com.mjc.school.service.NewsCommandsService;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.query.NewsQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController implements NewsCommandsController {
    private final NewsCommandsService service;
    @Autowired
    public NewsController(NewsCommandsService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/all")
    public List<NewsDTOResponse> readAll() {
        return service.readAll();
    }

    @Override
    @GetMapping("/{id}")
    public NewsDTOResponse readById(@PathVariable Long id) {
        return service.readById(id);
    }

    @Override
    @PostMapping()
    public NewsDTOResponse create(NewsDTORequest createRequest) {
        return service.create(createRequest);
    }

    @Override
    @PutMapping
    public NewsDTOResponse update(@RequestBody NewsDTORequest updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @Override
    public List<NewsDTOResponse> readBySearchParams(NewsQueryParams queryParams) {
        return service.readBySearchParams(queryParams);
    }

}
