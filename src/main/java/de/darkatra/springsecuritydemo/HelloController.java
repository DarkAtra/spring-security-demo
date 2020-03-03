package de.darkatra.springsecuritydemo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('SCOPE_SECURITY_DEMO:GET_HELLO')")
    public String hello(@AuthenticationPrincipal final Jwt jwt) {
        log.info("{}", jwt);
        return "hello";
    }
}
