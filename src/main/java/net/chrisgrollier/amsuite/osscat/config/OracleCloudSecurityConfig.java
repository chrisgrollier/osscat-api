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
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@Profile("oracle")
@OpenAPIDefinition(info = @Info(title = "Open Source Software Catalog API", version = "v1"), servers = {@Server(url = "https://132.145.60.143/apicat")})
//@formatter:off
@SecurityScheme(
		name = "bearerAuth", 
		type = SecuritySchemeType.OAUTH2, 
		flows = @OAuthFlows(authorizationCode = 
			@OAuthFlow(
					authorizationUrl = "https://idcs-ff72d424ef424056bfc689d6a41302cb.identity.oraclecloud.com:443/oauth2/v1/authorize",
					tokenUrl = "https://idcs-ff72d424ef424056bfc689d6a41302cb.identity.oraclecloud.com:443/oauth2/v1/token"
					
					)
		), 
		openIdConnectUrl = "https://idcs-ff72d424ef424056bfc689d6a41302cb.identity.oraclecloud.com/.well-known/openid-configuration", 
		bearerFormat = "JWT",  
		scheme = "bearer")
//@formatter:on
public class OracleCloudSecurityConfig extends WebSecurityConfigurerAdapter {

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
