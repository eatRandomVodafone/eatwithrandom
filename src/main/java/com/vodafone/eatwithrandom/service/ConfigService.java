package com.vodafone.eatwithrandom.service;

import com.vodafone.eatwithrandom.model.Config;
import com.vodafone.eatwithrandom.model.Mesa;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfigService {

    @Value("${config.nexts}")
    private String nexts;

    @Value("${config.minGroup}")
    private String minGroup;

    public Config readConfig() {
       return null;
    }
}
