package ie.britoj.currencyexchangerates.configurations;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ RootConfiguration.class, SpringSecurityConfig.class };
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebMvcConfiguration.class};
    }

    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
