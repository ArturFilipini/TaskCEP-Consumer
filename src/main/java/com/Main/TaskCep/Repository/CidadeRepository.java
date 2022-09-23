package com.Main.TaskCep.Repository;

import com.Main.TaskCep.Entidades.Cidades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidades, Long> {
}
