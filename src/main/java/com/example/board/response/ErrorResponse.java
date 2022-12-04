package com.example.board.response;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {

    //유효성 검증에서 발생하는 MethodArgumentNotValidException 에러처리
    private List<FieldError> fieldErrors;

    // URI 변수 값 검증에서 발생하는 ConstraintViolationException 에러처리
    private List<ConstraintViolationError> violationErrors;

    //private List<Runtime>

    private ErrorResponse(final List<FieldError> fieldErrors,
                          final List<ConstraintViolationError> violationErrors){
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
    }

    public static ErrorResponse of(BindingResult bindingResult){
        return new ErrorResponse(FieldError.of(bindingResult), null);
    }

    public static ErrorResponse of(Set<ConstraintViolation<?>> violations){
        return new ErrorResponse(null, ConstraintViolationError.of(violations));
    }

    @Getter
    public static class FieldError{
        private String field;
        private Object rejectedValue;
        private String reason;

        private FieldError(String field, Object rejectedValue, String reason) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        private static List<FieldError> of(BindingResult bindingResult){
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();

            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()
                    )).collect(Collectors.toList());
        }
    }

    @Getter
    public static class ConstraintViolationError{

        private String propertyPath;
        private Object rejectedValue;
        private String reason;

        private ConstraintViolationError(String propertyPath, Object rejectedValue, String reason){
            this.propertyPath = propertyPath;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        public static List<ConstraintViolationError> of(
                Set<ConstraintViolation<?>> constraintViolations){
            return constraintViolations.stream()
                    .map(constraintViolation -> new ConstraintViolationError(
                            constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getInvalidValue().toString(),
                            constraintViolation.getMessage()
                    )).collect(Collectors.toList());
        }

    }
}
