package br.com.rafaeboff.comTabela.produtos.criacao;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class Produto {
    private int codigo;
    private String nome;
    private BigDecimal preco;
    private int quantidade;

}
