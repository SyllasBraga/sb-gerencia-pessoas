package com.sb.gerencia.pessoas.services;

import com.sb.gerencia.pessoas.dtos.EnderecoDto;
import com.sb.gerencia.pessoas.entities.Endereco;
import com.sb.gerencia.pessoas.entities.Pessoa;
import com.sb.gerencia.pessoas.repositories.EnderecoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;
    private ModelMapper modelMapper;

    public EnderecoService(EnderecoRepository enderecoRepository, ModelMapper modelMapper) {
        this.enderecoRepository = enderecoRepository;
        this.modelMapper = modelMapper;
    }

    public boolean createEndereco(EnderecoDto enderecoDto, Pessoa pessoa){
        Endereco endereco = enderecoDtoToEndereco(enderecoDto);
        endereco.setPessoa(pessoa);

        if (enderecoRepository.save(endereco) != null){
            return true;
        }
        return false;
    }

    public EnderecoDto getById(Long idEndereco){

        Endereco endereco = enderecoRepository.findById(idEndereco).get();
        EnderecoDto enderecoDto = enderecoToEnderecoDto(endereco);

        return enderecoDto;

    }

    public Endereco enderecoDtoToEndereco(EnderecoDto enderecoDto){
        return modelMapper.map(enderecoDto, Endereco.class);
    }

    public EnderecoDto enderecoToEnderecoDto(Endereco endereco){
        return modelMapper.map(endereco, EnderecoDto.class);
    }
}
