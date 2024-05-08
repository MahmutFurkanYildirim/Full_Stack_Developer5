package com.techcareer.data.repository;

import com.techcareer.data.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

// CrudRepository<RoleEntity,Long>
// JpaRepository<RoleEntity,Long>
// PagingAndSortingRepository<RoleEntity,Long>

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    // Delivered Query (database query
    // Database Role Name bulmak
    // select * from Roles as rol where rol.role_name
    Optional<RoleEntity> findByRoleName(String roleName);

    @Query("select rol from Registers reg join reg.roles rol where reg.email=:emailParam")
    RoleEntity registerEmailFindRole(@Param("emailParam") String email);

}//end IRoleRepository
