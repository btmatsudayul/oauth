package com.example.oauth.auth.domain.repository.client;

import com.example.oauth.auth.domain.model.client.Client;
import com.example.oauth.auth.domain.model.client.ClientGrants;
import com.example.oauth.auth.domain.model.client.ClientRedirectUris;
import com.example.oauth.auth.domain.model.client.ClientResources;
import com.example.oauth.auth.domain.model.client.ClientScopes;

public interface ClientRepository {

	public Client findClientByClientId(String clientId);

	public ClientScopes findClientScopesByClientId(String clientId);

	public ClientResources findClientResourcesByClientId(String clientId);

	public ClientGrants findClientGrantsByClientId(String clientId);

	public ClientRedirectUris findClientRedirectUrisByClientId(String clientId);
}
