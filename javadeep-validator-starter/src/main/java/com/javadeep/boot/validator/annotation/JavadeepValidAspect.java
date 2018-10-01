package com.javadeep.boot.validator.annotation;

import com.javadeep.boot.common.util.StringFunction;
import com.javadeep.boot.common.validation.exception.ValidationException;
import com.javadeep.boot.common.validation.metadata.ValidationError;
import com.javadeep.boot.common.validation.metadata.ValidationResult;
import com.javadeep.boot.validator.beans.JavadeepValidHandlerBean;
import com.javadeep.boot.validator.beans.JavadeepValidHandlerFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.validation.Validator;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 参数校验切面
 */
@Aspect
public class JavadeepValidAspect {

    @Autowired
    @Qualifier("failFastValidator")
    private Validator failFastValidator;

    @Autowired
    @Qualifier("failOverValidator")
    private Validator failOverValidator;

    @Before(value = "@annotation(javadeepValid)")
    public void beforeExecution(JoinPoint point, JavadeepValid javadeepValid) {

        ValidationResult result = ValidationResult.build();

        // Hibernate校验
        if (javadeepValid.enableHibernateValidate()) {

            Validator validator = javadeepValid.failFast() ? failFastValidator : failOverValidator;
            for (Object argument : point.getArgs()) {
                result.addErrors(validator.validate(argument, javadeepValid.hibernateGroups()).stream()
                        .map(v -> ValidationError.builder(v.getPropertyPath().toString(), v.getMessage())
                                .invalidValue(v.getInvalidValue())
                                .errorCode(javadeepValid.hibernateErrorCode())
                                .build())
                        .collect(Collectors.toList()));

                if (javadeepValid.failFast() && !result.isSuccess()) {
                    throw new ValidationException(result);
                }
            }
        }

        // 根据提供的校验器进行校验
        if (!Objects.equals(javadeepValid.value(), Void.class)) {
            String handlerName = StringFunction.IS_EMPTY.apply(javadeepValid.method()) ?
                    point.getSignature().getName() : javadeepValid.method();
            JavadeepValidHandlerBean handler =
                    JavadeepValidHandlerFactory.getHandler(handlerName, javadeepValid.value())
                            .orElseThrow(() -> new ValidationException(ValidationResult.build()
                                    .addGlobalError(String.format("Handler %s(%s) not found",
                                            javadeepValid.value().getName(), handlerName))));
            Object[] newArguments = new Object[point.getArgs().length + 1];
            newArguments[0] = result;
            System.arraycopy(point.getArgs(), 0, newArguments, 1, point.getArgs().length);
            handler.invoke(newArguments);
        }

        if (!result.isSuccess()) {
           throw new ValidationException(result);
        }
    }
}
