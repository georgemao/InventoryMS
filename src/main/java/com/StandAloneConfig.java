package com;

import com.services.CarService;
import com.services.CarServiceStandAlone;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Loaded if the standalone profile is enabled.
 * standalone means we are running in standalone mode with a all data hardcoded and simulated
 */
@Configuration
@Profile("standalone")
public class StandAloneConfig {

    @Bean
    public CarService carService(){
        return new CarServiceStandAlone();
    }
}
