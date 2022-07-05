package br.com.rafaeboff.comTabela.clientes;

import br.com.rafaeboff.comTabela.clientes.criacao.DadosArquivo;
import br.com.rafaeboff.comTabela.clientes.criacao.Pessoa;

import java.util.List;

public class MontaArquivo {

    public static void main(String[] args) throws Exception {

        var listaPessoas = gerarLista();
        var dados = DadosArquivo.builder()
                .titulo("Listagem de clientes")
                .subTitulo("Com saldo na loja")
                .saudacao("Olá! Bem vindo(a)!")
                .imagem("welcome-sign.jpeg")
                .paragrafos(
                        List.of("Veja abaixo os clientes que estão cadastrados")
                )
                .nomeArquivo("relatorioClientes.docx")
                .build();

        var criaArquivo = new CriaArquivo();
        criaArquivo.gerarArquivo(dados, listaPessoas);
    }

    private static List<Pessoa> gerarLista() {
        var pessoa1 = Pessoa.builder()
                .codigo(1)
                .nome("Maria")
                .idade(20)
                .endereco("Torres")
                .saldo(100.50)
                .build();

        var pessoa2 = Pessoa.builder()
                .codigo(2)
                .nome("Rafaella")
                .idade(19)
                .endereco("Morrinhos do Sul")
                .saldo(103.20)
                .build();

        var pessoa3 = Pessoa.builder()
                .codigo(3)
                .nome("Pedro")
                .idade(23)
                .endereco("Canoas")
                .saldo(200.30)
                .build();

        return List.of(pessoa1, pessoa2, pessoa3);
    }
}
