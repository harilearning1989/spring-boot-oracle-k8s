package com.web.demo;

import com.web.demo.dtos.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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

        Post post = new Post();
        post.setBody("Post Body");
        post.setTitle("Post Title");
        post.setUserId(123);
        //create(post);
        //getPost();
        //getPostById(10);
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

    private void getPost() {
    }

    public void create(Post post) {
    }
}
