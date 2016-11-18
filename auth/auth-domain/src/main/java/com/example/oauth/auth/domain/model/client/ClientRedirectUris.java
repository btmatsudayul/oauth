package com.example.oauth.auth.domain.model.client;

import java.io.Serializable;
import java.util.Set;

public class ClientRedirectUris implements Serializable {

	private static final long serialVersionUID = 1L;

	private String clientId; // (1)

	private Set<String> redirectUris; // (2)

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Set<String> getRedirectUris() {
		return redirectUris;
	}

	public void setRedirectUris(Set<String> redirectUris) {
		this.redirectUris = redirectUris;
	}

	@Override
	public String toString() {
		return "ClientRedirectUris [clientId=" + clientId + "]";
	}
}
