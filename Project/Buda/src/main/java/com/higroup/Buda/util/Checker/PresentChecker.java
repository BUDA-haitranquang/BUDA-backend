package com.higroup.Buda.util.Checker;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class PresentChecker {
    public void checkId(Long id){
        if (id < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must not be negative");
    }


    public Object checkIdAndRepository(Long id, JpaRepository<?,Long> repository) {
        checkId(id);
        Optional<?> object = repository.findById(id);
        if (!object.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, repository.getClass().getName() + " Not found");
        return object.get();
    }
}
