package org.example.framework.mapping;

import org.modelmapper.ModelMapper;

/**
 * Visitor pattern.
 */
public interface MapperConfigurer {
    void configure(ModelMapper mapper);
}
