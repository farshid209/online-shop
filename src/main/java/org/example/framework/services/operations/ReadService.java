package org.example.framework.services.operations;

import org.example.framework.controllers.SearchFilter;
import org.example.framework.exception.NotFoundException;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

public interface ReadService<ID, Dto> {
    Dto read(ID id) throws NotFoundException;

    List<Dto> readAll();
}
