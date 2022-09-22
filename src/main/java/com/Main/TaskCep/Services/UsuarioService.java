package com.Main.TaskCep.Services;

import com.Main.TaskCep.Repository.UsuarioRepository;
import com.Main.TaskCep.UserDomain.Usuario;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
}
