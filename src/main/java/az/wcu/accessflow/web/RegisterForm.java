package az.wcu.accessflow.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// Отдельный класс, а не сущность: через форму нельзя передать role
public class RegisterForm {

    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Maximum 100 characters")
    private String fullName;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Must be 3-50 characters")
    @Pattern(regexp = "[a-zA-Z0-9_.]+", message = "Only Latin letters, digits, _ and . are allowed")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 72, message = "Password must be 6-72 characters")
    private String password;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}