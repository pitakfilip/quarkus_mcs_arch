package cz.muni.soa.kingdom.auth;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.xml.bind.DatatypeConverter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@ApplicationScoped
public class JwtGenerator {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ROLES = "roles";

    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private final Key key;
    private final String issuer;

    public JwtGenerator(@ConfigProperty(name = "wildTribes.security.jwt.key") String key,
                        @ConfigProperty(name = "wildTribes.security.jwt.issuer") String issuer) {
        byte[] secretBytes = DatatypeConverter.parseBase64Binary(key);
        this.key = new SecretKeySpec(secretBytes, signatureAlgorithm.getJcaName());
        this.issuer = issuer;
    }

    public String generateToken(long id, String name, String roles){
        LocalDateTime now = LocalDateTime.now();
        Date validUntil = convertLocalDateTimeToDate(now.plusDays(1));
        
        JwtBuilder builder = Jwts.builder()
                .claim(ID, id)
                .claim(NAME, name)
                .claim(ROLES, roles)
                .setIssuedAt(convertLocalDateTimeToDate(now))
                .signWith(signatureAlgorithm, key);
        builder.setExpiration(validUntil);
        return builder.compact();
    }

    private Date convertLocalDateTimeToDate(LocalDateTime now){
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }
}
