package es.daw.foodexpressapi.security;

import es.daw.foodexpressapi.entity.User;
import es.daw.foodexpressapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    /**
     * Spring Security trabaja con un sistema de autenticación basado en UserDetailsService, que carga los usuarios desde la base de datos.
     * User, al implementar UserDetails, tu entidad User es compatible con Spring Security y puedes personalizar la lógica de autenticación y autorización.
     * Carga el usuario de la base de datos para la autenticación
     */
    private final UserRepository userRepository;

//    @Autowired
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Cargar el usuario desde de la base de datos
        User user = userRepository.findByUsername(username) // Busca el usuario por su nombre de usuario (creado en UserRepository)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return user;
    }
}