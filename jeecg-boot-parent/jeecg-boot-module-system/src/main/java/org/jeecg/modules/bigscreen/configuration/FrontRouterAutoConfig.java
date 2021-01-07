package org.jeecg.modules.bigscreen.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 前端路由
 *
 * @author fangqin
 * @date 2018年10月15日
 */
@Configuration
@ConditionalOnClass({FrontRouterFilter.class})
public class FrontRouterAutoConfig {

    @Value("${starfish.front.service.page.urls:}")
    private String urlPatterns;

    @Value("${starfish.front.service.page.dispatchUrl:/index.html}")
    private String disPatchUrl;

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    /**
     * 添加前端路由过滤器
     */
    @Bean
    public FilterRegistrationBean frontFilterBean() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        FrontRouterFilter filter = new FrontRouterFilter();

        registrationBean.addInitParameter(FrontRouterFilter.URL_PATTERNS,
                urlPatterns);

        registrationBean.addInitParameter(FrontRouterFilter.DISPATCH_URL, disPatchUrl);

        registrationBean.addInitParameter(FrontRouterFilter.CONTEXT_PATH,contextPath);

        registrationBean.setFilter(filter);

        registrationBean.addUrlPatterns("/*");

        registrationBean.setName("frontFilter");

        registrationBean.setOrder(6);

        return registrationBean;

    }

}
