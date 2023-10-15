package br.com.kualit.springredis.services;

import br.com.kualit.springredis.model.entities.UserEntity;
import br.com.kualit.springredis.model.record.User;
import br.com.kualit.springredis.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Log4j2
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> createNewUser(User user) {
        val userDB = new UserEntity();

        copyProperties(user, userDB);
        userRepository.save(userDB);
        return ResponseEntity.created(URI.create("")).body(user);
    }
    public ResponseEntity<List<User>> listAll() {
        return ResponseEntity.ok(
                userRepository.
                findAll()
                .stream()
                .map(u -> new User(u.getName(), u.getCpf()))
                .collect(Collectors.toList()));
    }


    @Cacheable("users")
    public UserEntity findByCpf(String userCpf) {
        log.info("Getting from DB");
        return userRepository.findByCpf(userCpf).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
