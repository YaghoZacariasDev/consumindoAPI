package services;

import com.google.gson.Gson;
import interfaces.IPostService;
import model.Post;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PostsServices implements IPostService {
    private final Gson gson = new Gson();

    public List<Post> getPosts() {
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                Type listType = new TypeToken<ArrayList<Post>>() {}.getType();
                return gson.fromJson(response.toString(), listType);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
        }
        return new ArrayList<>();
    }

    public createPost(Post post) {
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(gson.toJson(post));
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                return gson.fromJson(response.toString(), Post.class);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
        }
        return null;
    }

    public Post getPostById(Integer id) {
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                return gson.fromJson(response.toString(), Post.class);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
        }
        return null;
    }

    public Post updatePost(Post post) {
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts/" + post.getId());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(gson.toJson(post));
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                return gson.fromJson(response.toString(), Post.class);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
        }
        return null;
    }

    public void deletePost(Integer id) {
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Post with ID " + id + " deleted successfully.");
            } else {
                System.out.println("Failed to delete post with ID " + id);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
        }
    }
}
