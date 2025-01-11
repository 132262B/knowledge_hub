package app.springcloudfunctionjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;
import java.util.function.Supplier;


@SpringBootApplication
public class SpringCloudFunctionJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudFunctionJavaApplication.class, args);
    }

    @Bean
    public Function<ExampleRequest, String> post() {

        return value -> "data + " + value.name + " + " + value.email;
    }

    public record ExampleRequest(
        String name,
        String email
    ) {

    }

}
