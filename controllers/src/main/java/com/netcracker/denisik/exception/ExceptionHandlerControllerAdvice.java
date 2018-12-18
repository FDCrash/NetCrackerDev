package com.netcracker.denisik.exception;

import com.netcracker.denisik.dto.ExceptionResponse;
import com.netcracker.denisik.exteption.ResourceAlreadyExistException;
import com.netcracker.denisik.exteption.ResourceNotFoundException;
import com.netcracker.denisik.exteption.ServiceException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice  {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ExceptionResponse handleResourceNotFound(
            final ResourceNotFoundException exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.callerURL(request.getRequestURI());
        return error;
    }


    @ExceptionHandler(ResourceAlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleResourceAlreadyExist(
            final ResourceAlreadyExistException exception, final HttpServletRequest request) {

        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.callerURL(request.getRequestURI());

        return error;
    }


    @ExceptionHandler(JDBCConnectionException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse unnecessaryToJDBC(
            final JDBCConnectionException exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.callerURL(request.getRequestURI());
        return error;
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse service(
            final ServiceException exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.callerURL(request.getRequestURI());
        return error;
    }
}
