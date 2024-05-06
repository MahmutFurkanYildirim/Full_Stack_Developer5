package com.techcareer.data.entity;

import com.techcareer.role.ERole;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data // @Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
//@SneakyThrows

// RoleEntity
//ENTITY
@Entity(name = "Roles")
@Table(name="roles")
public class RoleEntity  implements Serializable {

    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long roleId;

    // Validation = columnDefinition =>  Default USER olsun
    @Column(name = "role_name",length = 50,nullable = true,columnDefinition = "varchar(255) default USER")
    private String roleName = ERole.USER.toString();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemCreateDate;
} //end class RoleEntity