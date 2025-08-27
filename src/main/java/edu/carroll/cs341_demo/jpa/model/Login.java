package edu.carroll.cs341_demo.jpa.model;



import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "login")
public class Login {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    // Column name intentionally "password" in DB; field is hashedPassword
    @Column(name = "password", nullable = false)
    private String hashedPassword;

    // --- required by JPA
    public Login() {
    }

    // convenience ctor that accepts RAW password and hashes it (demo only)
    public Login(String username, String rawPassword) {
        this.username = username;
        setRawPassword(rawPassword);
    }

    // DEMO ONLY: do NOT do this in real apps; use BCrypt/Argon2
    public void setRawPassword(String rawPassword) {
        this.hashedPassword = Integer.toString(rawPassword.hashCode());
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getHashedPassword() { return hashedPassword; }
    public void setHashedPassword(String hashedPassword) { this.hashedPassword = hashedPassword; }

    private static final String EOL = System.lineSeparator();
    private static final String TAB = "\t";

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("Login @ ").append(super.toString()).append("[").append(EOL);
        b.append(TAB).append("username=").append(username).append(EOL);
        b.append(TAB).append("hashedPassword=").append("****").append(EOL);
        b.append("]").append(EOL);
        return b.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Login)) return false;
        Login that = (Login) o;
        return username.equals(that.username) && hashedPassword.equals(that.hashedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, hashedPassword);
    }
}
