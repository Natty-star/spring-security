package edu.miu.cs545.service.impl;

import edu.miu.cs545.domain.Comment;
import edu.miu.cs545.domain.Post;
import edu.miu.cs545.domain.dto.PostDto;
import edu.miu.cs545.domain.dto.PostDtoV2;
import edu.miu.cs545.repo.PostRepo;
import edu.miu.cs545.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    public PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    public List<PostDto> getAll() {
        return postRepo.findAll().stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }
    public PostDto getById(Long id){
        return modelMapper.map(postRepo.findById(id),PostDto.class);
    }

    public void save(PostDto postDto) {
        postRepo.save(modelMapper.map(postDto, Post.class));
    }

    @Override
    public void delete(Long id) {
        postRepo.deleteById(id);
    }

    @Override
    public void update(Long id, PostDto postDto) {
        Post getPost = postRepo.findById(id).get();
        getPost.setAuthor(postDto.getAuthor());
        getPost.setContent(postDto.getContent());
        getPost.setTitle(postDto.getTitle());
        postRepo.save(modelMapper.map(getPost,Post.class));
    }

    //version api v2
    @Override
    public List<PostDtoV2> getAll2() {
        return postRepo.findAll().stream().map(post -> modelMapper.map(post,PostDtoV2.class)).collect(Collectors.toList());
    }

    @Override
    public void addComment(Long id, List<Comment> comment) {
        var p = postRepo.findById(id).get();
       for (Comment c: comment ){
           p.getComments().add(c);
       }
        postRepo.save(p);
    }

    @Override
    public List<Comment> getPostComments(Long id) {
        var post = postRepo.findById(id).get();
        return new ArrayList<>(post.getComments());
    }

    @Override
    public List<Post> getPostsLikeTitle(String title) {
        return postRepo.findByTitle(title);
    }

}
