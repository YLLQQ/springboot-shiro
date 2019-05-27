package org.yang.springboot.shiro.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yang.springboot.shiro.common.ShiroConst;
import org.yang.springboot.shiro.common.error.ShiroRouteConst;
import org.yang.springboot.shiro.model.shiro.JwtToken;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * org.yang.springboot.shiro.filter.JwtFilter
 *
 * @author eleven
 * @date 2019/05/23
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        String authorization = getAuthorizationFromRequest(request);

        if (log.isInfoEnabled()) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;

            log.info("current authorization is {}, visit uri is {}", authorization, httpServletRequest.getRequestURI());
        }

        return authorization != null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        redirectToDefaultUrl(request, response);

        return false;
    }

    @Override
    protected boolean onLoginFailure(
            AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response
    ) {
        log.error("login fail, exception message is {}", e);

        return false;
    }

    /**
     * 登录操作
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        String authorization = getAuthorizationFromRequest(request);
        JwtToken token = new JwtToken(authorization);

        if (log.isInfoEnabled()) {
            log.info("current token is {}", token);
        }

        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        super.getSubject(request, response).login(token);

        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 这里我们详细说明下为什么最终返回的都是true，即允许访问
     * 例如我们提供一个地址 GET /article
     * 登入用户和游客看到的内容是不同的
     * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
     * 所以我们在这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);

                return true;
            } catch (Exception e) {
                e.printStackTrace();

                redirectToDefaultUrl(request, response);
            }

            return true;
        }

        return false;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));

        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());

            return false;
        }

        return super.preHandle(request, response);
    }

    private String getAuthorizationFromRequest(ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        return httpServletRequest.getHeader(ShiroConst.HEADER_AUTHORIZATION_KEY);
    }

    /**
     * 重置非法请求
     *
     * @param servletRequest
     * @param servletResponse
     */
    private void redirectToDefaultUrl(ServletRequest servletRequest, ServletResponse servletResponse) {
        try {
            WebUtils.redirectToSavedRequest(servletRequest, servletResponse, ShiroRouteConst.DEFAULT_ERROR_REDIRECT_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
