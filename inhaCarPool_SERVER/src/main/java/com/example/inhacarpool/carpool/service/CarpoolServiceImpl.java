package com.example.inhacarpool.carpool.service;

import com.example.inhacarpool.carpool.controller.port.CarpoolService;
import com.example.inhacarpool.carpool.domain.Carpool;
import com.example.inhacarpool.carpool.domain.CarpoolCreate;
import com.example.inhacarpool.carpool.service.port.CarpoolRepository;
import com.example.inhacarpool.common.port.ClockHolder;
import com.example.inhacarpool.topic.controller.port.TopicService;
import com.example.inhacarpool.user.controller.port.UserService;
import com.example.inhacarpool.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CarpoolServiceImpl implements CarpoolService {

    private final ClockHolder clockHolder;

    private final UserService userService;
    private final TopicService topicService;

    private final CarpoolRepository carpoolRepository;

    @Override
    public Carpool create(CarpoolCreate carpoolCreate) {
        String uid = carpoolCreate.getAdmin();
        User user = userService.findOne(uid);
        Carpool carpool = Carpool.from(carpoolCreate, user, clockHolder);
        // TODO 중복 체크
        carpool = carpoolRepository.save(carpool);

        topicService.create(user, carpool);

        return carpool;
    }

    @Override
    public Carpool findCarpool(String carpoolId) {
        return carpoolRepository.findCarpool(carpoolId);
    }

}
