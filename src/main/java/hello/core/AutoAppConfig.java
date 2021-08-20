package hello.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// 컴포넌트 스캔은 이름 그대로 '@Component' 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다.
@ComponentScan(
        // AppConfig 제외 때문에 또는 @Configuration 이 붙은 것 제외 때문에
        // 기존 예제 코드를 최대한 남기고 유지하기 위한 방법.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),
        // "hello.core.member"에서만 component 를 찾아라 -> 탐색할 패키지의 시작 위치를 지정한다. 이 패키지를 포함해서 하위패키지를 모두 탐색한다.
        // 하지만 이게 default. 즉, @ComponentScan(AutoAppConfig) 이 있는 패키지 를 포함한 하위패키지를 모두 탐색.
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class
)
public class AutoAppConfig {

}
