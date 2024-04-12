package org.example.framework.configuration;

import org.example.framework.annotation.MapperConfiguration;
import org.example.framework.mapping.MapperConfigurer;
import org.example.framework.mapping.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
@Order(value = Ordered.LOWEST_PRECEDENCE - 2)
public class MapperInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final ObjectMapper mapper;

    public MapperInitializer(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        context.getBeansOfType(MapperConfigurer.class)
                .values()
                .stream().filter(it -> it.getClass().getAnnotation(MapperConfiguration.class) != null)
                .sorted(Comparator.comparingInt(o -> o.getClass().getAnnotation(MapperConfiguration.class).order()))
                .forEach(mapper::register);

        mapper.validate();
    }
}
