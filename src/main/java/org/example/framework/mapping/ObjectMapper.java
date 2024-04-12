package org.example.framework.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ObjectMapper {
    private final ModelMapper mapper;

    public ObjectMapper() {
        mapper = new ModelMapper();
        mapper.getConfiguration().setImplicitMappingEnabled(false);
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public void register(MapperConfigurer configurer) {
        configurer.configure(mapper);
    }

    public void validate() {
        mapper.validate();
    }

    public <S, D> D map(S source, Class<D> clazz) {
        return mapper.map(source, clazz);
    }

    public <S, D> D map(S source, D destination) {
        mapper.map(source, destination);
        return destination;
    }
}
