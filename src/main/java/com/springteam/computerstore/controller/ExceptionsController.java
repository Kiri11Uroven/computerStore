package com.springteam.computerstore.controller;

import com.springteam.computerstore.exception.ProductNotFound;
import com.springteam.computerstore.response.ErrorDto;
import com.springteam.computerstore.response.ErrorsResponseApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@Slf4j
@ControllerAdvice
public class ExceptionsController {

    //region 404 Not Found
    @ExceptionHandler({NoHandlerFoundException.class, ProductNotFound.class})
    public ResponseEntity<ErrorsResponseApi> notFound(Exception exception) {
        log.error(exception.getMessage(), exception);

        List<ErrorDto> errors = List.of(new ErrorDto(
            0, //TODO нужно составить список кодов ошибок программы
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            null
        ));

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(new ErrorsResponseApi(errors));
    }
    //endregion

    //region 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorsResponseApi> anyException(Exception exception) {
        log.error(exception.getMessage(), exception);

        List<ErrorDto> errors = List.of(new ErrorDto(
            0, //TODO нужно составить список кодов ошибок программы
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            exception.getMessage()
        ));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(new ErrorsResponseApi(errors));
    }
    //endregion
}
