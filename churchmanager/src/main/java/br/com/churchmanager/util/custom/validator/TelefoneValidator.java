package br.com.churchmanager.util.custom.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.churchmanager.util.custom.Telefone;

public class TelefoneValidator implements ConstraintValidator<Telefone, String> {

	private Pattern padrao = Pattern.compile("(\\(\\d{2}\\)\\d{4,5}\\-\\d{4})?");

	@Override
	public void initialize(Telefone constraintAnnotation) {
		//Do not nothing
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || "".equals(value)) {
			return true;
		}

		Matcher matcher = padrao.matcher(value);
		return matcher.matches();
	}

}
