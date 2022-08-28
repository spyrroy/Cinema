package entity;

import java.util.Objects;

public class User {
    private int id;
    private String first_name;
    private String second_name;
    private String password;
    private Role role;

    public User(int id, String first_name, String second_name, String password, Role role) {
        this.id = id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.password = password;
        this.role = role;
    }

    public User(String first_name, String second_name, String password, Role role) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return id == user.id && Objects.equals(first_name, user.first_name) && Objects.equals(second_name, user.second_name) && Objects.equals(password, user.password) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, second_name, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
