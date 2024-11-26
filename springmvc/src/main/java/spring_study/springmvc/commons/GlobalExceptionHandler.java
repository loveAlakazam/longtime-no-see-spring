package spring_study.springmvc.commons;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring_study.springmvc.boards.errors.PasswordMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler( PasswordMismatchException.class)
    @ResponseStatus( HttpStatus.BAD_REQUEST)
    public ErrorResponse handlePasswordMismatchException ( PasswordMismatchException exception) {
        return new ErrorResponse("PASSWORD_MISMATCH", exception.getMessage());
    }
}
