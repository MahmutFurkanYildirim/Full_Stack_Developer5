package com.techcareer.data.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

// LOMBOK
@Getter
@Setter

// embeddable
@Embeddable
public class EmbeddableUserDetails {

    // Kullanıcı üye olurken kilitli olsun ancak mail onayı ile aktifleştirilsin
    @Column(name="account_locked")
    public Boolean isAccountNonLocked=false;

    // Kullanıcı hesabını pasit duruma getirme
    @Column(name="account_expired")
    public Boolean isAccountNonExpired=true;

    // Kullanıcı Hesap Bilgi Süresi (Authorization)
    @Column(name="credentials_expired")
    public Boolean isCredentialsNonExpired=true;

    // Kullanıcı Sistemde mi ?
    @Column(name="enabled")
    public Boolean isEnabled =true;
}//end EmbeddableUserDetails
