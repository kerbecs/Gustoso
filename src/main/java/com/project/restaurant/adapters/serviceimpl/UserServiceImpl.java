package com.project.restaurant.adapters.serviceimpl;

import com.project.restaurant.adapters.exception.NoSuchElementException;
import com.project.restaurant.domain.entity.User;
import com.project.restaurant.domain.repository.UserDescriptionRepository;
import com.project.restaurant.domain.repository.UserRepository;
import com.project.restaurant.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDescriptionRepository userDescriptionRepository;

    @Override
    @Cacheable(value = "users", key = "#id")
    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException(String.format("User with id %d was not found", id)));
    }

    @Override
    @Cacheable(value = "users", key = "#username")
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new NoSuchElementException(String.format("User with username %s was not found", username)));
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public User save(User user) {
        user.setUserDescription(userDescriptionRepository.save(user.getUserDescription()));

        return userRepository.save(user);
    }

    @Override
    @Cacheable("users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @CachePut(value = "users", key = "#user.username")
    public void updateUser(User user) {
        getUserById(user.getId());

        userRepository.save(user);
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public void activateUser(String username) {
        userRepository.activateUser(username);
    }
}
