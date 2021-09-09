package com.web.demo;

import com.web.demo.dtos.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringBootOracleK8sApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOracleK8sApplication.class, args);
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    WebClient.Builder webClientBuilder;

    @Autowired
    public void setWebClient(WebClient.Builder cropService) {
        this.webClientBuilder = webClientBuilder;
    }

    private final Environment environment;

    public SpringBootOracleK8sApplication(final Environment environment) {
        this.environment = environment;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("SpringBootOracleK8sApplication run Method ");
        /*String name = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/common/userDetails")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println("Name is==="+name);*/

        WebClient client = WebClient.create();

        WebClient.ResponseSpec responseSpec = client.get()
                .uri("http://localhost:8081/common/userDetails")
                .retrieve();
        String responseBody = responseSpec.bodyToMono(String.class).block();
        System.out.println("responseBody===" + responseBody);

        System.out.println("Application Name===="+getPropertyFromKey("spring.application.name"));
        System.out.println("User Name===="+getPropertyFromKey("user.address.userName"));
        System.out.println("Password===="+getPropertyFromKey("user.address.password"));
        System.out.println("Oracle User Name==="+getPropertyFromKey("spring.datasource.username"));
        System.out.println("Oracle Password==="+getPropertyFromKey("spring.datasource.password"));

        System.out.println("Windows==="+getPropertyFromKey("csv.read.windPrefix"));
        System.out.println("Linux==="+getPropertyFromKey("csv.read.linuxPrefix"));
        System.out.println("Mac==="+getPropertyFromKey("csv.read.macPrefix"));

        //Operating system name
        System.out.println(System.getProperty("os.name"));

        //Operating system version
        System.out.println(System.getProperty("os.version"));

        //Path separator character used in java.class.path
        System.out.println(System.getProperty("path.separator"));

        //User working directory
        System.out.println(System.getProperty("user.dir"));

        //User home directory
        System.out.println(System.getProperty("user.home"));

        //User account name
        System.out.println(System.getProperty("user.name"));

        //Operating system architecture
        System.out.println(System.getProperty("os.arch"));

        //Sequence used by operating system to separate lines in text files
        System.out.println(System.getProperty("line.separator"));

        System.out.println(System.getProperty("java.version")); //JRE version number

        System.out.println(System.getProperty("java.vendor.url")); //JRE vendor URL

        System.out.println(System.getProperty("java.vendor")); //JRE vendor name

        System.out.println(System.getProperty("java.home")); //Installation directory for Java Runtime Environment (JRE)

        System.out.println(System.getProperty("java.class.path"));

        System.out.println(System.getProperty("file.separator"));
    }

    private void getPostById(int i) {
        System.out.println("getPostById===");
        WebClient client = WebClient.create();

        WebClient.ResponseSpec responseSpec = client.get()
                .uri("http://localhost:8081/web/comsume/" + i)
                .retrieve();
        Post responseBody = responseSpec.bodyToMono(Post.class).block();
        System.out.println("responseBody===" + responseBody.toString());
    }

    @Bean
    public ApplicationRunner osLogger(Environment environment){
        return (arguments) -> {
            System.out.println("OS Version==="+environment.getProperty("os.version")+
                    "===OS Name==="+environment.getProperty("os.name"));
        };
    }

    private void getPost() {
    }

    public void create(Post post) {
    }

    private String getPropertyFromKey(String key) {
        return this.environment.getProperty(key);
    }
}
