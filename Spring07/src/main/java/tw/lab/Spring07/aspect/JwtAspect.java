package tw.lab.Spring07.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import tw.lab.Spring07.exception.JwtAuthException;
import tw.lab.Spring07.util.JwtToken;

@Component
@Aspect
public class JwtAspect {
    private static Logger logger = LoggerFactory.getLogger(JwtAspect.class);

    @Around("@annotation(tw.lab.Spring07.annotation.CheckJwt)")
    public Object checkJwt(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) (RequestContextHolder.getRequestAttributes());

        HttpServletRequest request = attributes.getRequest();

        logger.info(request.getRemoteAddr() + ":Hello");

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            throw new JwtAuthException("no token");
        }

        System.out.println(authHeader);
        // check bearer token
        if (!authHeader.startsWith("Bearer ")) {
            throw new JwtAuthException("token format error");
        }

        try {
            String token = authHeader.substring(7);
            String data = JwtToken.parseToken(token);
            if (data != null) {
                System.out.println(data);
            } else {
                throw new JwtAuthException("token data error");
            }
        } catch (Exception e) {
            throw new JwtAuthException("token parse error" + e);
        }

        return joinPoint.proceed();
    }
}
