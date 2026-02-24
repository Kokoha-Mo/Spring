package tw.lab.Spring07.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import tw.lab.Spring07.exception.JwtAuthException;

@RestControllerAdvice // 全域攔截處理
public class GlobalExceptionHandler {

    // 全域例外攔截處理
    @ExceptionHandler(JwtAuthException.class)
    public ResponseEntity<String> handleJwtException(JwtAuthException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("權限被拒:" + e.getMessage());
    }
}
