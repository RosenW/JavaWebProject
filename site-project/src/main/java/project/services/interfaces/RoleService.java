package project.services.interfaces;

import project.entities.Role;

public interface RoleService {
    Role findByName(String name);
}
