package edu.carroll.cs341_demo.service;

import java.util.List;

import edu.carroll.cs341_demo.jpa.model.Login;
import edu.carroll.cs341_demo.jpa.repo.LoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    private final LoginRepository loginRepo;

    public LoginServiceImpl(LoginRepository loginRepo) {
        this.loginRepo = loginRepo;
    }

    @Override
    public boolean validateUser(String username, String password) {
        // Never log the raw password
        log.debug("validateUser: user '{}' attempted login", username);

        // DB lookup
        List<Login> users = loginRepo.findByUsernameIgnoreCase(username);

        // Expect exactly one match
        if (users.size() != 1) {
            log.debug("validateUser: found {} users for username '{}'", users.size(), username);
            return false;
        }

        // Java 17 list access
        Login u = users.get(0);

        // Demo-only hashing (do NOT do this in production)
        final String userProvidedHash = Integer.toString(password.hashCode());
        if (!u.getHashedPassword().equals(userProvidedHash)) {
            log.debug("validateUser: password !match for '{}'", username);
            return false;
        }

        log.info("validateUser: successful login for {}", username);
        return true;
    }
}
