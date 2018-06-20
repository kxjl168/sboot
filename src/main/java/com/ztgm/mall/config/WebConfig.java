package com.ztgm.mall.config;

import com.ztgm.mall.filter.FreemarkerFilter;
import com.ztgm.mall.filter.WebSiteMeshFilter;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import java.util.ArrayList;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    /*@Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.setSessionTimeout(10);//单位为S
            }
        };
    }*/

    /**
     * Freemarker 过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean freemarkerFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();

        FreemarkerFilter freemarkerFilter = new FreemarkerFilter();
        filter.setFilter(freemarkerFilter);
        filter.setEnabled(true);
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("*.ftl");
        filter.setUrlPatterns(arrayList);
        return filter;
    }

    /**
     * 装饰器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean siteMeshFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();

        WebSiteMeshFilter siteMeshFilter = new WebSiteMeshFilter();
        filter.setFilter(siteMeshFilter);
        filter.setEnabled(true);
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("/*");
        filter.setUrlPatterns(arrayList);
        return filter;
    }


    /**
     * websocket
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }


    /*@Bean(name = "eisClientHandler")
    public EISClientHandler getEISClientHandler(EISprotocolMananger eiSprotocolMananger) {
        EISClientHandler handler = new EISClientHandler();
        handler.setEiSprotocolMananger(eiSprotocolMananger);
        return handler;
    }

    @Bean(name = "eisServiceHandler")
    public EISServiceHandler getEISServiceHandler(EISprotocolMananger eiSprotocolMananger) {
        EISServiceHandler handler = new EISServiceHandler();
        handler.setEiSprotocolMananger(eiSprotocolMananger);
        return handler;
    }

    @Bean(name = "serverAuthHandler")
    public ServerAuthHandler getServerAuthHandler(GateWayMapper gateWayMapper) {
        ServerAuthHandler handler = new ServerAuthHandler();
        handler.setGateWayMapper(gateWayMapper);
        return handler;
    }*/

    /**
     *  拦截器 用于拦截app token 验证
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }
    @Override
    public void addFormatters(FormatterRegistry registry) {

    }
}
