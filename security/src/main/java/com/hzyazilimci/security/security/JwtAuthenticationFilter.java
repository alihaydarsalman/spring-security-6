package com.hzyazilimci.security.security;

import com.hzyazilimci.security.user.JwtManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

/**
 * @author hzyazilimci
 */

/**
 * Client' tan gelen bir istegi filtreden/filtrelerden gecirmeliyiz.
 * Farkli birkac filtre yontemi mevcuttur, birden fazla filter kullanabilecegimiz gibi bu filtrelerin sirasini da kendimiz belirleyebiliriz.
 * JwtAuthenticationFilter sinifimizda kullanilabilir farkli yontemler vardir.
 * Biz burada her istek icin islemesi icin OncePerRequestFilter' tan kalitilan bir sinif yazacagiz.
 * Her istekte bir filtreleme yapilmis olacak.
 *
 * @RequiredArgsConstructor notasyonu sinif icerisinde herhangi final field var ise bu fieldlari parametre alan bir constructor metot olusturacaktir.
 **/

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtManager jwtManager;
    static String HEADER_STRING= "Authorization";
    static int lengthOfBearerWithSpace = 7;

    /**
     * requesti alip isleyebilecegimiz bir metot olan doFilterInternal metodunun parametreleri nullable olmamali,
     * bunu kontrol altina almadigimizda parametreler warning durumunda olacaktir.
     * bu sebepten @NonNull annotation ile isaretlendi.
     **/
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        /**
         * Json Web Token yapisini incelemek icin bakabilirsiniz: https://jwt.io/
         **/
        final String authHeader = request.getHeader(HEADER_STRING);
        final String jwToken;
        final String userName;

        if (authHeader == null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }

        jwToken = authHeader.substring(lengthOfBearerWithSpace);
        userName = jwtManager.extractUserName(jwToken);// TODO: extract the username from JWToken;

    }
}
