package com.example.inhacarpool.carpool.service;

import com.example.inhacarpool.carpool.controller.port.CarpoolService;
import com.example.inhacarpool.carpool.domain.Carpool;
import com.example.inhacarpool.carpool.domain.CarpoolCreate;
import com.example.inhacarpool.carpool.service.port.CarpoolRepository;
import com.example.inhacarpool.common.port.ClockHolder;
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
    private final CarpoolRepository carpoolRepository;
    private final UserService userService;

    @Override
    public Carpool create(CarpoolCreate carpoolCreate) {
        String uid = carpoolCreate.getAdmin();
        User user = userService.findUser(uid);
        Carpool carpool = Carpool.from(carpoolCreate, user, clockHolder);
        return carpoolRepository.save(carpool);
    }
}
