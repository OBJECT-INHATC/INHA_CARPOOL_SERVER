package com.example.inhacarpool.carpool.controller.port;

import com.example.inhacarpool.carpool.domain.Carpool;
import com.example.inhacarpool.carpool.domain.CarpoolCreate;

public interface CarpoolService {

    Carpool create(CarpoolCreate carpoolCreate);
}
