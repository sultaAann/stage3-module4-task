package com.mjc.school.service.dto;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Component
@Scope("prototype")
public record NewsDTORequest(
        @Size(min = 5, max = 30, message = "News title can not be less than 5 and more than 30 symbols.")
        String title,
        @Size(min = 5, max = 255, message = "News content can not be less than 5 and more than 255 symbols.")
        String content,
        @Positive
        Long authorId
) {
}