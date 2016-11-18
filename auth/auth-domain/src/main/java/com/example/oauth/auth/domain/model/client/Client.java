package com.example.oauth.auth.domain.model.client;

import java.io.Serializable;

public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	private String clientId; // (1)

	private String clientSecret; // (2)

	private String clientName; // (3)

	private Integer accessTokenValidity; // (4)

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientSecret=" + clientSecret + ", clientName=" + clientName
				+ ", accessTokenValidity=" + accessTokenValidity + "]";
	}
}
