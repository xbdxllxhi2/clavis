package xyz.clavis.security.utils;
//
//import static org.springframework.http.HttpMethod.GET;
//import static org.springframework.http.HttpMethod.POST;
//import static org.springframework.http.HttpMethod.PUT;
//import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
//
//import java.util.Arrays;
//import org.keycloak.adapters.KeycloakConfigResolver;
//import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
//import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
//import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
//import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
//import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@KeycloakConfiguration
//  public class KeycloakSecurityConfiguration extends KeycloakWebSecurityConfigurerAdapter {
//    private static final String CORS_ALLOWED_HEADERS = "origin,content-type,accept,x-requested-with,Authorization";
//    private long corsMaxAge = 60;
//    private static final Logger logger = LoggerFactory.getLogger(KeycloakSecurityConfiguration.class);
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) {
//      SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
//      grantedAuthorityMapper.setPrefix("ROLE_");
//      KeycloakAuthenticationProvider keycloakAuthenticationProvider =
//          keycloakAuthenticationProvider();
//      keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
//      auth.authenticationProvider(keycloakAuthenticationProvider);
//    }
//    @Bean
//    public KeycloakConfigResolver keycloakConfigResolver() {
//      return new KeycloakSpringBootConfigResolver();
//    }
//    @Bean
//    @Override
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//      return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//      super.configure(http);
//      logger.info("Inside configure method");
//      http.cors()
//          .and()
//          .authorizeRequests()
//          .antMatchers("/*")
//          .authenticated()
//          .and()
//          .csrf()
//          .and()
//          .logout()
//          .logoutRequestMatcher(new AntPathRequestMatcher("logout.do", "GET"));
//    }
//
//  @Override
//  public void init(WebSecurity builder) throws Exception {
//
//  }
//
//  @Override
//    public void configure(WebSecurity web) throws Exception {
//      /* @formatter:off */
//      web.ignoring()
//          .mvcMatchers("/js/**")
//          .and()
//          .ignoring()
//          .mvcMatchers("/css/**")
//          .and()
//          .ignoring()
//          .mvcMatchers("/images/**")
//          .and()
//          .ignoring()
//          .mvcMatchers("/html/**")
//          .and()
//          .ignoring()
//          .antMatchers(HttpMethod.OPTIONS, "/**")
//          .and()
//          .ignoring()
//          .antMatchers("/web");
//      /* @formatter:on */
//    }
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//      CorsConfiguration configuration = new CorsConfiguration();
//      configuration.setAllowedOrigins(Arrays.asList("*"));
//      configuration.setAllowedMethods(
//          Arrays.asList(GET.name(), POST.name(), PUT.name(), DELETE.name()));
//      configuration.setAllowedHeaders(Arrays.asList(CORS_ALLOWED_HEADERS.split(",")));
//      configuration.setMaxAge(corsMaxAge);
//      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//      source.registerCorsConfiguration("/**", configuration);
//      return source;
//    }
//  }
//
