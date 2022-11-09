package edu.miu.cs545.controller;

import edu.miu.cs545.domain.Comment;
import edu.miu.cs545.domain.Post;
import edu.miu.cs545.domain.dto.PostDto;
import edu.miu.cs545.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public List<PostDto> getAll(){
        return postService.getAll();
    }

   @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public PostDto getById(@PathVariable("id") Long id){
        return postService.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody PostDto postDto){
        postService.save(postDto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        postService.delete(id);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody PostDto postDto){
        postService.update(id,postDto);
    }

    @PostMapping("/{id}/comments")
    public void addComment(@PathVariable("id") Long id, @RequestBody List<Comment> comment){
        postService.addComment(id,comment);
    }
    @GetMapping("/{id}/comments")
    public List<Comment> getPostComments(@PathVariable("id") Long id){
        return postService.getPostComments(id);
    }

    @GetMapping("/filter")
    public List<Post> getPostsLikeTitle(@RequestParam String title){
        return postService.getPostsLikeTitle(title);
    }

}
