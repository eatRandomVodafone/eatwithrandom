package com.vodafone.eatwithrandom.service;

import com.vodafone.eatwithrandom.model.Config;
import com.vodafone.eatwithrandom.model.Mesa;
import org.springframework.beans.factory.annotation.Value;

public class ConfigService {

    @Value("${config.nexts}")
    private String nexts;

    @Value("${config.minGroup}")
    private String minGroup;

    @Value("${config.mesas}")
    private Mesa[] mesas;

    public Config readConfig() {
        return new Config(Integer.parseInt(nexts), Integer.parseInt(minGroup), mesas);
    }
}
