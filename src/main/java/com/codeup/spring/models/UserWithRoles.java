package com.codeup.spring.models;
// Imports
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserWithRoles extends User implements UserDetails {
    // Constructor
    public UserWithRoles(User user) {
        super(user);
    }

    // Gets Authorities for Roles
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = " ";
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    // Checks User Validity
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
