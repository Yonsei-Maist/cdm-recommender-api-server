/**
 * view Configuration Class
 * @Author Chanwoo Gwon, Yonsei Univ. Researcher, since 2020.05.~
 * @Date 2020.10.16
 */
package kr.ac.yonsei.recommender.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    /**
     * add resource
     * @param registry binding object
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/")
                .setCacheControl(CacheControl.maxAge(60, TimeUnit.MINUTES));
    }
}
