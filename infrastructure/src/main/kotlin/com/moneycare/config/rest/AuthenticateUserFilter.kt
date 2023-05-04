package com.moneycare.config.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.moneycare.users.rest.UserAuthenticated
import com.moneycare.users.user.exception.UnAuthorizedException
import io.jsonwebtoken.Claims
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
@WebFilter(urlPatterns = ["/v1/users/*"])
class AuthenticateUserFilter(
    private var objectMapper: ObjectMapper
): Filter {

    @Autowired
    private lateinit var userAuthenticated: UserAuthenticated

    private val log = LoggerFactory.getLogger(javaClass)

    override fun doFilter(request: ServletRequest, response: ServletResponse,
                          chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse

        val authorizationHeader: String? = httpRequest.getHeader("Authorization")

        if( authorizationHeader == null ){
            responseUnAuthorized(httpResponse)
        } else if ( authenticateUser(authorizationHeader, httpResponse) ) {
            chain.doFilter(request, response)
        }

    }

    private fun authenticateUser(authorizationHeader: String, response: HttpServletResponse) : Boolean {
        val claims: Claims = JWTHelper.getClaims(authorizationHeader) as Claims
        var isAuthenticated = false
        try{
            userAuthenticated.autenticate(claims)
            isAuthenticated = true
        } catch (e: UnAuthorizedException) {
            responseUnAuthorized(response)
        }

        return isAuthenticated
    }


    private fun responseUnAuthorized(response: HttpServletResponse) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        val error = ErrorResponse(status = HttpStatus.UNAUTHORIZED, error = "user unauthorized")
        log.debug(objectMapper.writeValueAsString(error))
        response.writer.write(objectMapper.writeValueAsString(ResponseEntity(error, error.status).body))
    }

}
