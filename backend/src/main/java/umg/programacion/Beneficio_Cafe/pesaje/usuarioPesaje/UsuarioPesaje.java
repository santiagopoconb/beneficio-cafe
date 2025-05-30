package umg.programacion.Beneficio_Cafe.pesaje.usuarioPesaje;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name="usuario", schema = "pesaje")
@Entity(name = "UsuarioPesaje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPesaje implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol")
    private RolPesaje idRol;
    private String nombre;
    private String password;
    private String usuario;
    private String estado;

    public UsuarioPesaje(DTOUsuarioPesaje dtoUsuarioPesaje){
        this.usuario = dtoUsuarioPesaje.usuario();
        this.password = dtoUsuarioPesaje.password();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword(){return password;}

    @Override
    public String getUsername(){return usuario;}

    @Override
    public boolean isAccountNonExpired(){return true;}

    @Override
    public boolean isAccountNonLocked(){return true;}

    @Override
    public boolean isCredentialsNonExpired(){return true;}

    @Override
    public boolean isEnabled(){return true;}

}
