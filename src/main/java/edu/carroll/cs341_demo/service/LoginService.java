package edu.carroll.cs341_demo.service;

import edu.carroll.cs341_demo.web.form.LoginForm;

public interface LoginService {
    /**
     * Given a loginForm, determine if the information provided is valid, and the user exists in the system.
     * @param loginForm - Data containing user login information, such as username and password.
     * @return true if data exists and matches what's on record, false otherwise
     */
    boolean validateUser(LoginForm loginForm);
}
