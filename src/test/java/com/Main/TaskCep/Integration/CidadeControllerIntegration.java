package com.Main.TaskCep.Integration;

import com.Main.TaskCep.Entidades.Cidades;
import com.Main.TaskCep.Entidades.Usuario;
import com.Main.TaskCep.Repository.CidadeRepository;
import com.Main.TaskCep.Repository.UsuarioRepository;
import com.Main.TaskCep.Util.CidadesCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CidadeControllerIntegration {
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    @Qualifier(value = "testRestTemplateRoleUser")
    private TestRestTemplate testRestTemplateRoleUser;

    @Autowired
    @Qualifier(value = "testRestTemplateRoleAdmin")
    private TestRestTemplate testRestTemplateRoleAdmin;


    private static final Usuario USER = Usuario.builder().username("Vitor")
            .cep("7640000")
            .cpf("675.633.540-50")
            .password("{bcrypt}$2a$10$aOXHubr7KqGH3sSi.aZXFOWAUOJsuLe5JHDYFmQ4qdWSCCk5Rq7B.")
            .authorities("ROLE_USER").build();

    private static final Usuario ADMIN = Usuario.builder().username("Artur")
            .cep("7640000")
            .cpf("834.616.480-78")
            .password("{bcrypt}$2a$10$Y3Xus7uIwlLxKP3jFwwaquzmN3w7s23XYii18OskTjWKqhlrDZpR2")
            .authorities("ROLE_ADMIN,ROLE_USER").build();

    @TestConfiguration
    @Lazy
    static class Config {
        @Bean(name = "testRestTemplateRoleUser")
        public TestRestTemplate testRestTemplateRoleUserCreator(@Value("${local.server.port}") int port) {
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                    .rootUri("http://localhost:"+port)
                    .basicAuthentication("Vitor", "vitor");
            return new TestRestTemplate(restTemplateBuilder);
        }
        @Bean(name = "testRestTemplateRoleAdmin")
        public TestRestTemplate testRestTemplateRoleAdminCreator(@Value("${local.server.port}") int port) {
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                    .rootUri("http://localhost:"+port)
                    .basicAuthentication("Artur", "artur");
            return new TestRestTemplate(restTemplateBuilder);
        }
    }

    @Test
    @DisplayName("listAll returns all citys when successful")
    void listall_returns_all_citys() {
        Cidades savedCidades = cidadeRepository.save(CidadesCreator.cidadeToBeSaved());
        usuarioRepository.save(USER);

        String expectedName = savedCidades.getCep();

        List<Cidades> cidades = testRestTemplateRoleUser.exchange("/main/listall", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Cidades>>() {
                }).getBody();

        Assertions.assertThat(cidades)
                .isNotNull()
                .isNotEmpty();

        Assertions.assertThat(cidades.get(0).getCep()).isEqualTo(expectedName);
    }
    @Test
    @DisplayName("list returns a citys when successful")
    void list_returns_a_city() {
        Cidades savedCidades = cidadeRepository.save(CidadesCreator.cidadeToValid());
        usuarioRepository.save(USER);

        String expectedName = savedCidades.getCep();
        String expectedId = savedCidades.getCep();

        Cidades cidades = testRestTemplateRoleUser.getForObject("/main/list/{cep}", Cidades.class, expectedId);


        Assertions.assertThat(cidades)
                .isNotNull().isNotNull().isEqualTo(savedCidades);

        Assertions.assertThat(cidades.getCep()).isEqualTo(expectedName);
        Assertions.assertThat(cidades.getId()).isEqualTo(savedCidades.getId());
    }

    @Test
    @DisplayName("save returns Cidades when successful")
    void save_ReturnsCidades_WhenSuccessful(){
        Cidades cidades = CidadesCreator.cidadeToBeSaved();
        String expectedCep = cidades.getCep();
        usuarioRepository.save(USER);
        ResponseEntity<Cidades> cidadesResponseEntity = testRestTemplateRoleUser.postForEntity("/main/save/{cep}", cidades, Cidades.class, expectedCep);

        Assertions.assertThat(cidadesResponseEntity).isNotNull();
        Assertions.assertThat(cidadesResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(cidadesResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(cidadesResponseEntity.getBody().getId()).isNotNull();

    }
}
