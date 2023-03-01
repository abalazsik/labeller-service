package com.mycompany.labeller.commons.roles;

import com.mycompany.labeller.domain.user.IUser;
import java.util.HashSet;

/**
 *
 * @author ador
 */
public class Role implements IUser {

    private final HashSet<String> rights;

    public Role(HashSet<String> rights) {
        this.rights = rights;
    }

    public Role(String... rights) {
        this.rights = new HashSet<>();
        for (String right : rights) {
            this.rights.add(right);
        }
    }

    @Override
    public boolean hasRight(String right) {
        return rights.contains(right);
    }

}
