package com.techcareer.business.dto;

import com.techcareer.annotation.UniqueRoleName;
import com.techcareer.role.ERole;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data // @Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
//@SneakyThrows

// RoleDto
public class RoleDto implements Serializable {

    public static final Long serialVersionUID = 1L;

    private long roleId;

    // Validation
    @NotEmpty(message = "{role.name.validation.constraints.NotNull.message}")
    @Builder.Default
    //Annotation kullanmalisin cunku database ayni role adinda olmamasi gerekiyor
    @UniqueRoleName
    private String roleName = ERole.USER.toString();

    private Date systemCreateDate;
} //end class RoleDto
