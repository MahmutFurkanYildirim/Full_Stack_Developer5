package com.techcareer.business.services.Impl;

import com.techcareer.bean.ModelMapperBeanClass;
import com.techcareer.business.dto.RoleDto;
import com.techcareer.business.services.IRoleService;
import com.techcareer.data.entity.RoleEntity;
import com.techcareer.data.repository.IRoleRepository;
import com.techcareer.exception.TechcareerException;
import com.techcareer.exception.Resource404NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

// Lombok
@RequiredArgsConstructor
@Log4j2

// Service: Asıl İş Yükünü sırtlayan
@Service
@Component("roleServicesImpl") // @Component => Spring'in bir parcasısın
public class RoleServicesImpl implements IRoleService<RoleDto, RoleEntity> {


    // 3. YOL (Lombok => Constructor Injection)
    private final IRoleRepository iRoleRepository;

    // 1.YOL (ModelMapper)
    private final ModelMapperBeanClass modelMapperBeanClass;

    ///////////////////////////////////////////////////////////////////////////////////////
    //**** Model Mapper *****************************************************************//
    @Override
    public RoleDto entityToDto(RoleEntity roleEntity) {
        return modelMapperBeanClass.modelMapperMethod().map(roleEntity, RoleDto.class);
    }//end entityToDto

    @Override
    public RoleEntity dtoToEntity(RoleDto roleDto) {
        return modelMapperBeanClass.modelMapperMethod().map(roleDto, RoleEntity.class);
    }//end dtoToEntity

    ///////////////////////////////////////////////////////////////////////////////////////
    //**** CRUD*****************************************************************//

    // CREATE (ROLE)
    @Override
    @Transactional// Create,Update,Delete
    public RoleDto roleServiceCreate(RoleDto roleDto) {
        RoleEntity roleEntity1;
        // Dto => Entity çevirmek
        roleEntity1=dtoToEntity(roleDto);
        roleEntity1.setRoleName(roleEntity1.getRoleName().toUpperCase());
        // Kaydetmek
        RoleEntity roleEntity2=iRoleRepository.save(roleEntity1);
        // ID ve Date Dto üzerinde Set yapıyorum
        roleDto.setRoleId(roleEntity2.getRoleId());
        roleDto.setSystemCreateDate(roleEntity2.getSystemCreateDate());
        return roleDto;
    }//end roleServiceCreate

    // LIST (ROLE)
    @Override
    public List<RoleDto> roleServiceList() {
        //Entity List
        List<RoleEntity> roleEntityList1=iRoleRepository.findAll();

        // Dto List
        List<RoleDto> roleDtoList=new ArrayList<>();

        // Entity To Dto List
        for(RoleEntity tempEntity:roleEntityList1){
            RoleDto roleDto1=entityToDto(tempEntity);
            roleDtoList.add(roleDto1);
        }
        return roleDtoList;
    }//end roleServiceList

    // FIND (ROLE)
    @Override
    public RoleDto roleServiceFindById(Long id) {
        // 1.YOL
        /*
        Optional<RoleEntity> optionalRoleEntityFindById= iRoleRepository.findById(id);
        // isPresent: Entity varsa
        if(optionalRoleEntityFindById.isPresent()){
            return entityToDto(optionalRoleEntityFindById.get());
        }
        */

        // 2.YOL
        Boolean booleanRoleEntityFindById = iRoleRepository.findById(id).isPresent();
        RoleEntity roleEntity = null;
        //if(id!=null){
        if (booleanRoleEntityFindById) {
            roleEntity = iRoleRepository.findById(id).orElseThrow(
                    () -> new Resource404NotFoundException(id + " nolu ID Bulunamadı")
            );
        } else if (!booleanRoleEntityFindById) {
            throw new TechcareerException("Roles Dto id boş değer geldi");
        }
        return entityToDto(roleEntity);
    }//end roleServiceFindById

    // UPDATE (ROLE)
    @Override
    @Transactional// Create,Update,Delete
    public RoleDto roleServiceUpdateById(Long id, RoleDto roleDto) {
        // Find
        RoleDto roleDtoFind = roleServiceFindById(id);

        // Update
        RoleEntity roleUpdateEntity = dtoToEntity(roleDtoFind);
        if (roleUpdateEntity != null) {
            roleUpdateEntity.setRoleName(roleDto.getRoleName());
            iRoleRepository.save(roleUpdateEntity);
        }
        // ID ve Date Dto üzerinde Set yapıyorum
        roleDto.setRoleId(roleUpdateEntity.getRoleId());
        roleDto.setSystemCreateDate(roleUpdateEntity.getSystemCreateDate());
        return entityToDto(roleUpdateEntity);
    }//end roleServiceUpdateById

    // DELETE (ROLE)
    @Override
    @Transactional// Create,Update,Delete
    public RoleDto roleServiceDeleteById(Long id) {
        // Find
        RoleDto roleDtoFind = roleServiceFindById(id);

        RoleEntity roleDeleteEntity = dtoToEntity(roleDtoFind);
        if (roleDeleteEntity != null) {
            iRoleRepository.deleteById(id);
            return roleDtoFind;
        }else {
            throw new TechcareerException(roleDtoFind+ "nolu data silinemedi");
        }
        // return null;
    }//end roleServiceDeleteById
}// end RoleServicesImpl
