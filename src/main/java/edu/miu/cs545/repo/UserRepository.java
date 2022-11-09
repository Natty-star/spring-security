package edu.miu.cs545.repo;

import edu.miu.cs545.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT u FROM User u WHERE u.posts.size > :post")
    List<User> findByPostsGreaterThan(int post);
//    List<User> findUserWithPosts(int post);

    @Query(value = "SELECT u FROM User u JOIN u.posts p WHERE p.title LIKE :title ")
    List<User> getUserFilteredPostTitle(String title);
}
