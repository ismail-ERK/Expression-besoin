package com.expressionbesoins.restexpbesoin.annotations;

import com.expressionbesoins.restexpbesoin.exception.UserHasNotAuthorisation;
import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.repository.UserRepo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Aspect
@Component
public class HasAuthorityImpl {
    @Autowired
    UserRepo userRepo;

    @Around("@annotation(hasAuthority)")
    public Object checkAuthority(ProceedingJoinPoint joinPoint, HasAuthority hasAuthority) throws Throwable {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = principal.toString();
        User user = userRepo.findUserByEmail(email);
        AtomicBoolean hasAuth = new AtomicBoolean(false);
        user.getRoles().forEach(role -> {
            if(role.getName().equals(hasAuthority.authority())){
                hasAuth.set(true);
            }
        });
        if(!hasAuth.get()){
            throw new UserHasNotAuthorisation("This user has not the authorisation...");
        }
        else {
            return joinPoint.proceed();
        }

    }
}
