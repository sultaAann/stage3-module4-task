package com.mjc.school.service;


import java.util.List;

public interface CommentCommandsService<T, L>{
    List<T> readCommentsByNewsId(L id);
}
