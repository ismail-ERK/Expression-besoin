package com.expressionbesoins.restexpbesoin.repository;
/**
 * @autor abdelhadi mouzafir
 */
import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;
import com.expressionbesoins.restexpbesoin.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {

    Role findRoleByName(PrivilegeEnum name);
    Role findById(long id);
}
