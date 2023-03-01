package com.mycompany.labeller.domain;

import com.mycompany.labeller.domain.user.IUser;
import java.util.HashSet;

/**
 *
 * @author ador
 */
public class TestUser implements IUser {

    private final HashSet<String> rights;

    public TestUser(HashSet<String> rights) {
        this.rights = rights;
    }

    public TestUser(String... rights) {
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
