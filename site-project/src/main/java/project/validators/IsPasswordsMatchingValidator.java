package project.validators;


import project.models.RegisterUserModel;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordsMatchingValidator implements ConstraintValidator<IsPasswordsMatching, Object> {
    @Override
    public void initialize(IsPasswordsMatching isPasswordsMatching) {

    }

    @Override
    public boolean isValid(Object userClass, ConstraintValidatorContext constraintValidatorContext) {
        if(userClass instanceof RegisterUserModel){
            return ((RegisterUserModel) userClass).getPassword().equals(((RegisterUserModel) userClass).getConfirmPassword());
        }

        return false;
    }
}
