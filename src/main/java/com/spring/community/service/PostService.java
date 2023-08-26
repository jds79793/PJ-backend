package com.spring.community.service;

import com.spring.community.DTO.LikeSaveDTO;
import com.spring.community.DTO.PostListResponseDTO;
import com.spring.community.DTO.PostSaveDTO;
import com.spring.community.DTO.PostUpdateDTO;
import com.spring.community.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    Post getPostById(Long id);
    List<Post> getAllPosts();
    Page<Post> getPostsByCategoryId(Long categoryId, int pageNumber);
    void savePost(PostSaveDTO postSaveDTO);
    void deletePostById(Long id);
    void update(PostUpdateDTO postUpdateDTO);
    void saveLike(LikeSaveDTO likeSaveDTO);
    void increaseViewCount (Long postId);

}
