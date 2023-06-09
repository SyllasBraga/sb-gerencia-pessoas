package com.sb.gerencia.pessoas.services;

import com.sb.gerencia.pessoas.dtos.EnderecoDto;
import com.sb.gerencia.pessoas.dtos.PessoaDto;
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
    private EnderecoService enderecoService;

    public PessoaService(PessoaRepository pessoaRepository, ModelMapper modelMapper, EnderecoService enderecoService) {
        this.pessoaRepository = pessoaRepository;
        this.modelMapper = modelMapper;
        this.enderecoService = enderecoService;
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

        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Pessoa não encontrada."));
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
            return "A pessoa de nome "+ x.getNome() +" foi excluída do sistema";
        }).orElseThrow(()-> new ResourceNotFoundException("Pessoa não encontrada"));
    }

    //Requisito: Criar endereço para pessoa
    public PessoaDto criarEnderecoParaPessoa(Long idPessoa, EnderecoDto enderecoDto){

        Pessoa pessoa = pessoaDtoToPessoa(getById(idPessoa));
        pessoa.setId(idPessoa);

        enderecoService.createEndereco(enderecoDto, pessoa);

        return getById(pessoa.getId());
    }

    //Requisito: Listar endereços da pessoa
    public List<EnderecoDto> listarEnderecos(Long idPessoa){

        PessoaDto pessoaDto = getById(idPessoa);

        return pessoaDto.getEnderecos();
    }

    //Requisito: Poder informar qual endereço é o principal da pessoa
    public PessoaDto setarEnderecoPrincipal(Long idPessoa, Long idEndereco){

        PessoaDto pessoaDto = getById(idPessoa);

        EnderecoDto enderecoDto = enderecoService.getById(idEndereco);

        if (pessoaDto.getEnderecos().contains(enderecoDto)){
            enderecoService.setarEndereco(pessoaDtoToPessoa(pessoaDto), idEndereco);
        }else{
            throw new ResourceNotFoundException("Endereço não encontrado.");
        }

        return getById(idPessoa);

    }

    private PessoaDto pessoaToPessoaDto(Pessoa pessoa){
        return modelMapper.map(pessoa, PessoaDto.class);
    }

    private Pessoa pessoaDtoToPessoa(PessoaDto pessoaDto){
        return modelMapper.map(pessoaDto, Pessoa.class);
    }

}
