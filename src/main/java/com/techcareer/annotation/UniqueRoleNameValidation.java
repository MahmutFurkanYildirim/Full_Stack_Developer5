package com.techcareer.annotation;

import com.techcareer.data.repository.IRoleRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

// LOMBOK
@RequiredArgsConstructor

/*
<A,T>
A= Annotation
T= Type
 */

public class UniqueRoleNameValidation implements ConstraintValidator<UniqueRoleName, String> {

    // Injection (3.YOL)
    private final IRoleRepository iRoleRepository;

    // Database rolName attributta aynı veri var mı ? Yok mu ?
    @Override
    public boolean isValid(String rolName, ConstraintValidatorContext constraintValidatorContext) {
        // 1.YOL
        //RoleEntity roleEntity=iRoleRepository.findByRoleName(rolName).orElseThrow(()->new NotFoundException(rolName+" bulunamadı"))

        // 2.YOL
        Boolean isRolesFind=iRoleRepository.findByRoleName(rolName).isPresent();
        // eğer böyle bir rolName yoksa return false döndersin
        if(!isRolesFind)
            return false;
        return true;
    } // end isValid
}// end UniqueRoleNameValidation
