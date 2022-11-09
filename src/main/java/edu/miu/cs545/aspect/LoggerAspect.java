package edu.miu.cs545.aspect;

import edu.miu.cs545.domain.Logger;
import edu.miu.cs545.repo.LoggerRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class LoggerAspect {
    @Autowired
    private LoggerRepository loggerRepository;

    @Pointcut("execution(* edu.miu.cs545.restApi.service..*.*(..))")
    public void serviceLog(){}

    @Before("serviceLog()")
    public void logger(JoinPoint joinPoint){
        Logger logger = Logger.builder()
                .dateTime(new Date())
                .username("natty")
                .operation(joinPoint.getSignature().getName())
                .build();
        loggerRepository.save(logger);
    }
}
