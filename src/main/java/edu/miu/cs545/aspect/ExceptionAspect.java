package edu.miu.cs545.aspect;

import edu.miu.cs545.domain.ExceptionDb;
import edu.miu.cs545.repo.ExceptionRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class ExceptionAspect {
    @Autowired
    private ExceptionRepository exceptionRepository;
    @Pointcut("execution( * edu.miu.cs545.restApi.controller.*.*(..))")
    public void exceptionAspect(){}

    @AfterThrowing(value = "exceptionAspect()" , throwing = "e")
    public void afterThrow(JoinPoint joinPoint, Exception e){
        ExceptionDb exceptionDb = ExceptionDb.builder()
                .dateTime(new Date())
                .username("natty")
                .operation(joinPoint.getSignature().getName())
                .exceptionType(e.getClass().getName())
                .build();
        exceptionRepository.save(exceptionDb);
    }
}
