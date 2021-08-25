package com.web.demo.controls;

import com.web.demo.dtos.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("web")
public class WebClientController {

    private final String POST_OF_API = "http://jsonplaceholder.typicode.com/posts/9";
    private final String POST_BY_ID_API = "http://jsonplaceholder.typicode.com/posts/{id}";
    private final String POST_API = "http://jsonplaceholder.typicode.com/posts";

    WebClient.Builder webClientBuilder;

    @Autowired
    public void setWebClient(WebClient.Builder cropService) {
        this.webClientBuilder = webClientBuilder;
    }

    /**
     * getPost() method call the defined API (static post)
     */
    @GetMapping("/comsume")
    public Post getPost() {

        return webClientBuilder.build()
                .get()
                .uri(POST_OF_API)
                .retrieve()
                .bodyToMono(Post.class)
                .block();
    }

    /**
     * getPostById() method call the API with the post id send by the user (dynamic post)
     */
    @GetMapping("/comsume/{id}")
    public Post getPostById(@PathVariable("id") Integer postId) {

        return webClientBuilder.build()
                .get()
                .uri(POST_BY_ID_API, postId)
                .retrieve()
                .bodyToMono(Post.class)
                .block();
    }

    /**
     * getAllPost() method call the API which returns the array or list of post
     */
    @GetMapping("/all")
    public Post[] getAllPost() {

        return webClientBuilder.build()
                .get()
                .uri(POST_API)
                .retrieve()
                .bodyToMono(Post[].class)
                .block();
    }

    @PostMapping("create")
    public Post createPost(@RequestBody Post post) {
        post.setId(10);
        return post;
    }
}