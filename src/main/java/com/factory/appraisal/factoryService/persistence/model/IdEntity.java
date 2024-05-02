package com.factory.appraisal.factoryService.persistence.model;



import javax.persistence.*;
import java.io.Serializable;


/**
 * This is class used for overriding attributes
 */


@MappedSuperclass
public abstract class IdEntity  implements Serializable {



    @Version
    private int version;

    @Column(name = "source_system")
    private String sourceSystem="SYSTEM";

    private Boolean valid=Boolean.TRUE;

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
