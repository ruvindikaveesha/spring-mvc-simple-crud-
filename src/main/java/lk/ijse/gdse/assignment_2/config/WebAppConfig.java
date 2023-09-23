package lk.ijse.gdse.assignment_2.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebAppConfig {

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
