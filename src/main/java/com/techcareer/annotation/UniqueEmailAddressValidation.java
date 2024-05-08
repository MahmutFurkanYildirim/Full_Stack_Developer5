package com.techcareer.annotation;

import com.techcareer.data.repository.IRegisterRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

// LOMBOK
@RequiredArgsConstructor

public class UniqueEmailAddressValidation implements ConstraintValidator<AnnotationUniqueEmailAddress,String> {

    // INJECTION
    private final IRegisterRepository iRegisterRepository;

    @Override
    public void initialize(AnnotationUniqueEmailAddress constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }//end initialize

    // DATABASE SORGUSU
    @Override
    public boolean isValid(String emailAddress, ConstraintValidatorContext constraintValidatorContext) {
        Boolean isEmailAddress=iRegisterRepository.findByEmail(emailAddress).isPresent();
        //Eğer email address sistemde yoksa
        if(!isEmailAddress){
            return false;
        }
        return true; //email sistemde varsa
    } //end isValid
}//end UniqueEmailAddressValidation
