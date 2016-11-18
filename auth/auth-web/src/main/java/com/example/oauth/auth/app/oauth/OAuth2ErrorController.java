package com.example.oauth.auth.app.oauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OAuth2ErrorController {

	@RequestMapping("/oauth/error") // (1)
	public String handleError() {
		// ommit
		return "common/error/oauthError";
	}
}
