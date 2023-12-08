package cz.muni.soa.infrastructure.security.model;

public enum Role {

    BASIC("basic"),
    DEVELOPER("dev"),
    ADMINISTRATOR("admin");

    public final String name;

    Role(String role) {
        name = role;
    }
}
