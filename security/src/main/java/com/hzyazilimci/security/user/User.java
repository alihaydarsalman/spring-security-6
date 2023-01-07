package com.hzyazilimci.security.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

/**
 * @author hzyazilimci
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    @Enumerated(EnumType.STRING)
    private EnmRole role;

    /**
     * Bu metot kullaniciya ait rolleri donebilir.
     **/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    /**
     * Not: getPassword accessor metodu UserDetails interface' i icerisinde method signature verilmistir.
     *      burada Lombok @Data annotation' ı ile password field' imiz icin zaten bir accessor metodumuz olusmustur.
     *      eger password field' imizin adi password disinda bir sey olsaydi (ornek: sifre) bu metodu override etmek zorunda olacaktik.
     *      suanki durumda getPassword metodunu override etmemize gerek yoktur.
     *
     *      Ayni durum getUsername icin de gecerlidir. Ancak kullanici adi field' i icin  userName seklinde bir tanimlama yapildi.
     *      getter metodumuz da Lombok tarafindan getUserName seklinde olusturuldugundan bu metot override edilmelidir.
     *      Ayrica override edilen metotlar Lombok tarafindan generate edilen accessor metotlarimizi ezecektir.
     **/
    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
