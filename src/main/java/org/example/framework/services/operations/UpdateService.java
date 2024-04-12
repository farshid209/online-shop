package org.example.framework.services.operations;

import org.example.framework.exception.NotFoundException;

public interface UpdateService<ID, Dto> {
    void update(ID id, Dto dto) throws NotFoundException;
}
