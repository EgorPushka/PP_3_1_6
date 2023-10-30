package testgroup;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class Main {

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    private static String cookie;


    public void getUsers() {

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String GET = "http://94.198.50.185:7081/api/users";
        ResponseEntity<String> response = restTemplate.exchange(GET, HttpMethod.GET, entity, String.class);
        cookie = response.getHeaders().getFirst("Set-Cookie");
//        System.out.println(response);
        System.out.println(response.getBody());
    }

    public void createUser() {

        User user = new User(3, "James", "Brown", 60);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        String POST = "http://94.198.50.185:7081/api/users";
        ResponseEntity<String> response = restTemplate.exchange(POST, HttpMethod.POST, entity, String.class);
        System.out.print(response.getBody());
    }

    private void updateUser() {

        User user1 = new User(3, "Thomas", "Shelby", 60);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<>(user1, headers);
        String PUT = "http://94.198.50.185:7081/api/users";
        ResponseEntity<String> response = restTemplate.exchange(PUT, HttpMethod.PUT, entity, String.class);
        System.out.print(response.getBody());
    }

    private void deleteUser() {

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<>(headers);
        String DELETE = "http://94.198.50.185:7081/api/users/3";
        ResponseEntity<String> response = restTemplate.exchange(DELETE, HttpMethod.DELETE, entity, String.class);
        System.out.print(response.getBody());
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.getUsers();
        main.createUser();
        main.updateUser();
        main.deleteUser();
    }




}