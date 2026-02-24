package tw.lab.Spring06.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LabAspect {

    @Around("@annotation(tw.lab.Spring06.annotation.LapAOP)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before");
        Object obj = joinPoint.proceed();
        System.out.println("After");
        return obj;
    }
}
