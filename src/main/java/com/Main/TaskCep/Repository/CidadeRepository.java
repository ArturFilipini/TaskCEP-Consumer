package com.Main.TaskCep.Repository;

import com.Main.TaskCep.Entidades.Cidades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidades, Long> {
    List<Cidades> findByCep(String cep);
}
