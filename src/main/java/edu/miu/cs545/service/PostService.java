package edu.miu.cs545.service;

import edu.miu.cs545.domain.Comment;
import edu.miu.cs545.domain.Post;
import edu.miu.cs545.domain.dto.PostDto;
import edu.miu.cs545.domain.dto.PostDtoV2;

import java.util.List;

public interface PostService {
    List<PostDto> getAll();
    PostDto getById(Long id);
    void save(PostDto postDto);
    void delete(Long id);
    void update(Long id, PostDto postDto);

    List<PostDtoV2> getAll2();

    void addComment(Long id, List<Comment> comment);

    List<Comment> getPostComments(Long id);

    List<Post> getPostsLikeTitle(String title);
}
