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
        List<Mesa> mesasList = new ArrayList<>();
        mesasList.add(new Mesa("mesa1", 6, "13-14"));
        mesasList.add(new Mesa("mesa2", 6, "14-15"));
        mesasList.add(new Mesa("mesa3", 6, "15-16"));
        mesasList.add(new Mesa("mesa4", 4, "13-14"));
        mesasList.add(new Mesa("mesa5", 4, "14-15"));
        mesasList.add(new Mesa("mesa6", 4, "15-16"));

        Mesa[] mesas = new Mesa[mesasList.size()];
        mesas = mesasList.toArray(mesas);
        return new Config(Integer.parseInt(nexts), Integer.parseInt(minGroup), mesas);
    }
}
