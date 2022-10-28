package com.Main.TaskCep.Util;

import com.Main.TaskCep.Entidades.Cidades;

public class CidadesCreator {

    public static Cidades cidadeToBeSaved(){
        return Cidades.builder().cep("01001-000").logradouro("Praça da Sé").complemento("lado ímpar").bairro("Sé")
                .localidade("São Paulo").uf("SP").ibge("3550308").gia("1004").ddd("11").siafi("7107").build();
    }
    public static Cidades cidadeToValid(){
        return Cidades.builder().cep("01001-000").logradouro("Praça da Sé").complemento("lado ímpar").bairro("Sé")
                .localidade("São Paulo").uf("SP").ibge("3550308").gia("1004").ddd("11").siafi("7107").id(1L).build();
    }
}
