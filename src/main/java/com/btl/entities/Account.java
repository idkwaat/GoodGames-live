package com.btl.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "accountid", length = 36)
    private String accountId;

    @NotBlank
    @Size(max = 64)
    @Column(name = "username", length = 64, unique = true)
    private String username;

    @NotBlank
    @Size(max = 64)
    @Column(name = "firstname", columnDefinition = "nvarchar(64)")
    private String firstname;

    @NotBlank
    @Size(max = 64)
    @Column(name = "lastname", columnDefinition = "nvarchar(64)")
    private String lastname;

    @NotBlank
    @Email
    @Size(max = 64)
    @Column(name = "email", length = 64, unique = true)
    private String email;

    @NotBlank
    @Column(name = "password", length = 256)
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "accountroles", joinColumns = @JoinColumn(name = "accountid",columnDefinition = "nvarchar(36)"), inverseJoinColumns = @JoinColumn(name = "roleid"))
	private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "account")
	private Set<Order> orders=new HashSet<Order>();

    public Account() {}

    public Account(String username, String firstname, String lastname, String email, String password, boolean enabled) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    @PrePersist
    private void prePersist() {
        if (this.accountId == null) {
            this.accountId = UUID.randomUUID().toString();
        }
    }

    // Getters và Setters (giống như trước)

    public String getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

	public void setAccountId(UUID randomUUID) {
		// TODO Auto-generated method stub
		
	}
}
