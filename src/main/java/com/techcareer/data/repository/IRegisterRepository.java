package com.techcareer.data.repository;

import com.techcareer.data.entity.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRegisterRepository extends JpaRepository<RegisterEntity, Long> {

    // Delivered Query (database query
    // Database Register Email bulmak
    // select * from Register as r where r.register_email
    Optional<RegisterEntity> findByEmail(String email);

    @Query("select reg from Registers reg join reg.roles rol where rol.roleName=:roleNameParam")
    List<RegisterEntity> findAllByRegisterInJoinRolesRoleName(@Param("roleNameParam") String roleName );

}//end IRegisterRepository
