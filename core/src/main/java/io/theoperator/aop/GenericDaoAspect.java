package io.theoperator.aop;

import io.theoperator.repository.GenericRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by andreas on 3/4/15.
 */

@Aspect
@Component
public class GenericDaoAspect {

    static Logger log = Logger.getLogger(GenericRepository.class);

    //@Pointcut("execution(* io.theoperator.repository.GenericRepository.save(..))")
    //public void pointcutLogDao() {}

    //@Before("pointcutLogDao()")
    //public void logDao() {
    //    log.info("pointcutLogDao");
    //}
}
