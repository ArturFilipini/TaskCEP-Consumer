package com.Main.TaskCep.Controller;

import com.Main.TaskCep.Entidades.Cidades;
import com.Main.TaskCep.Services.CidadeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/main")
@AllArgsConstructor
@CrossOrigin("*")
public class CidadeController {
    private final CidadeService cidadeService;
    @GetMapping("/save/{cep}")
    public ResponseEntity<Cidades> save(@PathVariable String cep){

           return new ResponseEntity<>(cidadeService.GetAndSave(cep), HttpStatus.CREATED);

    }
    @GetMapping("/list/{id}")
    public ResponseEntity<List<Cidades>> listById(@PathVariable Long id){
        return new ResponseEntity<>(cidadeService.listById(id), HttpStatus.OK);
    }
    @GetMapping("/listall")
    public ResponseEntity<List<Cidades>> listall(){
        return new ResponseEntity<>(cidadeService.listall(), HttpStatus.OK);
    }

}
