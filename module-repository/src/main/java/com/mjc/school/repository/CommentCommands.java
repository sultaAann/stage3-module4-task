package com.mjc.school.repository;


import java.util.List;

public interface CommentCommands<T, L>{
    List<T> readCommentsByNewsId(L id);
}
