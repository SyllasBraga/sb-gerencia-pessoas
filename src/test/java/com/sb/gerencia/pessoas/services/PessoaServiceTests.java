package com.sb.gerencia.pessoas.services;

import com.sb.gerencia.pessoas.dtos.EnderecoDto;
import com.sb.gerencia.pessoas.dtos.PessoaDto;
import com.sb.gerencia.pessoas.entities.Endereco;
import com.sb.gerencia.pessoas.entities.Pessoa;
import com.sb.gerencia.pessoas.repositories.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class PessoaServiceTests {

    public static final long ID = 1L;
    public static final String JOSÉ = "José";
    public static final Timestamp DATA_NASCIMENTO = Timestamp.valueOf("2020-10-2001");
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
