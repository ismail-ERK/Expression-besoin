package com.expressionbesoins.restexpbesoin.service;

/**
 * @autor abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;
import com.expressionbesoins.restexpbesoin.model.Privilege;
import com.expressionbesoins.restexpbesoin.repository.PrivilegeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService {
    @Autowired
    PrivilegeRepo privilegeRepo;

    Privilege findByName(PrivilegeEnum name) {
        if (name != null) {
            return privilegeRepo.findPrivilegeByName(name);
        }
        return null;
    }

    public Privilege savePrivilege( Privilege privilege){
        Privilege  privilege_ = privilegeRepo.findPrivilegeByName(privilege.getName());
        if(privilege_ == null) {
            privilege_ =  privilegeRepo.save(privilege);
        }
        return privilege_;
    }
}
