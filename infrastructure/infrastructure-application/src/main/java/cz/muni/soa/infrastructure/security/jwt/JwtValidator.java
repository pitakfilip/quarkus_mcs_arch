package cz.muni.soa.infrastructure.security.jwt;

import cz.muni.soa.infrastructure.security.model.User;
import cz.muni.soa.infrastructure.security.model.UserBuilder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.xml.bind.DatatypeConverter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@ApplicationScoped
public class JwtValidator {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ROLES = "roles";

    private static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private final Key key;
    private final String issuer;
    private final String secret;

    public JwtValidator(@ConfigProperty(name = "wildTribes.security.jwt.key") String key,
                        @ConfigProperty(name = "wildTribes.security.jwt.issuer") String issuer) {
        byte[] secretBytes = DatatypeConverter.parseBase64Binary(key);
        this.key = new SecretKeySpec(secretBytes, signatureAlgorithm.getJcaName());
        this.issuer = issuer;
        this.secret = key;
    }

    public User validateAndFetchUser(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                .parseClaimsJws(token)
                .getBody();
        
        return new UserBuilder()
                .setId(claims.get(ID).toString())
                .setRoles(claims.get(ROLES).toString())
                .setName(claims.get(NAME).toString()).createUser();
    }
}
