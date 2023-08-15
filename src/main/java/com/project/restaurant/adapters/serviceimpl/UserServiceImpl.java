package com.project.restaurant.adapters.serviceimpl;

import com.project.restaurant.adapters.exception.NoSuchElementException;
import com.project.restaurant.domain.entity.User;
import com.project.restaurant.domain.repository.UserDescriptionRepository;
import com.project.restaurant.domain.repository.UserRepository;
import com.project.restaurant.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDescriptionRepository userDescriptionRepository;

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException(String.format("User with id %d was not found",id)));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new NoSuchElementException(String.format("User with username %s was not found",username)));
    }

    @Override
    public User save(User user) {
        user.setUserDescription(userDescriptionRepository.save(user.getUserDescription()));

        return userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        getUserById(user.getId());

        userRepository.save(user);
    }

    @Override
    public void activateUser(String username) {
        userRepository.activateUser(username);
    }
}
