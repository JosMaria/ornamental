package org.fdryt.ornamental.service;

import org.fdryt.ornamental.domain.AppUser;
import org.fdryt.ornamental.domain.Role;

import java.util.List;

public interface UserService {

    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
