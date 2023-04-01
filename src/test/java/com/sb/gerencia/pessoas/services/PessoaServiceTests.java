package com.sb.gerencia.pessoas.services;

import com.sb.gerencia.pessoas.dtos.EnderecoDto;
import com.sb.gerencia.pessoas.dtos.PessoaDto;
import com.sb.gerencia.pessoas.entities.Endereco;
import com.sb.gerencia.pessoas.entities.Pessoa;
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

        Mockito.when(pessoaRepository.findAll()).thenReturn(List.of(pessoa));

        List<PessoaDto> resultado = pessoaService.getAll();

        Assertions.assertEquals(PessoaDto.class, resultado.get(0).getClass());

    }

    @Test
    @DisplayName("Teste: PessoaService.getById()")
    public void quandoGetByIdRetornaUmaPessoa(){

        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenReturn(optPessoa);

        PessoaDto resultado = pessoaService.getById(Mockito.anyLong());

        Assertions.assertEquals(PessoaDto.class, resultado.getClass());

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
