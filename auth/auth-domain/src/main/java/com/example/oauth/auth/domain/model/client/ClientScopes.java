package com.example.oauth.auth.domain.model.client;

import java.io.Serializable;
import java.util.Set;

import com.example.oauth.auth.domain.model.enums.Scope;

public class ClientScopes implements Serializable {

	private static final long serialVersionUID = 1L;

	private String clientId; // (1)

	private Set<Scope> scopes; // (2)

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Set<Scope> getScopes() {
		return scopes;
	}

	public void setScopes(Set<Scope> scopes) {
		this.scopes = scopes;
	}

	@Override
	public String toString() {
		return "ClientScopes [clientId=" + clientId + ", scopes=" + scopes + "]";
	}
}
