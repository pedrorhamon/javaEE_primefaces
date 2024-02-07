package starking.comercio.util.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import starking.comercio.model.Grupo;
import starking.comercio.model.Usuario;
import starking.comercio.repository.UsuarioRepository;
import starking.comercio.util.cdi.CDIServiceLocator;

/**
 * @author pedroRhamon
 */
public class AppDetailsService implements UserDetailsService{

	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UsuarioRepository usuarioRepository = CDIServiceLocator.getBean(UsuarioRepository.class);
		Usuario usuario = usuarioRepository.porEmail(email);
		UsuarioSistema user = null;
		
		if(usuario != null) {
			user = new UsuarioSistema(usuario,  getGrupos(usuario));
		}
		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> grupos = new ArrayList<>();
		
		for(Grupo grupo: usuario.getGrupos()) {
			grupos.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		}
		
		return grupos;
	}

}
