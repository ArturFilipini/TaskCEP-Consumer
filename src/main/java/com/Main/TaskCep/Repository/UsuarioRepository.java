package com.Main.TaskCep.Repository;

import com.Main.TaskCep.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
    List<Usuario> findByCpf(String cpf);
}
