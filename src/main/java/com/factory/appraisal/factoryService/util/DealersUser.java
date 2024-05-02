package com.factory.appraisal.factoryService.util;

import com.factory.appraisal.factoryService.persistence.model.EDealerRegistration;
import com.factory.appraisal.factoryService.persistence.model.ERoleMapping;
import com.factory.appraisal.factoryService.persistence.model.EUserRegistration;
import com.factory.appraisal.factoryService.repository.DealerRegistrationRepo;
import com.factory.appraisal.factoryService.repository.RoleMappingRepo;
import com.factory.appraisal.factoryService.repository.UserRegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DealersUser {
    @Autowired
    private RoleMappingRepo mappingRepo;
    @Autowired
    private UserRegistrationRepo userRepo;
    @Autowired
    private DealerRegistrationRepo dealerRepo;

    public List<UUID> getAllUsersUnderDealer(UUID userId){
        List<UUID> userIds=new ArrayList<>();
        ERoleMapping mapping = mappingRepo.findByUserId(userId);
        String roleGroup = mapping.getRole().getRoleGroup();

        switch (roleGroup) {
                case "D":
                EDealerRegistration dealer = userRepo.findUserById(userId).getDealer();
                List<EUserRegistration> users = userRepo.findUserByDealerId(dealer.getId());
                userIds = users.stream().map(EUserRegistration::getId).collect(Collectors.toList());
                break;
                case "DM":
                userIds= mappingRepo.findUsersUnderManager(userId);
                break;
                case "DS":
                    case "P":
                userIds.add(userId);
                break;
                default: return userIds;
        }

        return userIds;
    }
}
