package com.Main.TaskCep.Repository;

import com.Main.TaskCep.Entidades.Cidades;
import com.Main.TaskCep.Entidades.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CidadeRepositoryTest {
    @Autowired
   private CidadeRepository cidadeRepository;

    private Cidades createCidade(){
        Cidades cidade = Cidades.builder().cep("01001-000").logradouro("Praça da Sé").complemento("lado ímpar").bairro("Sé")
                .localidade("São Paulo").uf("SP").ibge("3550308").gia("1004").ddd("11").siafi("7107").build();
        return cidade;
    }

    @Test
    @DisplayName("Save persists Cidades when Successful")
    void save_PersistCidades_WhenSuccessful(){
        Cidades CidadeToBeSaved = createCidade();

        Cidades CidadeSaved = this.cidadeRepository.save(CidadeToBeSaved);

        Assertions.assertThat(CidadeSaved).isNotNull();

        Assertions.assertThat(CidadeSaved.getId()).isNotNull();

        Assertions.assertThat(CidadeSaved.getCep()).isEqualTo(CidadeToBeSaved.getCep());
    }

    @Test
    @DisplayName("Save updates Cidades when Successful")
    void save_UpdatesCidades_WhenSuccessful(){
        Cidades CidadeToBeSaved = createCidade();

        Cidades CidadeSaved = this.cidadeRepository.save(CidadeToBeSaved);

        CidadeSaved.setCep("7640000");

        Cidades cidadeupdate = this.cidadeRepository.save(CidadeSaved);

        Assertions.assertThat(cidadeupdate).isNotNull();

        Assertions.assertThat(cidadeupdate.getId()).isNotNull();

        Assertions.assertThat(cidadeupdate.getCep()).isEqualTo(CidadeSaved.getCep());
    }

    @Test
    @DisplayName("Delete removes Cidades when Successful")
    void delete_RemovesCidades_WhenSuccessful(){
        Cidades CidadeToBeSaved = createCidade();

        Cidades CidadeSaved = this.cidadeRepository.save(CidadeToBeSaved);

        this.cidadeRepository.delete(CidadeSaved);

        Optional<Cidades> cidadesOptional = this.cidadeRepository.findById(CidadeSaved.getId());

        Assertions.assertThat(cidadesOptional).isEmpty();

    }

    @Test
    @DisplayName("Find By Cep returns list of Cidades when Successful")
    void findByName_ReturnsListOfCidades_WhenSuccessful(){
        Cidades cidadeToBeSave = createCidade();

        Cidades cidadeSaved = this.cidadeRepository.save(cidadeToBeSave);

        String cep = cidadeSaved.getCep();

        List<Cidades> cidadesList = this.cidadeRepository.findByCep(cep);

        Assertions.assertThat(cidadesList)
                .isNotEmpty()
                .contains(cidadeSaved);

    }
    

}