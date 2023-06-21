package com.springteam.computerstore.controller;

import com.springteam.computerstore.exception.CreationException;
import com.springteam.computerstore.exception.FindProductsByType;
import com.springteam.computerstore.exception.ProductNotFound;
import com.springteam.computerstore.exception.UpdateException;
import com.springteam.computerstore.response.ErrorDto;
import com.springteam.computerstore.response.ErrorsResponseApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@Slf4j
@ControllerAdvice
public class ExceptionsController {

    //region 404 Not Found
    @ExceptionHandler({NoHandlerFoundException.class, ProductNotFound.class,
        FindProductsByType.class, UpdateException.class, CreationException.class})
    public ResponseEntity<ErrorsResponseApi> notFound(Exception exception) {
        log.error(exception.getMessage(), exception);

        List<ErrorDto> errors = List.of(
            new ErrorDto(404, HttpStatus.NOT_FOUND.getReasonPhrase(), "No Handler Found"),
            new ErrorDto(404, HttpStatus.NOT_FOUND.getReasonPhrase(), "No Product Found"),
            new ErrorDto(404, HttpStatus.NOT_FOUND.getReasonPhrase(), "No products with type found"),
            new ErrorDto(400, HttpStatus.NOT_FOUND.getReasonPhrase(), "Product not updated"),
            new ErrorDto(400, HttpStatus.NOT_FOUND.getReasonPhrase(), "Product not created")
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(new ErrorsResponseApi(errors));
    }
    //endregion

    //region 400 Not Valid
    @ExceptionHandler({MethodArgumentNotValidException.class,
        HttpMessageNotReadableException.class,
        MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorsResponseApi> notValid(Exception exception) {
        log.error(exception.getMessage(), exception);

        List<ErrorDto> errors = List.of(
            new ErrorDto(400, HttpStatus.BAD_REQUEST.getReasonPhrase(), "Method Argument Not Valid"),
            new ErrorDto(400, HttpStatus.BAD_REQUEST.getReasonPhrase(), "Malformed JSON Request"),
            new ErrorDto(400, HttpStatus.BAD_REQUEST.getReasonPhrase(), "The parameter '%s' of value '%s' could not be converted to type '%s'")
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(new ErrorsResponseApi(errors));
    }
    //end region

    //region 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorsResponseApi> anyException(Exception exception) {
        log.error(exception.getMessage(), exception);

        List<ErrorDto> errors = List.of(new ErrorDto(
            500,
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            exception.getMessage()
        ));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(new ErrorsResponseApi(errors));
    }
    //endregion
}
