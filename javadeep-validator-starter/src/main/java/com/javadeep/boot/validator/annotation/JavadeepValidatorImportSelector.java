package com.javadeep.boot.validator.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Javadeep Validator Import Selector
 *
 * @author javadeep
 * @since 1.0.0
 */
public class JavadeepValidatorImportSelector implements ImportSelector {

    @Override
    public final String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {JavadeepValidatorConfiguration.class.getName(), JavadeepValidAspect.class.getName()};
    }
}
