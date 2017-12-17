package ie.britoj.currencyexchangerates.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebMvc
public class WebConfigurer implements WebMvcConfigurer {


    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**", "/static/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/", "/static/")
                .setCacheControl(
                CacheControl.maxAge(30L, TimeUnit.DAYS).cachePublic());

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/signin").setViewName("signin-form");
        registry.addViewController("/about").setViewName("about");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
