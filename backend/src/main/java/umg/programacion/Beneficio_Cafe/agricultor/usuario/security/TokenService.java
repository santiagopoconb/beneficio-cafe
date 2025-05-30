package umg.programacion.Beneficio_Cafe.agricultor.usuario.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.Usuario;
import umg.programacion.Beneficio_Cafe.beneficio.usuarioBeneficio.UsuarioBeneficio;
import umg.programacion.Beneficio_Cafe.pesaje.usuarioPesaje.UsuarioPesaje;

import java.security.AlgorithmConstraints;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("{api.security.secret")
    private String apiSecret;

    public String generarToken(UserDetails usuario) {
        String esquema;
        String rol;
        Long idUsuario;

        if(usuario instanceof Usuario usuarioAgricultor) {
            esquema = "agricultor";
            rol = usuarioAgricultor.getIdRol().getRol();
            idUsuario = usuarioAgricultor.getIdUsuario();
        } else if(usuario instanceof UsuarioBeneficio usuarioBeneficio) {
            esquema = "beneficio";
            rol = usuarioBeneficio.getIdRol().getRol();
            idUsuario = usuarioBeneficio.getIdUsuario();
        } else if(usuario instanceof UsuarioPesaje usuarioPesaje) {
            esquema ="pesaje";
            rol = usuarioPesaje.getIdRol().getRol();
            idUsuario = usuarioPesaje.getIdUsuario();
        } else {
            throw new RuntimeException("Tipo de usuario no encontrado");
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("beneficio cafe")
                    .withSubject(usuario.getUsername())
                    .withClaim("idUsuario", idUsuario)
                    .withClaim("rol", rol)
                    .withClaim("esquema", esquema)
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
    }

    public String getSubject(String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token viene nulo o vacio");
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("beneficio cafe")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception);
        }
        if(verifier.getSubject() == null){
            throw new RuntimeException("Verificacion invalida");
        }
        return verifier.getSubject();
    }
}
