package com.example.prova.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.prova.model.Musica;
import com.example.prova.repository.MusicaRepository;
import com.example.prova.shared.MusicaDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicaServiceImpl implements MusicaService {

    @Autowired
    MusicaRepository repositorioMusica;

    @Override
    public List<MusicaDTO> obterTodos() {
        List<Musica> musicas = repositorioMusica.findAll();
        ModelMapper mapper = new ModelMapper();

        List<MusicaDTO> musicasDto = musicas.stream()
        .map(musica-> mapper.map(musica, MusicaDTO.class))
        .collect(Collectors.toList());

        return musicasDto;
    }

    @Override
    public MusicaDTO obterPorId(String idMusica) {
        Optional<Musica> musica = repositorioMusica.findById(idMusica);

        if(musica.isEmpty()){
            throw new InputMismatchException("Música nãoi encontrada com este ID: " + idMusica);
        }

        return new ModelMapper().map(musica.get(), MusicaDTO.class);
    }

    @Override
    public MusicaDTO adicionar(MusicaDTO musicaDto) {
        musicaDto.setId(null);
        ModelMapper mapper = new ModelMapper();

        Musica musica = mapper.map(musicaDto, Musica.class);
        musica = repositorioMusica.save(musica);

        return mapper.map(musica, MusicaDTO.class);
    }

    @Override
    public Musica atualizar(String idMusica, Musica musica) {
        musica.setId(idMusica);
        return repositorioMusica.save(musica);
    }

    @Override
    public void deletar(String idMusica) {
        Optional<Musica> musica = repositorioMusica.findById(idMusica);

        if(musica.isEmpty()){
            throw new InputMismatchException("Não é possível deletar música com este ID: " + idMusica + "Música não encontrada");

        }

        repositorioMusica.deleteById(idMusica);
        
    }
    
}
