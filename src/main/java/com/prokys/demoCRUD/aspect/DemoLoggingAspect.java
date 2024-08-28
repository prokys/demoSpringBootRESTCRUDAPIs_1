package com.prokys.demoCRUD.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    //setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    //setup pointcut declaration
    @Pointcut("execution(* com.prokys.demoCRUD.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.prokys.demoCRUD.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.prokys.demoCRUD.dao.*.*(..))")
    private void forDAOPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){
        // display method we are calling
        String method = joinPoint.getSignature().toShortString();
        logger.info("====>>>> in @Before: calling method: " + method);

        // display the arguments to the method
        //get arguments
        Object[] args = joinPoint.getArgs();

        //loop through and display
        for (Object arg : args){
            logger.info("====>>>>argument: " + arg);
        }

    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void after(JoinPoint joinPoint, Object result){

        //display method we are returning from

        String method = joinPoint.getSignature().toShortString();
        logger.info("====>>>> in @AfterReturning from the method: " + method);

        //display data that was returned
        logger.info("====>>>>result: " + result);
    }

}
