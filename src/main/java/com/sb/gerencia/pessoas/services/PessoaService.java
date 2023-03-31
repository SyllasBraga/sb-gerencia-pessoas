package com.sb.gerencia.pessoas.services;

import com.sb.gerencia.pessoas.dtos.PessoaDto;
import com.sb.gerencia.pessoas.entities.Endereco;
import com.sb.gerencia.pessoas.entities.Pessoa;
import com.sb.gerencia.pessoas.repositories.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;
    private ModelMapper modelMapper;

    public PessoaService(PessoaRepository pessoaRepository, ModelMapper modelMapper) {
        this.pessoaRepository = pessoaRepository;
        this.modelMapper = modelMapper;
    }

    public PessoaDto pessoaToPessoaDto(Pessoa pessoa){
        return modelMapper.map(pessoa, PessoaDto.class);
    }

    public Pessoa pessoaDtoToPessoa(PessoaDto pessoaDto){
        return modelMapper.map(pessoaDto, Pessoa.class);
    }

}
