package br.com.rafaeboff.comTabela.produtos;

import br.com.rafaeboff.comTabela.produtos.criacao.DadosArquivo;
import br.com.rafaeboff.comTabela.produtos.criacao.Produto;

import java.math.BigDecimal;
import java.util.List;

public class MontaArquivo {
    public static void main(String[] args) throws Exception {
        var listaProdutos = gerarLista();

        var totalDoPedido = listaProdutos.stream()
                .map(p -> p.getPreco().multiply(BigDecimal.valueOf(p.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var dados = DadosArquivo.builder()
                .titulo("Listagem de produtos comprados")
                .subTitulo("Total do pedido: R$ " + totalDoPedido)
                .saudacao("Olá! Obrigado pela preferência!")
                .imagem("welcome-sign.jpeg")
                .paragrafos(
                        List.of("Veja abaixo os clientes que estão cadastrados",
                                "Deseja avaliar os produtos e a loja? Acesse o site xxxxxx.com.br"
                                )
                )
                .nomeArquivo("relatorioProdutos.docx")
                .build();

        var criaArquivo = new CriaArquivo();
        criaArquivo.gerarArquivo(dados, listaProdutos);
    }

    private static List<Produto> gerarLista() {
        var produto1 = Produto.builder()
                .codigo(1)
                .nome("Leite")
                .preco(BigDecimal.valueOf(6.50))
                .quantidade(4)
                .build();

        var produto2 = Produto.builder()
                .codigo(2)
                .nome("Pão")
                .preco(BigDecimal.valueOf(5.50))
                .quantidade(2)
                .build();

        var produto3 = Produto.builder()
                .codigo(3)
                .nome("Creme de Leite")
                .preco(BigDecimal.valueOf(3.00))
                .quantidade(3)
                .build();

        return List.of(produto1, produto2, produto3);
    }
}
