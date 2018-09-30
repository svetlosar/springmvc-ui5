package com.github.springui5.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Simple domain object. Uses JSR-303 validation annotations. Converted to/from JSON automatically.
 */
public class User implements Serializable {

    /**
	 * Generated serialVersionUID.
	 */
	private static final long serialVersionUID = 1844754847684801878L;

	private static long offset = 0L;

    private long id;

    @NotNull
    @NotBlank
    private String name;
    
    @NotNull
    @NotBlank
    private String familyName;
    
    @NotNull
    @NotBlank
    private String email;
    
    @NotNull
    @NotBlank
    //TODO: It should be enumeration of User and Administrator
    private String role;
    
    @NotNull
    @NotBlank
    private String password;

    /**
     * Returns a new value for {@code id} attribute. Uses timestamp adjusted with the static offset. Used only for
     * illustration.
     */
    public static long newId() {
        return System.currentTimeMillis() + offset++;
    }

    public User() {
        // default constructor
    }

    public User(String name, String familyName, String email, String role, String password) {
        this.id = User.newId();
        this.name = name;
        this.familyName = familyName;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
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
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof User && ((User) obj).getId() == id;
    }

    @Override
    public String toString() {
        return "User [id: " +
                id +
                ", name: " +
                name +
                ", familyName: " +
                familyName +
                "]";
    }
}
