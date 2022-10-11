package com.Main.TaskCep.Services;

import com.Main.TaskCep.Repository.UsuarioRepository;
import com.Main.TaskCep.Entidades.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    public List<Usuario> FindUserByCpf(String cpf){
        return usuarioRepository.findByCpf(cpf);
    }

    public List<Usuario> listall() {
        return usuarioRepository.findAll();
    }
}
