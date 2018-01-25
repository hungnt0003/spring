package com.hung.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * Admin Request.
 *
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {

    /**
     * 
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object)
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    // Called before the handler execution, returns a boolean value, “true” : continue the handler execution chain;
    // “false”, stop the execution chain and return it.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // HttpURLConnection.setFollowRedirects(false);
        // HttpURLConnection con = (HttpURLConnection) new URL(request.getRequestURL().toString()).openConnection();
        // con.setRequestMethod("HEAD");
        // if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
        // if (con.getResponseCode() != 302) {
        // response.sendRedirect("/Spring1/hello");
        // }
        // }

        // long startTime = System.currentTimeMillis();
        // System.out.println("\n-------- LogInterception.preHandle --- ");
        // System.out.println("Request URL: " + request.getRequestURL());
        // System.out.println("Start Time: " + System.currentTimeMillis());
        //
        // request.setAttribute("startTime", startTime);
        //
        return true;
    }

    /**
     * 
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    // Called after the handler execution, allow manipulate the ModelAndView object before render it to view page.
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // System.out.println("\n-------- LogInterception.postHandle --- ");
        // System.out.println("Request URL: " + request.getRequestURL());

        // Ở đây, bạn có thể add các attribute vào modelAndView
        // Và sử dụng nó trong các View (jsp,..)
    }

    /**
     * 
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    // Called after the complete request has finished. Seldom use, cant find any use case.
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // System.out.println("\n-------- LogInterception.afterCompletion --- ");
        //
        // long startTime = (Long) request.getAttribute("startTime");
        // long endTime = System.currentTimeMillis();
        // System.out.println("Request URL: " + request.getRequestURL());
        // System.out.println("End Time: " + endTime);
        //
        // System.out.println("Time Taken: " + (endTime - startTime));
    }

}
