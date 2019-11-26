package com.vodafone.eatwithrandom.repository;

import java.util.List;
import java.util.Optional;

import com.vodafone.eatwithrandom.model.Config;

public interface ConfigRepository{

	public Optional<List<Config>> getConfig();
    
}