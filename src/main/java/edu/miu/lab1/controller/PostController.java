package edu.miu.lab1.controller;

import edu.miu.lab1.entity.dto.PostDto;
import edu.miu.lab1.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;


    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PostDto> getAll() {
        return postService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody PostDto p) {
        postService.save(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PostDto>> getById(@PathVariable("id") int id) {
        var product = postService.getById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<PostDto> resource = EntityModel.of(product);

        // Self-link
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getById(id))
                .withSelfRel());

        // Add all products
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PostController.class).getAll())
                .withRel("all-products"));

        CacheControl cacheControl = CacheControl.maxAge(1, TimeUnit.DAYS)
                .cachePublic(); // Use cachePrivate() if it should only be cached by browser, not proxies

        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .body(resource);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        postService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int productId, @RequestBody PostDto p) {
        postService.update(productId,p);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/authors/filter")
    public List<PostDto> getPostsByAuthor(@RequestParam(value = "q" ,required = false) String author, @RequestParam(value = "exact" ,required = false) int exact) {
        return author==null?
                postService.findAll():
                postService.findPostByAuthor(author, exact);
    }
}
