package edu.miu.lab1.service;

import edu.miu.lab1.entity.dto.CommentDto;

public interface CommentService {
    void save(long PostId, CommentDto commentDto);
}
