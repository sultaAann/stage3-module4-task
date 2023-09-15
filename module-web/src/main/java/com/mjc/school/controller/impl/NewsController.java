package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.NewsCommandsController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.NewsCommandsService;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController implements BaseController<NewsDTORequest, NewsDTOResponse, Long>, NewsCommandsController<NewsDTOResponse, Long> {
    @Autowired
    private BaseService<NewsDTORequest, NewsDTOResponse, Long> service;

    @Autowired
    private NewsCommandsService newsCommandsService;

    @Override
    @GetMapping("/all")
    public List<NewsDTOResponse> readAll() {
        return service.readAll();
    }

    @Override
    @GetMapping("/{id}")
    public NewsDTOResponse readById(@PathVariable Long id) throws NewsIDException, AuthorIDException, TagIDException {
        return service.readById(id);
    }

    @Override
    @PostMapping()
    public NewsDTOResponse create(NewsDTORequest createRequest) throws TitleOrContentLengthException, AuthorNameException, AuthorIDException, TagNameException {
        return service.create(createRequest);
    }

    @Override
    @PutMapping
    public NewsDTOResponse update(@RequestBody NewsDTORequest updateRequest) throws NewsIDException, TitleOrContentLengthException, AuthorIDException, AuthorNameException, TagNameException, TagIDException {
        return service.update(updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) throws NewsIDException, AuthorIDException, TagIDException {
        return service.deleteById(id);
    }

    public void deleteRelatedNews(Long id) {
//        System.out.println("""
//                Delete all articles related to this Author? (Write Number)
//                1. Yes
//                2. No (Field authorId of related news will be null)""");
//        boolean bool = scanner.nextLine().trim().equals("1");
//        if (bool) {
//            service.readAll().forEach(newsDTOResponse -> {
//                if (newsDTOResponse.authorId() == id) {
//                    try {
//                        service.deleteById(newsDTOResponse.id());
//                    } catch (NewsIDException | TagIDException | AuthorIDException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            });
//            System.out.println("All related news have been deleted.");
//        } else {
//            service.readAll().forEach(newsDTOResponse -> {
//                if (newsDTOResponse.authorId() == id) {
//                    try {
//                        service.update(new NewsDTORequest(newsDTOResponse.id(), newsDTOResponse.title(), newsDTOResponse.content(), null));
//                    } catch (TitleOrContentLengthException | NewsIDException | TagNameException | TagIDException |
//                             AuthorNameException | AuthorIDException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            });
//            System.out.println("All related authorId fields have been replaced with null.");
//        }
    }

    // Additional Commands
//    @Override
//    @CommandHandler(commandNumber = 16)
//    public List<NewsDTOResponse> readByTagName(String tagName) {
//        return newsCommandsService.readByTagName(tagName);
//    }
//
//    @Override
//    @CommandHandler(commandNumber = 17)
//    public List<NewsDTOResponse> readByTagId(Long tagId) {
//        return newsCommandsService.readByTagId(tagId);
//    }
//
//    @Override
//    @CommandHandler(commandNumber = 18)
//    public List<NewsDTOResponse> readByAuthorName(String authorName) {
//        return newsCommandsService.readByAuthorName(authorName);
//    }
//
//    @Override
//    @CommandHandler(commandNumber = 19)
//    public List<NewsDTOResponse> readByTitle(String title) {
//        return newsCommandsService.readByTitle(title);
//    }
//
//    @Override
//    @CommandHandler(commandNumber = 20)
//    public List<NewsDTOResponse> readByContent(String content) {
//        return newsCommandsService.readByContent(content);
//    }
}
