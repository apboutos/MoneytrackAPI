package com.apboutos.moneytrackapi.controller.Response;

import com.apboutos.moneytrackapi.model.CategoryDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Timestamp;

@Data
@RequiredArgsConstructor
@ResponseStatus(code = HttpStatus.CREATED)
public class CategoryCreationResponse {

    private final HttpStatus status;
    private final String result;
    private final String message;
    private final Timestamp timestamp;
    private final CategoryDTO category;
}
