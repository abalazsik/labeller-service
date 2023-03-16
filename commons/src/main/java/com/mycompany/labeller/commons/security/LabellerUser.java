
package com.mycompany.labeller.commons.security;

import com.mycompany.labeller.domain.user.IUser;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author ador
 */
public class LabellerUser implements UserDetails {

    private final String username;
    private final String password;
    private final IUser role;

    public LabellerUser(String username, String password, IUser role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

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

    public IUser getRole() {
        return role;
    }
    
}
