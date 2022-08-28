package com.my.entity;

import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String first_name;
    private String second_name;
    private String password;
    private String email;
    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private Role role;

    public User() {
    }

    public User(String login, String first_name, String second_name, String password, String email, String salt) {
        this.login = login;
        this.first_name = first_name;
        this.second_name = second_name;
        this.password = password;
        this.email = email;
        this.salt = salt;
    }

    public User(int id, String login, String first_name, String second_name, String password, String email, String salt, Role role) {
        this.id = id;
        this.login = login;
        this.first_name = first_name;
        this.second_name = second_name;
        this.password = password;
        this.email = email;
        this.salt = salt;
        this.role = role;
    }

    public User(String login, String first_name, String second_name, String password, String email, String salt, Role role) {
        this.login = login;
        this.first_name = first_name;
        this.second_name = second_name;
        this.password = password;
        this.email = email;
        this.salt = salt;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(first_name, user.first_name) && Objects.equals(second_name, user.second_name) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, first_name, second_name, password, email, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", salt='" + salt + '\'' +
                ", role=" + role +
                '}';
    }
}
