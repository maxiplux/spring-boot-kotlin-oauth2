package livemarket.business.kotlin.app.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import java.util.*

@Configuration
@EnableResourceServer
class ResourceServerConfig : ResourceServerConfigurerAdapter() {

    @Autowired
    lateinit  var unauthorizedHandler: JwtAuthenticationEntryPoint

    override fun configure(http: HttpSecurity) {
        http.cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/common/**", "/v2/api-docs", "/configuration/ui", "/swagger-resources",
                        "/configuration/security", "/swagger-ui.html", "/webjars/**",
                        "/auth/singin/**",
                        "/metrics/**",
                        "/manage/**",
                        "/health/**", "/info/**",
                        "/api/clientes", "/api/clientes/page/**", "/api/uploads/img/**", "/images/**",
                        "/api/auth/**",
                        "/api/test/**",
                        "/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/v3/api-docs/**",
                        "/v3/**",
                        "/v2/**",
                        "/auth/token/**",
                        "/swagger-resources/**"
                ) /*.antMatchers(HttpMethod.GET, "/api/clientes/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/clientes/upload").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
                .antMatchers("/api/clientes/ **").hasRole("ADMIN")*/
                .permitAll()
                .antMatchers(HttpMethod.GET, "/protected").hasRole("ADMIN")
                .antMatchers("/api/v1/password/**")
                .permitAll()
                .antMatchers("/api/v1/auth/**")
                .permitAll()
                .antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
                .permitAll()
                .and().cors().configurationSource(corsConfigurationSource())
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val config = CorsConfiguration()
        config.allowedOrigins = Arrays.asList("http://localhost:4200", "*")
        config.allowedMethods = Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")
        config.allowCredentials = true
        config.allowedHeaders = Arrays.asList("Content-Type", "Authorization")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return source
    }

    @Bean
    fun corsFilter(): FilterRegistrationBean<CorsFilter>? {
        val bean = FilterRegistrationBean(CorsFilter(corsConfigurationSource()!!))
        bean.order = Ordered.HIGHEST_PRECEDENCE
        return bean
    }
}
