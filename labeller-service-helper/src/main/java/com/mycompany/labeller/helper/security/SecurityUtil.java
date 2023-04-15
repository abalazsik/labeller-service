package com.mycompany.labeller.helper.security;

import com.mycompany.labeller.helper.roles.Roles;
import com.mycompany.labeller.domain.user.IUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author ador
 */
public class SecurityUtil {

    public static IUser getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return Roles.Anonymus;
        }
        if (authentication instanceof LabellerUser) {
            return ((LabellerUser) authentication).getRole();
        }
        return Roles.Anonymus;
    }

}
