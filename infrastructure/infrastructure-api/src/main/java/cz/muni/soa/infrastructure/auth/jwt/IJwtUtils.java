package cz.muni.soa.infrastructure.auth.jwt;


import cz.muni.soa.infrastructure.auth.model.User;

public interface IJwtUtils {

    String generate();

    boolean validate();

    User getTokenUser();
}
