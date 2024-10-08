package com.elearningwebappapi.fypapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.Map;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping(path="/api/user")
public class UserController {
    @Autowired
    private UserRepository userRespository;

    @GetMapping(value = {"", "/"})
    public ResponseEntity index() {
        return ResponseEntity.ok(userRespository.findAll());
    }

    // Login
    @PostMapping(value = "/login")
    public ResponseEntity checkUser(@RequestBody Map<String, String> body) {

        String username = body.get("username");
        String password = body.get("password");

        Optional<User> Userlists = userRespository.findByUsername(username);

        if(Userlists.isPresent()){
            if(Userlists.get().getUsername().equals(username) && Userlists.get().getPassword().equals(password)) {
                return ResponseEntity.ok(Userlists.get());
            } else {
                return ResponseEntity.badRequest().body("Password incorrect!");
            }
        } else {
            return ResponseEntity.badRequest().body("Current username not found");
        }
    }

    // Registation
    @PostMapping(value = "/reg")
    public ResponseEntity regUser(@RequestBody Map<String, String> body) {

        String username = body.get("username");
        String password = body.get("password");

        Optional<User> Userlists = userRespository.findByUsername(username);

        if(Userlists.isPresent()){
            return ResponseEntity.badRequest().body("User existed");
        }else {
            return ResponseEntity.ok(userRespository.save(new User(username, password)));
        }

    }
}

