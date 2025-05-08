package com.btl.entities;

import java.util.Set;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {

    @NotBlank(message = "Hãy nhập tên của bạn")
    @Size(min = 3, max = 20, message = "Tên phải từ 3 đến 20 ký tự")
    private String firstname;

    @NotBlank(message = "Hãy nhập họ của bạn")
    @Size(min = 1, max = 20, message = "Họ phải từ 1 đến 20 ký tự")
    private String lastname;

    @NotBlank(message = "Hãy nhập email")
    @Email(message = "Email phải đúng định dạng")
    private String email;
    
    @NotBlank(message = "Vui lòng nhập tên đăng nhập")
    @Size(min = 3, max = 64, message = "Tên đăng nhập phải từ 3 đến 64 ký tự")
    private String username;


    @NotBlank(message = "Vui lòng nhập mật khẩu")
    @Size(min = 8, max = 15, message = "Mật khẩu phải từ 8 đến 15 ký tự")
    private String password;

    @NotBlank(message = "Vui lòng xác nhận lại mật khẩu")
    @Size(min = 8, max = 15, message = "Mật khẩu xác nhận phải từ 8 đến 15 ký tự")
    private String repassword;

    @AssertTrue(message = "Mật khẩu và xác nhận mật khẩu không khớp")
    public boolean isPasswordMatching() {
        if (password == null || repassword == null) {
            return false;
        }
        return password.equals(repassword);
    }

    // Getters and Setters
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


	public void setRoles(Set<Role> roles) {
		// TODO Auto-generated method stub
		
	}
}
