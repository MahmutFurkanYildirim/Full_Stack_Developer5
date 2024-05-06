package com.techcareer.business.dto;

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
    private String roleName;

    private Date systemCreateDate;
} //end class RoleDto
