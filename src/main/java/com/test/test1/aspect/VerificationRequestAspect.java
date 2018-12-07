package com.test.test1.aspect;


import com.test.test1.utils.ClientRequestIPCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class VerificationRequestAspect {

    private static ClientRequestIPCache cache = new ClientRequestIPCache();

    @Pointcut("@annotation(BeforeVerificationRequest)")
    public void executeVerification(){

    }

    @Around("executeVerification()")
    public void beforeVerificationRequest(ProceedingJoinPoint joinPoint) throws Throwable {
            if(cache.inMinutReqLessThan50(getClientIp(),System.currentTimeMillis())) {
                joinPoint.proceed();
            } else {
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getResponse()
                        .sendError(502,"BAD GATEWAY");
            }
    }

    private String getClientIp(){
        return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest()
                .getRemoteAddr();
    }
}
