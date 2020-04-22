package com.microservice.config;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private final AuthenticationManager authenticationManager;

	AuthorizationServerConfig(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Value("${jwt.enhance.store.file}")
	private Resource keystore;

	@Value("${jwt.enhance.store.password}")
	private String keystorePassword;

	@Value("${jwt.enhance.key.alias}")
	private String keyAlias;

	@Value("${jwt.enhance.key.password}")
	private String keyPassword;

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keystore, keystorePassword.toCharArray());
		KeyPair keyPair = keyStoreKeyFactory.getKeyPair(keyAlias, keyPassword.toCharArray());
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setKeyPair(keyPair);
		return converter;
	}

	@Bean
	TokenStore tokenStore() {
		return new JwtTokenStore(this.jwtAccessTokenConverter());
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		// @formatter:off
		clients.inMemory().withClient("").secret("{noop}")
				.authorizedGrantTypes("password", "client_credentials", "authorization_code", "refresh_token")
				.autoApprove(true).scopes("profile", "read", "write")
				.accessTokenValiditySeconds(60 * 60 * 24 * 1000)
				.refreshTokenValiditySeconds(7 * 60 * 60 * 24 * 1000);
		// @formatter:on

	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(this.tokenStore()).authenticationManager(authenticationManager)
				.accessTokenConverter(jwtAccessTokenConverter());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

}
