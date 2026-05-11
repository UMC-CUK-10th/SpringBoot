package com.example.umc10thchunsam.global.anotation;


import com.example.umc10thchunsam.global.vaildator.PageVaildator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = PageVaildator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PageUnderOne {

    String message() default "잘못된 페이지 입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
