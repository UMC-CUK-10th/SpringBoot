package com.example.umc10thchunsam.global.vaildator;

import com.example.umc10thchunsam.global.anotation.PageUnderOne;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageVaildator implements ConstraintValidator<PageUnderOne, Long> {

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {

        if (value == null) {
            return true;
        }

        return value >= 1;
    }
}