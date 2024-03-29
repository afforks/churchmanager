package br.com.churchmanager.util.custom;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.churchmanager.util.custom.validator.CepValidator;

@Constraint(validatedBy = CepValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cep {
	
	String message() default "{javax.validation.constraints.Cep.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}