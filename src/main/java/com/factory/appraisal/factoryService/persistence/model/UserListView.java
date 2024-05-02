package com.factory.appraisal.factoryService.persistence.model;


import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "userlistview")
@Immutable
@Data
public class UserListView {
    @Id
    private Long  roleId;
    private String firstName;
    private String lastName;
    private UUID userId;
    private String roleGroup;

}
