package com.example.prova.service;

import java.util.List;
import com.example.prova.model.Musica;
import com.example.prova.shared.MusicaDTO;

public interface MusicaService {
    
    List<MusicaDTO> obterTodos();

    MusicaDTO obterPorId(String idMusica);

    MusicaDTO adicionar(MusicaDTO musica);

    Musica atualizar(String idMusica, Musica musica);

    void deletar(String idMusica);
}
