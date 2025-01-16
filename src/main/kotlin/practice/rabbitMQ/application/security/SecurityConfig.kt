package practice.rabbitMQ.application.security

import com.fasterxml.jackson.databind.ObjectMapper
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.security.config.Customizer
import org.springframework.security.web.access.intercept.AuthorizationFilter
import practice.rabbitMQ.application.error.ExceptionFilter
import java.util.*


@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
class SecurityConfig(
    private val objectMapper: ObjectMapper,
) {

    @Bean
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .cors(Customizer.withDefaults())
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

        http
            .authorizeHttpRequests { authorize ->
                authorize
                    // /healthcheck
                    .requestMatchers(HttpMethod.GET, "/").permitAll()
                    .anyRequest().permitAll()
            }
        http.addFilterBefore(ExceptionFilter(objectMapper), AuthorizationFilter::class.java)

        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = Arrays.asList("OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
        configuration.allowCredentials = false
        configuration.addAllowedHeader("*")

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
