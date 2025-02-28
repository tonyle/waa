package edu.miu.lab1.aop;

import edu.miu.lab1.service.ExceptionService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionLoggingAspect {

    @Autowired
    private ExceptionService exceptionService;

    @Pointcut("execution(* edu.miu.lab1.service..*.*(..))")
    public void applicationMethod() {}

    @AfterThrowing(pointcut = "applicationMethod()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        String operation = joinPoint.getSignature().toString();
        String exceptionType = ex.getClass().getSimpleName();

        exceptionService.logException(operation, exceptionType);
    }
}
