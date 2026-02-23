package tw.lab.Spring06.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Pointcut("execution(* tw.lab.Sprong06.controller..*(..))")
    public void doControllerMethod() {

    }
}
