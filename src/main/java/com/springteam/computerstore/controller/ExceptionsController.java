package com.springteam.computerstore.controller;

import com.springteam.computerstore.exception.CreationException;
import com.springteam.computerstore.exception.FindProductsByTypeException;
import com.springteam.computerstore.exception.ProductNotFoundException;
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
    @ExceptionHandler({NoHandlerFoundException.class, ProductNotFoundException.class})
    public ResponseEntity<ErrorsResponseApi> notFound(ProductNotFoundException exception) {
        log.error(exception.getMessage(), exception);

        List<ErrorDto> errors = List.of(
            new ErrorDto(404, HttpStatus.NOT_FOUND.getReasonPhrase(), null)
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(new ErrorsResponseApi(errors));
    }
    @ExceptionHandler({FindProductsByTypeException.class})
    public ResponseEntity<ErrorsResponseApi> noType(FindProductsByTypeException exception) {
        log.error(exception.getMessage(), exception);

        List<ErrorDto> errors = List.of(
            new ErrorDto(404, HttpStatus.NOT_FOUND.getReasonPhrase(), "Could not found product with type")
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(new ErrorsResponseApi(errors));
    }
    @ExceptionHandler({UpdateException.class})
    public ResponseEntity<ErrorsResponseApi> notUpdated(UpdateException exception) {
        log.error(exception.getMessage(), exception);

        List<ErrorDto> errors = List.of(
            new ErrorDto(404, HttpStatus.NOT_FOUND.getReasonPhrase(), "Could not update product")
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(new ErrorsResponseApi(errors));
    }
    @ExceptionHandler({CreationException.class})
    public ResponseEntity<ErrorsResponseApi> notCreated(CreationException exception) {
        log.error(exception.getMessage(), exception);

        List<ErrorDto> errors = List.of(
            new ErrorDto(404, HttpStatus.NOT_FOUND.getReasonPhrase(), "Could not create product")
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(new ErrorsResponseApi(errors));
    }
    //end regions

    //region 400 Not Valid
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorsResponseApi> notValid(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);

        List<ErrorDto> errors = List.of(
            new ErrorDto(400, HttpStatus.BAD_REQUEST.getReasonPhrase(), "Argument is not valid")
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(new ErrorsResponseApi(errors));
    }
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorsResponseApi> notReadableMessage(HttpMessageNotReadableException exception) {
        log.error(exception.getMessage(), exception);

        List<ErrorDto> errors = List.of(
            new ErrorDto(400, HttpStatus.BAD_REQUEST.getReasonPhrase(), "Unreadable JSON")
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(new ErrorsResponseApi(errors));
    }
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorsResponseApi> mismatchException(MethodArgumentTypeMismatchException exception) {
        log.error(exception.getMessage(), exception);

        List<ErrorDto> errors = List.of(
            new ErrorDto(400, HttpStatus.BAD_REQUEST.getReasonPhrase(), "Incorrect type of argument")
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(new ErrorsResponseApi(errors));
    }
    //end regions

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
