package com.sb.gerencia.pessoas.services;

import com.sb.gerencia.pessoas.dtos.EnderecoDto;
import com.sb.gerencia.pessoas.dtos.PessoaDto;
import com.sb.gerencia.pessoas.entities.Endereco;
import com.sb.gerencia.pessoas.entities.Pessoa;
import com.sb.gerencia.pessoas.exceptions.ResourceNotFoundException;
import com.sb.gerencia.pessoas.repositories.PessoaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class PessoaServiceTests {

    public static final long ID = 1L;
    public static final String JOSÉ = "José";
    public static final Timestamp DATA_NASCIMENTO = Timestamp.valueOf("2020-10-21 00:00:00");
    public static final long ID1 = 1L;
    public static final String LOGRADOURO = "Rua José Francisco";
    public static final String CEP = "12345-000";
    public static final int NUMERO = 12;
    public static final String CIDADE = "Rio de Janeiro";

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private EnderecoService enderecoService;

    @Spy
    private ModelMapper modelMapper;

    private PessoaDto pessoaDto;
    private Pessoa pessoa;
    private Optional<Pessoa> optPessoa;
    private EnderecoDto enderecoDto;
    private Endereco endereco;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startObjects();
    }

    @Test
    @DisplayName("Teste: PessoaService.getAll()")
    public void quandoGetAllRetornaUmaListaPessoas(){

        when(pessoaRepository.findAll()).thenReturn(List.of(pessoa));

        List<PessoaDto> resultado = pessoaService.getAll();

        Assertions.assertEquals(PessoaDto.class, resultado.get(0).getClass());

    }

    @Test
    @DisplayName("Teste: PessoaService.getById()")
    public void quandoGetByIdRetornaUmaPessoa(){

        when(pessoaRepository.findById(Mockito.anyLong())).thenReturn(optPessoa);

        PessoaDto resultado = pessoaService.getById(Mockito.anyLong());

        Assertions.assertEquals(PessoaDto.class, resultado.getClass());

    }

    @Test
    @DisplayName("Teste: PessoaService.getById().ResourceNotFoundException")
    public void quandoGetByIdRetornaUmaResourceNotFoundException(){

        when(pessoaRepository.findById(Mockito.anyLong())).thenThrow(new ResourceNotFoundException("Pessoa não encontrada."));

        try {
            pessoaService.getById(Mockito.anyLong());
        }catch (Exception ex){
            Assertions.assertEquals(ResourceNotFoundException.class, ex.getClass());
        }

    }

    @Test
    @DisplayName("Teste: PessoaService.create()")
    public void quandoCreateRetornaUmaPessoa() {

        when(pessoaRepository.save(Mockito.any())).thenReturn(pessoa);

        PessoaDto resultado = pessoaService.create(pessoaDto);

        Assertions.assertEquals(PessoaDto.class, resultado.getClass());
    }

    @Test
    @DisplayName("Teste: PessoaService.update()")
    public void quandoUpdateRetornaUmaPessoa() {

        when(pessoaRepository.findById(Mockito.anyLong())).thenReturn(optPessoa);
        when(pessoaRepository.save(Mockito.any())).thenReturn(pessoa);

        PessoaDto resultado = pessoaService.update(pessoaDto.getId(), pessoaDto);

        Assertions.assertEquals(PessoaDto.class, resultado.getClass());
    }

    @Test
    @DisplayName("Teste: PessoaService.update().ResourceNotFoundException")
    public void quandoUpdateRetornaUmResourceNotFoundException() {

        when(pessoaRepository.findById(Mockito.anyLong())).thenThrow(new ResourceNotFoundException("Pessoa não encontrada."));

        try {
            pessoaService.update(pessoaDto.getId(), pessoaDto);
        }catch (Exception ex){
            Assertions.assertEquals(ResourceNotFoundException.class, ex.getClass());
        }
    }

    @Test
    @DisplayName("Teste: PessoaService.deletar()")
    public void quandoExcluirRetornaUmaString(){
        when(pessoaRepository.findById(Mockito.anyLong())).thenReturn(optPessoa);
        doNothing().when(pessoaRepository).deleteById(Mockito.anyLong());

        String resultado = pessoaService.deletar(pessoa.getId());

        Assertions.assertEquals(String.class, resultado.getClass());
    }

    @Test
    @DisplayName("Teste: PessoaService.deletar().ResourceNotFoundException")
    public void quandoDeleteRetornaUmResourceNotFoundException() {

        when(pessoaRepository.findById(Mockito.anyLong())).thenThrow(new ResourceNotFoundException("Pessoa não encontrada."));

        try {
            pessoaService.deletar(pessoa.getId());
        }catch (Exception ex){
            Assertions.assertEquals(ResourceNotFoundException.class, ex.getClass());
        }
    }

    private void startObjects(){
        endereco = new Endereco(ID1, LOGRADOURO, CEP, NUMERO,
                CIDADE, true, null);
        enderecoDto = new EnderecoDto(LOGRADOURO, CEP, NUMERO,
                CIDADE, true);
        pessoaDto = new PessoaDto(ID, JOSÉ, DATA_NASCIMENTO, List.of(enderecoDto));
        pessoa = new Pessoa(ID, JOSÉ, DATA_NASCIMENTO, List.of(endereco));
        optPessoa = Optional.of(new Pessoa(ID, JOSÉ, DATA_NASCIMENTO, List.of(endereco)));
        endereco.setIdPessoa(pessoa);
    }
}
