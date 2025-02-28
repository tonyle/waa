package edu.miu.lab1.aop;

import edu.miu.lab1.service.LoggerService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExecutionTimeAspect {

    private final LoggerService loggerService;
    private long startTime;

    public ExecutionTimeAspect(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @Pointcut("@annotation(ExecutionTime)")
    public void executionTimePointcut() {}

    @Before("executionTimePointcut()")
    public void beforeExecution(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
        joinPoint.getArgs();
    }

    @AfterReturning("executionTimePointcut()")
    public void afterExecution(JoinPoint joinPoint) {
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        loggerService.logOperation(joinPoint.getSignature().toString(), executionTime);
    }
}
