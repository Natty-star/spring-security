//package edu.miu.cs545.restApi.repo.impl;
//
//import edu.miu.cs545.restApi.domain.Post;
//import edu.miu.cs545.restApi.repo.PostRepo;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//@Repository
//public class PostRepoImpl implements PostRepo {
//    private static List<Post> posts;
//
//    private static Long postId = 100L;
//
//    static {
//        posts = new ArrayList<>();
//        Post post1 = new Post(1,"great", "positive content","Natty");
//        Post post2 = new Post(2,"Sport", "positive content","Mera");
//        Post post3 = new Post(3,"New Post", "positive content","Nat");
//
//        posts.add(post1);
//        posts.add(post2);
//        posts.add(post3);
//    }
//
//
//    @Override
//    public List<Post> findAll(){
//        return posts;
//    }
//
//    @Override
//    public Post findById(Long id){
//        return  posts.stream().filter(post -> post.getId() == id).findFirst().orElse(null);
//    }
//
//    @Override
//    public void save(Post post) {
//        post.setId(postId++);
//        posts.add(post);
//    }
//
//    @Override
//    public void delete(Long id) {
//        Post p = posts.stream().filter(post -> post.getId() ==id).findFirst().orElse(null);
//        posts.remove(p);
//    }
//
//    @Override
//    public void update(Long id, Post post) {
//        Post toUpdatePost = findById(id);
//        toUpdatePost.setAuthor(post.getAuthor());
//        toUpdatePost.setContent(post.getContent());
//        toUpdatePost.setTitle(post.getTitle());
//    }
//
//
//}
