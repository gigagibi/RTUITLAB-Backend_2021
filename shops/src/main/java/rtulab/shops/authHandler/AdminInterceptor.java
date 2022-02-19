package rtulab.shops.authHandler;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
public class AdminInterceptor implements HandlerInterceptor {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeaders("Authorization").nextElement().substring(7);
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("roles", "[ROLE_ADMIN]")
                    .build();
            DecodedJWT decodedJWT = JWT.decode(token);
            DecodedJWT jwt = verifier.verify(token);
            String claim = jwt.getPayload();
        } catch (JWTVerificationException exception){
            response.sendError(403, "Unauthorized");
            response.getOutputStream().write("Unauthorized".getBytes());
            return false;
        }
        return true;
    }
}
