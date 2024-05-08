package com.techcareer.data.entity;

import com.techcareer.annotation.AnnotationUniqueEmailAddress;
import com.techcareer.data.embedded.EmbeddableUserDetails;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// LOMBOK
@Data // @Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
//@SneakyThrows

// RegisterEntity
//ENTITY
@Entity(name = "Registers")
@Table(name="registers")
public class RegisterEntity implements Serializable {

    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "register_id")
    private long registerId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemCreateDate;

    // Nickname
    @Column(name = "nickname")
    private String nickName;

    // Name
    @Column(name = "name")
    private String name;

    // Surname
    @Column(name = "surname")
    private String surname;

    // Email
    @Column(
            name = "email",
            nullable = false,
            updatable = false,
            insertable = true,
            length = 60,
            columnDefinition = "varchar(255) default 'hamitmizrak@gmail.com'")
    private String email;

    // Password
    @Column(name = "password")
    private String password;

    // Page Authorization (O kişi o sayfaya yetkisi var mı
    @Column(name = "page_authorization")
    private Boolean pageAuthorization=false;

    /////////////////////////////////////////////
    // Relation
    // Roles (Enum)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "register_roles",
            joinColumns = @JoinColumn(name = "register_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();


    /////////////////////////////////////////////
    // USER DETAILS
    // @Embedable
    // @Embedded
    // @EmbeddedId
    @Embedded
    private EmbeddableUserDetails embeddableUserDetails = new EmbeddableUserDetails();


}//end RegisterEntity
