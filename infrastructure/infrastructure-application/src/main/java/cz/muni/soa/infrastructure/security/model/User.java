package cz.muni.soa.infrastructure.security.model;

import java.io.Serializable;
import java.security.Principal;

public class User implements Principal, Serializable {

    private long id;

    private Role role;

    private String name;

    private String password;

    public User(long id, Role role, String name) {
        this.id = id;
        this.role = role;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return id == user.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role.name;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
