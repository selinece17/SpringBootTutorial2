package edu.carroll.cs341_demo.service;

import java.util.List;

import edu.carroll.cs341_demo.jpa.model.Login;
import edu.carroll.cs341_demo.jpa.repo.LoginRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private final LoginRepository loginRepo;

    public LoginServiceImpl(LoginRepository loginRepo) {
        this.loginRepo = loginRepo;
    }

    @Override
    public boolean validateUser(String username, String password) {
        List<Login> users = loginRepo.findByUsernameIgnoreCase(username);
        if (users.size() != 1) return false;

        Login u = users.get(0); // JDK 17 compatible
        String userProvidedHash = Integer.toString(password.hashCode()); // demo only
        return u.getHashedPassword().equals(userProvidedHash);
    }
}
