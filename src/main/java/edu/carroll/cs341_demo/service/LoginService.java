package edu.carroll.cs341_demo.service;

import edu.carroll.cs341_demo.web.form.LoginForm;



public interface LoginService {
    /**
     * @param username raw username from the client
     * @param password raw password from the client
     * @return true if credentials match a known user
     */
    boolean validateUser(String username, String password);
}
