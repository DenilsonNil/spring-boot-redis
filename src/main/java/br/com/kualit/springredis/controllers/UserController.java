package br.com.kualit.springredis.controllers;

import br.com.kualit.springredis.model.record.User;
import br.com.kualit.springredis.services.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public ResponseEntity<User> createNew(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return userService.listAll();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<User> findByCpf(@PathVariable String cpf) {
        val entity = userService.findByCpf(cpf);
        return ResponseEntity.ok(new User(entity.getName(), entity.getCpf()));
    }
}
