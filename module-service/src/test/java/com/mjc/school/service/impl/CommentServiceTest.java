package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.CommentRepository;
import com.mjc.school.repository.model.impl.Comment;
import com.mjc.school.service.dto.CommentDTORequest;
import com.mjc.school.service.dto.CommentDTOResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    CommentRepository repository;
    @InjectMocks
    CommentService service;

    @Test
    void readAll() {
        Comment res1 = new Comment();
        res1.setContent("Good!");
        Comment res2 = new Comment();
        res2.setContent("Bad!");
        List<Comment> comments = Arrays.asList(res1, res2);

        when(repository.readAll()).thenReturn(comments);

        List<CommentDTOResponse> result = service.readAll();
        assertThat(result).hasSize(2);
    }

    @Test
    void readById() {
        Comment comment = new Comment();
        comment.setContent("GREAT");
        when(repository.readById(1L)).thenReturn(Optional.of(comment));

        CommentDTOResponse res = service.readById(1L);

        assertThat(res.content()).isEqualTo(comment.getContent());
    }

    @Test
    void create() {
        CommentDTORequest request = new CommentDTORequest(null, "Ohh");

        CommentDTOResponse result = service.create(request);

        assertThat(result.content()).isEqualTo(request.content());
    }

    @Test
    void update() {
        CommentDTORequest request = new CommentDTORequest(null, "John");

        CommentDTOResponse result = service.update(request);

        assertThat(result.content()).isEqualTo(request.content());
    }

    @Test
    void deleteById() {
        when(repository.deleteById(1L)).thenReturn(true);

        assertTrue(service.deleteById(1L));
    }

    @Test
    void readCommentsByNewsId() {
    }
}