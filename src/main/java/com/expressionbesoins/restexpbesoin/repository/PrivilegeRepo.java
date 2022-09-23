package com.expressionbesoins.restexpbesoin.repository;
/**
 * @autor abdelhadi mouzafir
 */
import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;
import com.expressionbesoins.restexpbesoin.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepo extends JpaRepository<Privilege,Long> {
    Privilege findById(long id);
    Privilege findPrivilegeByName(PrivilegeEnum name);

}
