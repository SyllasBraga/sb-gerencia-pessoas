package com.sb.gerencia.pessoas.services;

import com.sb.gerencia.pessoas.dtos.PessoaDto;
import com.sb.gerencia.pessoas.entities.Endereco;
import com.sb.gerencia.pessoas.entities.Pessoa;
import com.sb.gerencia.pessoas.exceptions.ResourceNotFoundException;
import com.sb.gerencia.pessoas.repositories.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;
    private ModelMapper modelMapper;

    public PessoaService(PessoaRepository pessoaRepository, ModelMapper modelMapper) {
        this.pessoaRepository = pessoaRepository;
        this.modelMapper = modelMapper;
    }

    //Requisito: Listar pessoas
    public List<PessoaDto> getAll(){
        List<PessoaDto> listaDto = new ArrayList<>();
        List<Pessoa> lista = pessoaRepository.findAll();

        lista.forEach(x -> listaDto.add(pessoaToPessoaDto(x)));

        return listaDto;
    }

    //Requisito: Consultar uma pessoa
    public PessoaDto getById(Long id){

        Pessoa pessoa = pessoaRepository.findById(id).get();
        PessoaDto pessoaDto = pessoaToPessoaDto(pessoa);

        return pessoaDto;
    }

    //Requisito: Criar uma pessoa
    public PessoaDto create(PessoaDto pessoaDto){

        Pessoa pessoa = pessoaDtoToPessoa(pessoaDto);
        Pessoa pessoaCriada = pessoaRepository.save(pessoa);

        PessoaDto pessoaDtoCriada = pessoaToPessoaDto(pessoaCriada);

        return pessoaDtoCriada;

    }

    //Requisito: Editar uma pessoa
    public PessoaDto update(Long idPessoa, PessoaDto pessoaDto){

        return pessoaRepository.findById(idPessoa).map( Record ->{
                    Record.setDataNascimento(pessoaDto.getDataNascimento());
                    Record.setNome(pessoaDto.getNome());
                    return pessoaToPessoaDto(pessoaRepository.save(Record));
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada."));
    }

    //Requisito: Excluir uma pessoa
    public String deletar(Long idPessoa){

        return pessoaRepository.findById(idPessoa).map(x -> {
            pessoaRepository.deleteById(x.getId());
            return "A pessoa de nome "+ x.getNome() +" excluído do sistema";
        }).orElseThrow(()-> new ResourceNotFoundException("Pessoa não encontrada"));
    }

    public PessoaDto pessoaToPessoaDto(Pessoa pessoa){
        return modelMapper.map(pessoa, PessoaDto.class);
    }

    public Pessoa pessoaDtoToPessoa(PessoaDto pessoaDto){
        return modelMapper.map(pessoaDto, Pessoa.class);
    }

}
