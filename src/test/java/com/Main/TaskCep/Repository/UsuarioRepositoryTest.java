package com.Main.TaskCep.Repository;

import com.Main.TaskCep.Entidades.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;


@DataJpaTest
class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void test(){

    }
    private Usuario createUsuario(){
        Usuario usuario = Usuario.builder().authorities("Role_ADMIN").cep("7640000")
                .cpf("06329100110")
                .password("sexo").username("Artur").build();
        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encode = delegatingPasswordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encode);
        return usuario;
    }
    @Test
    @DisplayName("Tests for User Repository")
    void save_PersistUser_WhenSucessful(){
        Usuario usuario = createUsuario();
        Usuario save = usuarioRepository.save(usuario);

        Assertions.assertThat(save).isEqualTo(usuario);
        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(save.getId()).isNotNull();
        Assertions.assertThat(save.getUsername()).isEqualTo(usuario.getUsername());
        Assertions.assertThat(save.getPassword()).isEqualTo(usuario.getPassword());
        Assertions.assertThat(save.getCpf()).isEqualTo(usuario.getCpf());
        Assertions.assertThat(save.getCep()).isEqualTo(usuario.getCep());
    }
    @Test
    @DisplayName("Returns Void When Sucessful")
    void Delete_User_WhenSucessful(){
        Usuario usuario = createUsuario();
        Usuario save = usuarioRepository.save(usuario);
        usuarioRepository.delete(save);
        Optional<Usuario> UsuarioOptional = usuarioRepository.findById(usuario.getId());

        Assertions.assertThat(UsuarioOptional).isEmpty();
    }
    @Test
    @DisplayName("Find By Username and Returns ListofUser When Sucessful")
    void findByUsername_ReturnsListOfUser_WhenSucessful(){
        Usuario usuario = createUsuario();
        Usuario save = usuarioRepository.save(usuario);
        List<Usuario> username = usuarioRepository.findByUsername(usuario.getUsername());
        Assertions.assertThat(username).isNotEmpty();
        Assertions.assertThat(username).contains(save);
    }
    @Test
    @DisplayName("Find by Cpf and Returns ListOfUser When Sucessful")
    void findByCpf_ReturnsListOfUser_WhenSucessful(){
        Usuario usuario = createUsuario();
        Usuario save = usuarioRepository.save(usuario);
        List<Usuario> byCpf = usuarioRepository.findByCpf(usuario.getCpf());
        Assertions.assertThat(byCpf).contains(save);
        Assertions.assertThat(byCpf).isNotEmpty();
    }
    @Test
    @DisplayName("Find By Username and Returns List Empty When Sucessful")
    void findByUsername_ReturnsListOfUserEmpty_WhenSucessful(){
        List<Usuario> username = usuarioRepository.findByUsername("sexo");
        Assertions.assertThat(username);
    }


}