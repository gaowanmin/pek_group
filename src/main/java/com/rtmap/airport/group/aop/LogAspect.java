package com.rtmap.airport.group.aop;import com.fasterxml.jackson.databind.ObjectMapper;import com.rtmap.airport.group.entity.SysLog;import com.rtmap.airport.group.mapper.SysLogMapper;import com.rtmap.airport.group.util.RequestUtil;import org.aspectj.lang.ProceedingJoinPoint;import org.aspectj.lang.annotation.Around;import org.aspectj.lang.annotation.Aspect;import org.aspectj.lang.annotation.Pointcut;import org.aspectj.lang.reflect.MethodSignature;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.stereotype.Component;import org.springframework.web.context.request.RequestAttributes;import org.springframework.web.context.request.RequestContextHolder;import org.springframework.web.context.request.ServletRequestAttributes;import javax.annotation.Resource;import javax.servlet.http.HttpServletRequest;import java.lang.reflect.Method;import java.util.Date;/** * @Auther: shan * @Date:2022/2/17 * @Description: */@Aspect@Componentpublic class LogAspect {    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);    @Resource    private SysLogMapper sysLogMapper;    @Resource    private ObjectMapper objectMapper;    @Pointcut("@annotation(com.rtmap.airport.group.aop.LogAnnotation)")    public void logPointCut() {    }    //定义通知：环绕通知 - 执行核心任务之前 + 执行完核心任务之后 该做的事情    @Around("logPointCut()")    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {        long start = System.currentTimeMillis();        //执行核心任务        Object object = joinPoint.proceed();        long time = System.currentTimeMillis() - start;        saveLog(joinPoint, time);        return object;    }    //保存操作日志    private void saveLog(ProceedingJoinPoint point, Long time) throws Exception {        log.info("触发AOP保存操作日志");        MethodSignature signature = (MethodSignature) point.getSignature();        Method method = signature.getMethod();        SysLog entity = new SysLog();        //获取请求操作的描述信息        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);        if (annotation != null) {            entity.setOperation(annotation.value());        }        //获取操作方法名        String className = point.getTarget().getClass().getName();        String methodName = signature.getName();        entity.setMethod(className + "." + methodName + "()");        //获取请求参数        Object[] args = point.getArgs();        entity.setParams(objectMapper.writeValueAsString(args[0]));        //获取请求方式和ip        RequestAttributes ra = RequestContextHolder.getRequestAttributes();        ServletRequestAttributes sra = (ServletRequestAttributes) ra;        HttpServletRequest request = sra.getRequest();        entity.setType(request.getMethod());        entity.setIp(remoteIp(request));        entity.setUsername(request.getHeader("token"));        //获取剩余参数        entity.setTime(time);        entity.setCreateDate(new Date());        sysLogMapper.insert(entity);    }    private String remoteIp(HttpServletRequest request) {        return RequestUtil.remoteIp(request);    }}