package com.example.oauth.auth.domain.model.client;

import java.io.Serializable;
import java.util.Set;

import com.example.oauth.auth.domain.model.enums.Grant;

public class ClientGrants implements Serializable {

	private static final long serialVersionUID = 1L;

	private String clientId; // (1)

	private Set<Grant> grants; // (2)

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Set<Grant> getGrants() {
		return grants;
	}

	public void setGrants(Set<Grant> grants) {
		this.grants = grants;
	}

	@Override
	public String toString() {
		return "ClientGrants [clientId=" + clientId + ", grants=" + grants + "]";
	}
}
