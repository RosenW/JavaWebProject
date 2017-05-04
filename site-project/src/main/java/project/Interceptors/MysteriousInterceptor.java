package project.Interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MysteriousInterceptor extends HandlerInterceptorAdapter {

    private long counter;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        this.counter++;
        httpServletRequest.setAttribute("counter", counter);
        return true;
    }
}
