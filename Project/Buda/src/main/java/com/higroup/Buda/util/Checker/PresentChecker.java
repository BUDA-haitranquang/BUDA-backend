package com.higroup.Buda.util.Checker;

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


    public void checkIdAndRepository(Long id, JpaRepository repository) {
        checkId(id);
        if (!repository.findById(id).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
    }
}
