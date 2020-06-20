package livemarket.business.kotlin.app.services.impl;

import livemarket.business.kotlin.app.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public interface UserDetailsServiceExtra extends UserDetailsService {
    @Transactional
    User findByUsername(String username) throws UsernameNotFoundException;
}
