package org.example.framework.services.operations;

public interface CreateService<ID, Dto> {
    Dto create(Dto dto);
}
