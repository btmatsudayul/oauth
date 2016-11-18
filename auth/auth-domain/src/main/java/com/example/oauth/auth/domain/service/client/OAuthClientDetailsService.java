package com.example.oauth.auth.domain.service.client;

import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.oauth.auth.domain.model.client.Client;
import com.example.oauth.auth.domain.model.client.ClientGrants;
import com.example.oauth.auth.domain.model.client.ClientRedirectUris;
import com.example.oauth.auth.domain.model.client.ClientResources;
import com.example.oauth.auth.domain.model.client.ClientScopes;
import com.example.oauth.auth.domain.model.client.OAuthClientDetails;
import com.example.oauth.auth.domain.model.enums.Grant;
import com.example.oauth.auth.domain.model.enums.Scope;
import com.example.oauth.auth.domain.repository.client.ClientRepository;

@Service("clientDetailsService") // (1)
@Transactional
public class OAuthClientDetailsService implements ClientDetailsService {

	@Inject
	ClientRepository clientRepository;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

		Client client = clientRepository.findClientByClientId(clientId); // (2)
		ClientScopes clientScopes = clientRepository.findClientScopesByClientId(clientId);
		ClientResources clientResources = clientRepository.findClientResourcesByClientId(clientId);
		ClientGrants clientGrants = clientRepository.findClientGrantsByClientId(clientId);
		ClientRedirectUris clientRedirectUris = clientRepository.findClientRedirectUrisByClientId(clientId);

		if (client == null) { // (3)
			throw new NoSuchClientException("No client with requested id: " + clientId);
		}

		// (4)
		OAuthClientDetails clientDetails = new OAuthClientDetails();
		clientDetails.setClientId(client.getClientId());
		clientDetails.setClientSecret(client.getClientSecret());
		clientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
		clientDetails.setResourceIds(clientResources.getResourceIds());
		clientDetails.setScope(clientScopes.getScopes().stream().map(Scope::toString).collect(Collectors.toSet()));
		clientDetails.setAuthorizedGrantTypes(
				clientGrants.getGrants().stream().map(Grant::getValue).collect(Collectors.toSet()));
		clientDetails.setRegisteredRedirectUri(clientRedirectUris.getRedirectUris());
		clientDetails.setClient(client);
		clientDetails.setClientGrants(clientGrants);
		clientDetails.setClientRedirectUris(clientRedirectUris);
		clientDetails.setClientResources(clientResources);
		clientDetails.setClientScopes(clientScopes);

		return clientDetails;
	}
}
