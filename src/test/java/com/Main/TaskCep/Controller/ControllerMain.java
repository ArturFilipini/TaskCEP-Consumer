package com.Main.TaskCep.Controller;

import com.Main.TaskCep.Entidades.Cidades;
import com.Main.TaskCep.Entidades.Usuario;
import com.Main.TaskCep.Repository.UsuarioRepository;
import com.Main.TaskCep.Services.UsuarioService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ControllerMain {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @TestConfiguration
    @Lazy
    static class Config{
        @Bean(name = "testRestTemplateRoleUser")
        public TestRestTemplate testRestTemplateRolesUserCreator(@Value("${local.server.port") int port){
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                    .rootUri("http://localhost:"+port)
                    .basicAuthentication("Artur", "artur");
            return new TestRestTemplate(restTemplateBuilder);
        }
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

}
