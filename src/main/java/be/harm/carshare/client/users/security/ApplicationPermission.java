package be.harm.carshare.client.users.security;

import lombok.Getter;

public enum ApplicationPermission {
    GET_ALL_USERS("user:read_all");

    @Getter
    private final String permission;

    ApplicationPermission(String permission) {
        this.permission = permission;
    }
}
