package com.example.oauth.auth.domain.model.client;

import org.springframework.security.oauth2.provider.client.BaseClientDetails;

public class OAuthClientDetails extends BaseClientDetails {

	private static final long serialVersionUID = 1L;

	private Client client;

	private ClientGrants clientGrants;

	private ClientRedirectUris clientRedirectUris;

	private ClientResources clientResources;

	private ClientScopes clientScopes;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ClientGrants getClientGrants() {
		return clientGrants;
	}

	public void setClientGrants(ClientGrants clientGrants) {
		this.clientGrants = clientGrants;
	}

	public ClientRedirectUris getClientRedirectUris() {
		return clientRedirectUris;
	}

	public void setClientRedirectUris(ClientRedirectUris clientRedirectUris) {
		this.clientRedirectUris = clientRedirectUris;
	}

	public ClientResources getClientResources() {
		return clientResources;
	}

	public void setClientResources(ClientResources clientResources) {
		this.clientResources = clientResources;
	}

	public ClientScopes getClientScopes() {
		return clientScopes;
	}

	public void setClientScopes(ClientScopes clientScopes) {
		this.clientScopes = clientScopes;
	}

	@Override
	public String toString() {
		return "OAuthClientDetails [client=" + client + ", clientGrants=" + clientGrants + ", clientRedirectUris="
				+ clientRedirectUris + ", clientResources=" + clientResources + ", clientScopes=" + clientScopes + "]";
	}
}
