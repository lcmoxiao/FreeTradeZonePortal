package com.ftzp.filter.lc;

import com.ftzp.cache.RedisBeanFactory;
import com.ftzp.cache.RedisObjCache;
import com.ftzp.pojo.lc.user.Permission;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ftzp.controller.lc.LoginController.getRemoteIP;

public class LoginFilter implements Filter {

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
            System.out.println(hsr.getRequestURI() + "不会被过滤哦");
            filterChain.doFilter(re, servletResponse);
        } else {
            String IP = getRemoteIP(hsr);
            System.out.println(redisObjCache);
            if (redisObjCache.getValue(IP + "u") == null) {
                System.out.println("没登录，没权限，去登录吧");
                hsr.getRequestDispatcher("/index").forward(re, servletResponse);
            } else {
                System.out.println("已经登陆了,再验证一下权限");
                List<Permission> ps = (ArrayList<Permission>) redisObjCache.getValue(IP + "p");
                if (authoricationCheck(targetUrl, ps)) filterChain.doFilter(re, servletResponse);
                else hsr.getRequestDispatcher("/error").forward(re, servletResponse);
            }
        }
    }


    private boolean canIgnore(String targetUrl) {
        for (String ignore : prefixIignores) {
            if (targetUrl.startsWith(ignore)) {
                System.out.println(targetUrl + "不会被过滤哦");
                return true;
            }
        }
        System.out.println(targetUrl + "需要检测权限才给访问");
        return false;
    }

    private boolean authoricationCheck(String targetUrl, List<Permission> ps) {
        for (Permission ignore : ps) {
            if (targetUrl.startsWith(ignore.getpSrc())) {
                System.out.println(targetUrl + "你有权限");
                return true;
            }
        }
        System.out.println(targetUrl + "你没权限");
        return false;
    }


}
