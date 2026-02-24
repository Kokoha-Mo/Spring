package tw.lab.Spring06.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import tw.lab.Spring06.dto.Register;

@Aspect
@Component
public class MyAspect {

    @Pointcut("execution(* tw.lab.Spring06.controller.TestController.*(..))") // .*.*(..) -> .所有類別.所有方法(所有參數)
    public void doTestController() {
    }

    @Pointcut("execution(* tw.lab.Spring06.controller.LabController.*(..))")
    public void doLabController() {
    }

    @Pointcut("execution(* tw.lab.Spring06.controller.*.*(..))")
    public void doAllController() {
    }

    // ----------------------------------------------------
    @Before("doTestController()")
    public void doBefore() {
        System.out.println("doBefore");
    }

    @After("doTestController()")
    public void doAfter() {
        System.out.println("doAfter");
    }

    @Around("doTestController()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around1");
        Object obj = joinPoint.proceed();
        System.out.println("around2");
        return obj;
    }

    // -------------------------------------------------------
    @Around("doAllController()")
    public Object doAllAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("aroundAll1");
        Object obj = joinPoint.proceed();
        System.out.println("aroundAll2");
        return obj;
    }
    // ------------------------------------------------------------
    // @Before("doLabController()")
    // public void doLabBefore(JoinPoint joinPoint) {
    // String name = joinPoint.getSignature().getName();
    // String type = joinPoint.getSignature().getDeclaringTypeName();
    // Object[] args = joinPoint.getArgs();
    // /*
    // * if (args != null) {
    // * if (args[0] instanceof Register) {
    // * Register reg = (Register) args[0];
    // * System.out.println(reg.getAccount());
    // * System.out.println(reg.getPasswd());
    // * System.out.println(reg.getName());
    // * System.out.println("----");
    // * String account = reg.getAccount().toUpperCase();
    // * reg.setAccount(account);
    // * }
    // * }
    // */

    // }

    @Before("doLabController()")
    public void doLabBeforeV2(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("1." + args[i]);
                System.out.println("------");
                if (args[i] instanceof String) {
                    args[i] = ((String) args[i]).toUpperCase();
                }
                System.out.println("2." + args[i]);
                System.out.println("------");
            }
        }
        Object[] args2 = joinPoint.getArgs();
        System.out.println("3." + args2[0]);
        System.out.println("------");

    }

    // @Around("doLabController()")
    // public Object doLabAround(ProceedingJoinPoint joinPoint) throws Throwable {
    // Object[] arg = joinPoint.getArgs();

    // System.out.println("1." + arg[0]);
    // arg[0] = ((String) arg[0]).toUpperCase();
    // if (arg[0] instanceof String) {
    // System.out.println("2." + arg[0]);
    // }
    // joinPoint.proceed(arg);
    // // joinPoint.proceed();
    // System.out.println("3." + arg[0]);
    // return arg;

    // }
}
