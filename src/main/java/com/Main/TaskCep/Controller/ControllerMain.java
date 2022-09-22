package com.Main.TaskCep.Controller;

import com.Main.TaskCep.Services.UsuarioService;
import com.Main.TaskCep.UserDomain.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class ControllerMain {
    private final UsuarioService usuarioService;

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloworld(){
        return "Hello World";
    }

    @PostMapping("/usersave")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
        String encode = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(usuario.getPassword());
        usuario.setPassword(encode);
        return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
    }
}
