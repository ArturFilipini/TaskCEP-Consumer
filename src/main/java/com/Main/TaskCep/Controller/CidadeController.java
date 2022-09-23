package com.Main.TaskCep.Controller;

import com.Main.TaskCep.Entidades.Cidades;
import com.Main.TaskCep.Services.CidadeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/cidade")
@AllArgsConstructor
public class CidadeController {
    private final CidadeService cidadeService;
    @GetMapping("/save/{cep}")
    public ResponseEntity<Cidades> save(@PathVariable String cep){

           return new ResponseEntity<>(cidadeService.GetAndSave(cep), HttpStatus.CREATED);

    }

}
