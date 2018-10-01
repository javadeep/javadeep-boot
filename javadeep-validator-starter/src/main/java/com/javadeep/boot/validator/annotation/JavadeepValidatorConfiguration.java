package com.javadeep.boot.validator.annotation;

import com.javadeep.boot.common.function.Function1;
import com.javadeep.boot.common.util.CollectionFunction;
import com.javadeep.boot.common.util.StringFunction;
import com.javadeep.boot.validator.beans.JavadeepValidHandlerBeanPostProcessor;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Javadeep Validator Configuration
 *
 * @author javadeep
 * @since 1.0.0
 */
@Configuration
public class JavadeepValidatorConfiguration implements ImportAware {

    private AnnotationMetadata annotationMetadata;

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        this.annotationMetadata = importMetadata;
    }

    @Bean
    public JavadeepValidHandlerBeanPostProcessor validHandlerBeanPostProcessor() {
        AnnotationAttributes enableJavadeepValidator = AnnotationAttributes.fromMap(
                annotationMetadata.getAnnotationAttributes(EnableJavadeepValidator.class.getName(),
                        false));
        if (Objects.isNull(enableJavadeepValidator)) {
            throw new IllegalArgumentException(
                    "@EnableJavadeepValidator is not present on importing class " + annotationMetadata.getClassName());
        }
        String[] basePackagesArray = enableJavadeepValidator.getStringArray("basePackages");
        Set<String> basePackages = Arrays.stream(basePackagesArray)
                .flatMap(pkg -> Arrays.stream(StringUtils.tokenizeToStringArray(pkg,
                        ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS)))
                .filter(Function1.toPredicate(StringFunction.IS_NOT_EMPTY))
                .collect(Collectors.toSet());
        if (CollectionFunction.IS_EMPTY.apply(basePackages)) {
            basePackages.add(ClassUtils.getPackageName(annotationMetadata.getClassName()));
        }
        return JavadeepValidHandlerBeanPostProcessor.of(basePackages);
    }

    @Bean("failFastValidator")
    public Validator failFastValidator() {
        Optional.ofNullable(webMvcProperties().getLocale())
                .ifPresent(Locale::setDefault);
        return Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();
    }

    @Bean
    public Validator failOverValidator() {
        Optional.ofNullable(webMvcProperties().getLocale())
                .ifPresent(Locale::setDefault);
        return Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(false)
                .buildValidatorFactory()
                .getValidator();
    }

    @Bean
    public WebMvcProperties webMvcProperties() {
        return new WebMvcProperties();
    }
}
