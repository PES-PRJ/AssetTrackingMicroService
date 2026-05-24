package bt.edu.gcit.assettracking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // 1. Permissions for creating assets at the root collection level
                        .requestMatchers(HttpMethod.POST, "/api/assets").hasAuthority("ASSETMANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/assets/").hasAuthority("ASSETMANAGER")

                        // 2. Explicit permissions for mutating specific asset records by ID
                        .requestMatchers(HttpMethod.PUT, "/api/assets/{id}").hasAuthority("ASSETMANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/assets/{id}").hasAuthority("ASSETMANAGER")

                        // 3. Open GET requests to any authenticated user (ADMIN, ASSETMANAGER,
                        // EMPLOYEE)
                        .requestMatchers(HttpMethod.GET, "/api/assets", "/api/assets/", "/api/assets/{id}")
                        .authenticated()

                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}