package net.chrisgrollier.amsuite.osscat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@Profile("prod")
@OpenAPIDefinition(info = @Info(title = "Open Source Software Catalog API", version = "v1"))
//@formatter:off
@SecurityScheme(
		name = "bearerAuth", 
		type = SecuritySchemeType.OAUTH2, 
		flows = @OAuthFlows(authorizationCode = 
			@OAuthFlow(
					authorizationUrl = "http://localhost:8180/auth/realms/chris02/protocol/openid-connect/auth",
					tokenUrl = "http://localhost:8180/auth/realms/chris02/protocol/openid-connect/token"
					)
		), 
		openIdConnectUrl = "http://localhost:8180/auth/realms/chris02/.well-known/openid-configuration", 
		bearerFormat = "JWT", 
		scheme = "bearer")
//@formatter:on
public class SecuredSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
		http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests(authz -> 
					authz
						.antMatchers(HttpMethod.GET, "/api/v1/swagger-ui/**", "/api/v1/v3/api-docs/**", "/api/v1/error")
							.permitAll()
						.antMatchers(HttpMethod.PUT, "/api/v1/artefacts/**")
							.hasAuthority("SCOPE_create")
						.antMatchers(HttpMethod.PATCH, "/api/v1/artefacts/**", "/api/v1/artefacts/**", "/api/v1/artefacts_versions/**")
							.hasAuthority("SCOPE_write")
						.antMatchers(HttpMethod.DELETE, "/api/v1/artefacts/**")
							.hasAuthority("SCOPE_write")
						.anyRequest()
							.authenticated())
			.oauth2ResourceServer(oauth2 -> oauth2.jwt());
		//@formatter:on
	}
}
