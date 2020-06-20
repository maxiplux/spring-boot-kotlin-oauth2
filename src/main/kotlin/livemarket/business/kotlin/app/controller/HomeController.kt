package livemarket.business.kotlin.app.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {
    @GetMapping("/")
    fun helloWorld(): String {
        return "Hello World"
    }

    @Autowired
    lateinit  var tokenStore: TokenStore

    @GetMapping("/protected")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    fun helloWorldProtected(oAuth: OAuth2Authentication): ResponseEntity<*> {
        // how to get principal from OAuth2Authentication using spring boot 2.3
        // todo: improve openapi
        oAuth.principal
        val oAuthDetails = oAuth.details as OAuth2AuthenticationDetails
        val registeredToken = tokenStore!!.readAccessToken(oAuthDetails.tokenValue)
        if (registeredToken == null) {
            val newToken = DefaultOAuth2AccessToken(oAuthDetails.tokenValue)
            tokenStore.storeAccessToken(newToken, oAuth)
        }
        return ResponseEntity("todo bien 10 10+" + oAuth.principal, HttpStatus.OK)
    }
}
