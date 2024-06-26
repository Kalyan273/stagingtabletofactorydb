package com.factory.appraisal.factoryService.util;

import com.factory.appraisal.factoryService.constants.AppraisalConstants;

import com.factory.appraisal.factoryService.dto.DealerRegistration;
import com.factory.appraisal.factoryService.dto.DlrInvntryPdfFilter;
import com.factory.appraisal.factoryService.dto.FilterParameters;
import com.factory.appraisal.factoryService.dto.SellingDealer;
import com.factory.appraisal.factoryService.persistence.model.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;


public class AppraisalSpecification {
    private AppraisalSpecification(){

    }
    public static Specification<EAppraiseVehicle> getEApprSpecification(FilterParameters filter, UUID userId) {
        Specification<EAppraiseVehicle> spec = Specification.where((root, query, criteriaBuilder) -> null);

        spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(AppraisalConstants.USER).get(AppraisalConstants.ID), userId));
        spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(AppraisalConstants.INVENTORYSTS), AppraisalConstants.CREATED));
        spec = getEAppraiseVehicleSpecification(filter, spec);
        return spec;
    }

    public static Specification<EAppraiseVehicle> getInventrySpecification(FilterParameters filter, UUID userId) {
        Specification<EAppraiseVehicle> spec = Specification.where((root, query, criteriaBuilder) -> null);
        spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(AppraisalConstants.USER).get(AppraisalConstants.ID), userId));
        spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(AppraisalConstants.INVENTORYSTS), AppraisalConstants.INVENTORY));
        spec = getEAppraiseVehicleSpecification(filter, spec);
        return spec;
    }
    public static Specification<EAppraiseVehicle> getSearchFactorySpecification(FilterParameters filter, UUID userId) {
        Specification<EAppraiseVehicle> spec = Specification.where((root, query, criteriaBuilder) -> null);

        spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get(AppraisalConstants.USER).get(AppraisalConstants.ID), userId));
        spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(AppraisalConstants.INVENTORYSTS), AppraisalConstants.INVENTORY));
        spec = getEAppraiseVehicleSpecification(filter, spec);
        return spec;
    }

    public static Specification<TransactionReport> displayBySellingDealer(SellingDealer filter) {
        Specification<TransactionReport> spec = Specification.where((root, query, criteriaBuilder) -> null);
        if (null!=filter.getFirstName()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + filter.getFirstName().toLowerCase() + "%"));
        }
        if (null!=filter.getFirstName()) {
            spec = spec.or((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + filter.getFirstName().toLowerCase() + "%"));
        }
        return spec;
    }

    public static Specification<MembersByFactoryManager> displayByFtryManager(SellingDealer filter) {
        Specification<MembersByFactoryManager> spec = Specification.where((root, query, criteriaBuilder) -> null);
        if (null!=filter.getFirstName()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("fmFirstName")), "%" + filter.getFirstName().toLowerCase() + "%"));
        }
        if (null!=filter.getFirstName()) {
            spec = spec.or((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("fmLastName")), "%" + filter.getFirstName().toLowerCase() + "%"));
        }
        return spec;
    }


    public static Specification<MembersByFactorySalesmen> displayByFtrySales(SellingDealer filter) {
        Specification<MembersByFactorySalesmen> spec = Specification.where((root, query, criteriaBuilder) -> null);
        if (null!=filter.getFirstName()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("fsFirstName")), "%" + filter.getFirstName().toLowerCase() + "%"));
        }
        if (null!=filter.getFirstName()) {
            spec = spec.or((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("fsLastName")), "%" + filter.getFirstName().toLowerCase() + "%"));
        }
        return spec;
    }


    private static Specification<EAppraiseVehicle> getEAppraiseVehicleSpecification(FilterParameters filter, Specification<EAppraiseVehicle> spec) {
        if (null!= filter.getYear()){
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(AppraisalConstants.VEH_YEAR), filter.getYear()));
        }
        if (null!= filter.getMinDistance()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(AppraisalConstants.VEH_MILES), filter.getMinDistance()));
        }
        if (null!= filter.getMaxDistance()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(AppraisalConstants.VEH_MILES), filter.getMaxDistance()));
        }
        if (null!=filter.getMinYear()) {
        spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(AppraisalConstants.VEH_YEAR), filter.getMinYear()));
        }
        if (null!=filter.getMaxYear()) {
        spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(AppraisalConstants.VEH_YEAR), filter.getMaxYear()));
        }
        if (null!= filter.getMake()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get(AppraisalConstants.VEH_MAKE)), filter.getMake().toLowerCase()));
        }
        if (null!= filter.getModel()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(AppraisalConstants.VEH_MODEL)),"%" + filter.getModel().toLowerCase() + "%"));
        }

        if (null != filter.getSeries()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(AppraisalConstants.VEH_SERIES)),"%" + filter.getSeries().toLowerCase() + "%"));
        }

        if (null!= filter.getEngine()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get(AppraisalConstants.TD_STATUS).get(AppraisalConstants.ENG_TYPE)), filter.getEngine().toLowerCase()));
        }
        if (null!= filter.getTransmission()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get(AppraisalConstants.TD_STATUS).get(AppraisalConstants.TRANS_TYPE)), filter.getTransmission().toLowerCase()));
        }

        return spec;
    }

    public static Specification<DlrInvntryView> getDlrInvntryViewSpecification(DlrInvntryPdfFilter filter) {

        Specification<DlrInvntryView> spec = Specification.where((root, query, criteriaBuilder) -> null);
        if (null!= filter.getVehicleMake()) {
            spec = spec.or((root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get(AppraisalConstants.VEH_MAKE)), filter.getVehicleMake().toLowerCase()));
        }
        if (null!= filter.getConsumerAskPrice()) {
            spec = spec.or((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(AppraisalConstants.CONSUMER_ASKING_PRICE), filter.getConsumerAskPrice()));
        }
        if (null!= filter.getDaysSinceInventory()) {
            spec = spec.or((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(AppraisalConstants.INVNTRY_DAYS), filter.getDaysSinceInventory()));
        }
        if(null!=filter.getUsers()){
            spec = spec.and((root, query, criteriaBuilder) -> root.get("user").in(filter.getUsers()));
        }
        return spec;
    }


    public static Specification<ECompany> searchByCompany(String companyName){
        Specification<ECompany> spec = Specification.where((root, query, criteriaBuilder) -> null);
            spec=spec.and((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+companyName.toLowerCase()+"%"));
        return spec;
    }


    public static Specification<EDealerRegistration> dsplyDlrList(DealerRegistration filter) {
        Specification<EDealerRegistration> spec = Specification.where((root, query, criteriaBuilder) -> null);
        if (null!=filter.getFirstName()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + filter.getFirstName().toLowerCase() + "%"));
        }
        if (null!=filter.getFirstName()) {
            spec = spec.or((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + filter.getFirstName().toLowerCase() + "%"));
        }
        return spec;
    }
    public static Specification<UserListView> usrLst(String roleGroup,String name) {
        Specification<UserListView> spec = Specification.where((root, query, criteriaBuilder) -> null);
/*        if (null!=roleGroup) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get("roleGroup")), roleGroup.toLowerCase() ));
        }
        if (null!=name) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + name.toLowerCase() + "%"));
        }
        if (null!=name) {
            spec = spec.or((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + name.toLowerCase() + "%"));
        }*/

        if ( null!=name) {
            spec = spec.and(
                    (root, query, criteriaBuilder) ->
                            criteriaBuilder.or(
                                    criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + name.toLowerCase() + "%"),
                                    criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + name.toLowerCase() + "%")
                            )
            );
        }
        if (null!=roleGroup ) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(criteriaBuilder.lower(root.get("roleGroup")), roleGroup.toLowerCase()));
        }



        return spec;
    }


}
