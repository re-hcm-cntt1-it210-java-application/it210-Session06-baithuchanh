package miniPoject.bookManager.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("miniPoject.bookManager")
public class AppConfiguration implements WebMvcConfigurer {
    // ① Kích hoạt Thymeleaf Layout Dialect
    //    → Cho phép dùng layout:decorate, layout:fragment
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    // ② Tìm file template theo prefix + suffix
    //    → "home" sẽ tìm /WEB-INF/templates/home.html
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver =
                new SpringResourceTemplateResolver();
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode("HTML");
        return resolver;
    }

    // ③ Bộ máy xử lý template Thymeleaf
    //    → Gắn Resolver + Dialect + bật Spring EL (${...})
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        engine.addDialect(layoutDialect());
        engine.setEnableSpringELCompiler(true);
        return engine;
    }

    // ④ Kết nối Thymeleaf vào Spring MVC
    //    → setOrder(1): ưu tiên số 1
    //    → UTF-8: hiển thị tiếng Việt đúng
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        resolver.setOrder(1);
        return resolver;
    }

    // ⑤ Cho phép load file tĩnh: CSS, JS, ảnh
    //    → Đặt file trong thư mục /resources/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }
}