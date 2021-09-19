package com.example.prova.controller;

import java.util.List;

import com.example.prova.model.Musica;
import com.example.prova.service.MusicaServiceImpl;
import com.example.prova.shared.MusicaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/musicas")
public class MusicaController {

    @Autowired
    MusicaServiceImpl servicoMusica;

    @GetMapping
    public ResponseEntity<List<MusicaDTO>> obterTodos(){
        return new ResponseEntity<>(servicoMusica.obterTodos(), HttpStatus.OK);
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MusicaDTO> obterPorId(@PathVariable String id){
        MusicaDTO musicaDto = servicoMusica.obterPorId(id);
        return new ResponseEntity<>(musicaDto, HttpStatus.OK);
        
    }
    
    @PostMapping
    public ResponseEntity<MusicaDTO> adiionar(@RequestBody MusicaDTO musicaDto){
        MusicaDTO musicaCadastrada = servicoMusica.adicionar(musicaDto);
        return new ResponseEntity<>(musicaCadastrada, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id){
        servicoMusica.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Musica> atualizar(@PathVariable String id, @RequestBody Musica musica){
        Musica musicaAtualizada = servicoMusica.atualizar(id, musica);
        return new ResponseEntity<>(musicaAtualizada, HttpStatus.OK);
    
    }

}
