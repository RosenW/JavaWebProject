package project.models;

import project.entities.Role;
import project.validators.IsPasswordsMatching;

import javax.validation.constraints.Size;

@IsPasswordsMatching
public class RegisterUserModel {

    private String email;

    @Size(min = 4, message = "Password too short")

    private String password;

    private String confirmPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
