package com.Main.TaskCep.Controller;

import com.Main.TaskCep.Entidades.Cidades;
import com.Main.TaskCep.Services.CidadeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/main")
@AllArgsConstructor
@CrossOrigin("*")
public class CidadeController {
    private CidadeService cidadeService;
    @PostMapping("/save/{cep}")
    public ResponseEntity<Cidades> save(@PathVariable String cep){

           return new ResponseEntity<>(cidadeService.GetAndSave(cep), HttpStatus.CREATED);

    }
    @GetMapping("/list/{cep}")
    public ResponseEntity <Cidades> listByCep(@PathVariable String cep){
        return ResponseEntity.ok(cidadeService.listByCep(cep));
    }
    @GetMapping("/listall")
    public ResponseEntity<List<Cidades>> listall(){
        return new ResponseEntity<>(cidadeService.listall(), HttpStatus.OK);
    }

}
