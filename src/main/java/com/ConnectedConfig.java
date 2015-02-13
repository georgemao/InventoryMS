package com;

import com.services.CarService;
import com.services.CarServiceConnected;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Loaded if the connected profile is enabled.
 * Connected means we are running in fully operational mode with a MongoDB backend running
 */

@Configuration
@Profile("connected")
public class ConnectedConfig {

    @Bean
    public CarService carService(){
        return new CarServiceConnected();
    }
}
