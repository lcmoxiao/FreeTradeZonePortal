package com.ftzp.filter.lc;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginFilter implements Filter {

    List<String> prefixIignores = new ArrayList<>();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest hsr = (HttpServletRequest) servletRequest;
        hsr.setCharacterEncoding("UTF-8");

        if (canIgnore(hsr)) {
            System.out.println(hsr.getRequestURI() + "不会被过滤哦");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (hsr.getSession().getAttribute("user") == null) {
                System.out.println("没登录，去登录吧");
                hsr.getRequestDispatcher("/index.html").forward(servletRequest, servletResponse);
            } else {
                System.out.println("已经登陆了,随便访问");
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void init(FilterConfig config) {
        String cp = config.getServletContext().getContextPath();
        String ignoresParam = config.getInitParameter("ignores");
        String[] ignoreArray = ignoresParam.split(",");
        for (String s : ignoreArray) {
            prefixIignores.add(cp + s);
        }
    }


    @Override
    public void destroy() {

    }

    private boolean canIgnore(HttpServletRequest request) {
        String url = request.getRequestURI();
        for (String ignore : prefixIignores) {

            if (url.startsWith(ignore)) {
                System.out.println(url + "不会被过滤哦");
                return true;
            }
        }
        System.out.println(url + "不给访问");
        return false;
    }

}
