package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.ModelView;
import org.eclipse.jdt.internal.compiler.parser.JavadocParser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelViewRepo extends JpaRepository<ModelView,String > {
}
