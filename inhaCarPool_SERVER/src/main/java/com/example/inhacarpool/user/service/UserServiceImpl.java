package com.example.inhacarpool.user.service;

import com.example.inhacarpool.common.port.ClockHolder;
import com.example.inhacarpool.topic.controller.port.TopicService;
import com.example.inhacarpool.user.controller.port.UserService;
import com.example.inhacarpool.user.controller.request.UserCreateRequest;
import com.example.inhacarpool.user.controller.response.UserWithHistoryCount;
import com.example.inhacarpool.user.domain.User;
import com.example.inhacarpool.user.service.port.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final ClockHolder clockHolder;

    private final TopicService topicService;

    private final UserRepository userRepository;

    @Transactional
    public User create(UserCreateRequest userCreateRequest) {
        /*TODO: DuplicateKeyException 처리*/
        User user = User.from(userCreateRequest.to(), clockHolder);
        user = userRepository.save(user);
        return user;
    }

    public List<UserWithHistoryCount> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> {
            Long historyCount = topicService.findHistoryCount(user);
            return UserWithHistoryCount.from(user, historyCount);
        }).toList();
    }

    @Transactional(readOnly = true)
    public User findOne(String uid) {
        return userRepository.findById(uid);
    }

    @Transactional
    public User resetYellow(String uid) {
        User user = userRepository.findById(uid);
        user = User.resetYellow(user);
        return userRepository.save(user);
    }

    public int countYellow(String uid) {
        User user = userRepository.findById(uid);
        return user.getYellowCard();
    }

    @Override
    @Transactional
    public void addYellow(String uid) {
        userRepository.addYellow(uid);
    }
}
