package miniPoject.bookManager.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { AppConfiguration.class };
    }

    // LỖI ở đây — Servlet Mapping bị đặt sai
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };   // ← Chú ý dòng này
    }
}

