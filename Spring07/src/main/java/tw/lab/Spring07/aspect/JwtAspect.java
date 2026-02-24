package tw.lab.Spring07.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Component
@Aspect
public class JwtAspect {

    @Around("@annotation(tw.lab.Spring07.annotation.CheckJwt)")
    public Object checkJwt(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) (RequestContextHolder.getRequestAttributes());

        HttpServletRequest request = attributes.getRequest();
        String authHeader = request.getHeader("Authorization");
        System.out.println(authHeader);

        return joinPoint.proceed();
    }
}
