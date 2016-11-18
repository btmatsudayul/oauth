package com.example.oauth.auth.domain.model.client;

import java.io.Serializable;
import java.util.Set;

public class ClientResources implements Serializable {

	private static final long serialVersionUID = 1L;

	private String clientId; // (1)

	private Set<String> resourceIds; // (2)

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Set<String> getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(Set<String> resourceIds) {
		this.resourceIds = resourceIds;
	}

	@Override
	public String toString() {
		return "ClientResources [clientId=" + clientId + ", resourceIds=" + resourceIds + "]";
	}
}
