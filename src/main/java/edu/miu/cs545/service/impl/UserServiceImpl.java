package edu.miu.cs545.service.impl;

import edu.miu.cs545.domain.Post;
import edu.miu.cs545.domain.User;
import edu.miu.cs545.domain.dto.PostDto;
import edu.miu.cs545.domain.dto.UserDto;
import edu.miu.cs545.repo.UserRepository;
import edu.miu.cs545.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;


    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Long id) {
        return modelMapper.map(userRepository.findById(id), UserDto.class);
    }

    @Override
    public UserDto save(UserDto userDto) {
        User savedUser = userRepository.save(modelMapper.map(userDto, User.class));
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public List<PostDto> getUserPosts(Long id) {
        User user =userRepository.findById(id).get();

        return user.getPosts().stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void addPost(Long id, List<Post> post) {
        var user = userRepository.findById(id).get();
        for (Post p: post){
            user.getPosts().add(p);
        }

        userRepository.save(user);
    }

    @Override
    public User getUserByIdTest(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<UserDto> getUserMoreThanPosts(int post) {
        return userRepository.findByPostsGreaterThan(post).stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<User> getUserFilteredPostTitle(String title) {
        return userRepository.getUserFilteredPostTitle(title);
    }
}
