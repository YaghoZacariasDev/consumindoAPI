import java.util.List;

import interfaces.IPostService;
import model.Post;

public class App {
    public static void main(String[] args) throws Exception {
        IPostService postService = new services.PostsServices();
        List<Post> posts = postService.getPosts();

        posts.forEach(post -> {
            System.out.println("-----------------------");
            System.out.println("Title: - " + post.getTitle());
            System.out.println("Body:" + post.getBody());
            System.out.println("----------------------");
        });
        Post postById = postService.getPostById(1);
        System.out.println(postById);

            Post post01 = new Post(1, "god", "lalalalal" );
            Post createdPost = postService.createPost(post01);
           System.out.println(createdPost);

           Post updatedPost = postService.updatePost(post01, 3);
            System.out.println(updatedPost);

            postService.deletePost(7);


    }

}
