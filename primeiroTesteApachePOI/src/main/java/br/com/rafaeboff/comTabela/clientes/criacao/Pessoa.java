package br.com.rafaeboff.comTabela.clientes.criacao;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class Pessoa {
    private int codigo;
    private String nome;
    private int idade;
    private String endereco;
    private double saldo;
}
