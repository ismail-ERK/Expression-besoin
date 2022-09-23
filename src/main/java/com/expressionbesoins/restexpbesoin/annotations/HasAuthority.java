package com.expressionbesoins.restexpbesoin.annotations;

import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface HasAuthority {
    PrivilegeEnum authority();
}
