package com.factory.appraisal.factoryService.persistence.generator;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * This Class generates custom Ids for tables
 */

public class CustomIDGenerator extends SequenceStyleGenerator{


    private static final DateTimeFormatter monthFormatter
            = DateTimeFormatter.ofPattern("MM");

    private static final DateTimeFormatter yearFormatter=DateTimeFormatter.ofPattern("yy");


    @Override
    public Serializable generate(SharedSessionContractImplementor session,
                                 Object object) throws HibernateException {
        LocalDate date=LocalDate.now();
        String prefix=date.format(yearFormatter).concat(date.format(monthFormatter));
        Long  id= (Long) super.generate(session, object);
        String genId = prefix.concat((String.valueOf(id)) );
        return Long.valueOf(genId);
    }

    @Override
    public void configure(Type type, Properties params,
                          ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(LongType.INSTANCE, params, serviceRegistry);
    }
}
