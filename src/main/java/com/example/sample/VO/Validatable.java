package com.example.sample.VO;

import com.example.sample.exception.BadRequestException;

public interface Validatable {
    void validate() throws BadRequestException;
}
