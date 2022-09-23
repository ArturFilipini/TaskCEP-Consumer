package com.Main.TaskCep.Services;

import com.Main.TaskCep.Entidades.Cidades;
import com.Main.TaskCep.Repository.CidadeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@Log4j2
public class CidadeService {

    private final CidadeRepository cidadeRepository;

        public Cidades GetAndSave(String cep){
            RestTemplate restTemplate = new RestTemplate();
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
                restTemplate = restTemplateBuilder.build();
            log.info("https://viacep.com.br/ws/"+cep+"/json/");
                Cidades cidades = restTemplate.getForObject("https://viacep.com.br/ws/"+cep+"/json/",Cidades.class);
        return cidadeRepository.save(cidades);

    }
}
