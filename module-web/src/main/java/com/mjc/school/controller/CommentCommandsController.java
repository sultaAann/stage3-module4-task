package com.mjc.school.controller;


import java.util.List;

public interface CommentCommandsController<T, L>{
    List<T> readCommentsByNewsId(L id);
}
