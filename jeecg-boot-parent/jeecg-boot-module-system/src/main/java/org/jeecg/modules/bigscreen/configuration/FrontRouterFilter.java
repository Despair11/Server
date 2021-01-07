package org.jeecg.modules.bigscreen.configuration;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 前端vue采用history模式的路由处理
 * 将不同的模块路径统一返回到指定的html页面中
 *
 * @author gonghao
 */
public class FrontRouterFilter implements Filter {

    public static final String DISPATCH_URL = "dispatchUrl";

    public static final String URL_PATTERNS = "urlPatterns";

    public static final String CONTEXT_PATH = "contextPath";

    private List<String> frontRoute = new ArrayList();

    private String dispatchUrl;
    private PathMatcher pathMatcher = new AntPathMatcher();
    private String contextPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        contextPath = filterConfig.getInitParameter(CONTEXT_PATH);
        String urls = filterConfig.getInitParameter(URL_PATTERNS);
        if (!StringUtils.isEmpty(urls)) {
            String[] urlsArray = urls.split(",");
            List<String> newUrlsArray = Arrays.stream(urlsArray).map(url->{
                return contextPath + url;
                }).collect(Collectors.toList());
            frontRoute.addAll(newUrlsArray);
        }

        dispatchUrl = filterConfig.getInitParameter(DISPATCH_URL);
    }

    private String match(String requestUri) {
        for (String router : frontRoute) {
            if (pathMatcher.match(router, requestUri)) {
                return router;
            }
        }
        return null;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        String requestURI = request.getRequestURI();
        String router = match(requestURI);
        if (router != null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(dispatchUrl);
            if (null != requestDispatcher) {
                String actualDispatchUrl = dispatchUrl;
                String inPatternPart = pathMatcher.extractPathWithinPattern(router,requestURI);
                if(!StringUtils.isEmpty(inPatternPart)){
                    actualDispatchUrl+="#"+inPatternPart;
                }
                requestDispatcher = request.getRequestDispatcher(actualDispatchUrl);
                requestDispatcher.forward(request, response);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}