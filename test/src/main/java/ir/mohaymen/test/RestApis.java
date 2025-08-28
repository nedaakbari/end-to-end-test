package ir.mohaymen.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestApis {

    private final List<Users> users = new ArrayList<>();

    public RestApis() {
        users.add(new Users(1, "neda", "akbari"));
        users.add(new Users(2, "ali", "zare"));
        users.add(new Users(3, "nima", "asgari"));
    }

    @GetMapping("/users")
    public List<Users> getUsers() {
        return users;
    }

    @PostMapping("/add-users")
    public List<Users> postUsers(@RequestBody Users user) {
        users.add(user);
        return users;
    }
}
