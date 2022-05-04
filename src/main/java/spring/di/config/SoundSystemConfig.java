package spring.di.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "spring.di", 
               excludeFilters = { @Filter(Configuration.class) })
public class SoundSystemConfig {
}
