package com.sb.gerencia.pessoas.controller;

import com.sb.gerencia.pessoas.dtos.EnderecoDto;
import com.sb.gerencia.pessoas.dtos.PessoaDto;
import com.sb.gerencia.pessoas.services.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<PessoaDto>> getAll(){
        return ResponseEntity.ok().body(pessoaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDto> getById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(pessoaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PessoaDto> create(@RequestBody @Valid PessoaDto pessoaDto){
        return ResponseEntity.status(201).body(pessoaService.create(pessoaDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDto> update(@PathVariable(name = "id") Long id, @RequestBody @Valid PessoaDto pessoaDto){
        return ResponseEntity.status(200).body(pessoaService.update(id, pessoaDto));
    }

    @PostMapping("/{id}/enderecos")
    public ResponseEntity<PessoaDto> createEndereco(@PathVariable(name = "id") Long id,
                                                    @RequestBody @Valid EnderecoDto enderecoDto){
        return ResponseEntity.status(201).body(pessoaService.criarEnderecoParaPessoa(id, enderecoDto));
    }

    @GetMapping("/{id}/enderecos")
    public ResponseEntity<List<EnderecoDto>> getEnderecos(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(pessoaService.listarEnderecos(id));
    }

    @PutMapping("/{id}/enderecos/{id_endereco}")
    public ResponseEntity<PessoaDto> setEnderecoPrincipal(@PathVariable(name = "id") Long id,
                                                          @PathVariable(name = "id_endereco") Long idEndereco){
        return ResponseEntity.ok().body(pessoaService.setarEnderecoPrincipal(id, idEndereco));
    }
}
