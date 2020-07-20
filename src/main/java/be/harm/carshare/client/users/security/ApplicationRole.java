package be.harm.carshare.client.users.security;

import java.util.Collections;
import java.util.List;

public enum ApplicationRole {
    USER(Collections.emptyList()),
    ADMIN(Collections.singletonList(
            ApplicationPermission.GET_ALL_USERS));

    private final List<ApplicationPermission> permissions;

    ApplicationRole(List<ApplicationPermission> permissions) {
        this.permissions = permissions;
    }

}
