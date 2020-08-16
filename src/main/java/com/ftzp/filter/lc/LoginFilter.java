package com.ftzp.filter.lc;

import com.ftzp.cache.RedisBeanFactory;
import com.ftzp.cache.RedisObjCache;
import com.ftzp.pojo.lc.user.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ftzp.controller.lc.LoginController.getRemoteIP;

public class LoginFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    List<String> prefixIignores = new ArrayList<>();

    RedisObjCache redisObjCache;

    @Override
    public void init(FilterConfig config) {
        String cp = config.getServletContext().getContextPath();
        String ignoresParam = config.getInitParameter("ignores");
        String[] ignoreArray = ignoresParam.split(",");
        for (String s : ignoreArray) {
            prefixIignores.add(cp + s);
        }
        ServletContext servletContext = config.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        assert ctx != null;
    }


    @Override
    public void doFilter(ServletRequest re
            , ServletResponse servletResponse
            , FilterChain filterChain) throws IOException, ServletException {
        if (redisObjCache == null) redisObjCache = RedisBeanFactory.getInstance();
        HttpServletRequest hsr = (HttpServletRequest) re;
        hsr.setCharacterEncoding("UTF-8");

        String targetUrl = hsr.getRequestURI();
        if (canIgnore(targetUrl)) {
            filterChain.doFilter(re, servletResponse);
        } else {
            String IP = getRemoteIP(hsr);
            if (redisObjCache.getValue(IP + "u") == null) {
                logger.info(IP + "尝试进入" + hsr.getRequestURI() + ",但未登录");
                hsr.getRequestDispatcher("/index").forward(re, servletResponse);
            } else {
                List<Permission> ps = (ArrayList<Permission>) redisObjCache.getValue(IP + "p");
                if (authoricationCheck(targetUrl, ps)) filterChain.doFilter(re, servletResponse);
                else {
                    logger.info(IP + "已经登陆，但无权限进入" + hsr.getRequestURI());
                    hsr.getRequestDispatcher("/error").forward(re, servletResponse);
                }
            }
        }
    }


    private boolean canIgnore(String targetUrl) {
        for (String ignore : prefixIignores) {
            if (targetUrl.startsWith(ignore)) {
                return true;
            }
        }
        return false;
    }

    private boolean authoricationCheck(String targetUrl, List<Permission> ps) {
        for (Permission ignore : ps) {
            if (targetUrl.startsWith(ignore.getpSrc())) {
                return true;
            }
        }
        return false;
    }


}
