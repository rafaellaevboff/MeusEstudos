package br.com.rafaeboff.comTabela.clientes.criacao;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DadosArquivo {
    // define o que o arquivo word terá
    private String titulo;
    private String subTitulo;
    private String saudacao;
    private List<String> paragrafos;
    private String nomeArquivo;
    private String imagem;
}
