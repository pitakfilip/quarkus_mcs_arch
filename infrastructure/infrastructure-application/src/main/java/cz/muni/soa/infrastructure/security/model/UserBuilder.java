package cz.muni.soa.infrastructure.security.model;

public class UserBuilder {
    private String id;
    private String role;
    private String name;

    public UserBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public UserBuilder setRoles(String role) {
        this.role = role;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public User createUser() {
        return new User(Long.parseLong(id), Role.valueOf(role), name);
    }
}