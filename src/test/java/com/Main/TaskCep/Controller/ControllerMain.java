package com.Main.TaskCep.Controller;

import com.Main.TaskCep.Entidades.Cidades;
import com.Main.TaskCep.Entidades.Usuario;
import com.Main.TaskCep.Services.UsuarioService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ControllerMain {
    @InjectMocks
    private ControllerMain controllerMain;
    @Mock
    private UsuarioService usuarioService;


    private Usuario createUsuario(){
        Usuario usuario = Usuario.builder().authorities("Role_ADMIN").cep("7640000")
                .cpf("06329100110")
                .password("sexo").username("Artur").build();
        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encode = delegatingPasswordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encode);
        return usuario;
    }

}
