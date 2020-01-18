package spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    private Logger myLogger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* spring.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* spring.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* spring.DAO.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {}

    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("@Before: calling method: " + theMethod);

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg: args) {
            myLogger.info("argument: " + tempArg);
        }
    }

    @AfterReturning(pointcut="forAppFlow()", returning="theResult")
    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("@After: calling method: " + theMethod);

        myLogger.info("reuslt: " + theResult);
    }
}
