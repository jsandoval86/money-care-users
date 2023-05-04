package com.moneycare.config.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class FilterRegister {

    @Autowired
    private lateinit var authenticateUserFilter: AuthenticateUserFilter

    @Bean
    open fun loggingFilter(): FilterRegistrationBean<AuthenticateUserFilter> {
        val registrationBean: FilterRegistrationBean<AuthenticateUserFilter> =
            FilterRegistrationBean<AuthenticateUserFilter>()
        registrationBean.filter = authenticateUserFilter
        registrationBean.addUrlPatterns("/v1/users/*")
        return registrationBean
    }
}