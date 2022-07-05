package br.com.rafaeboff.comTabela.clientes;

import br.com.rafaeboff.comTabela.clientes.criacao.DadosArquivo;
import br.com.rafaeboff.comTabela.clientes.criacao.Pessoa;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CriaArquivo {
    private static final String FONT_VERDANA = "Verdana";

    public void gerarArquivo(final DadosArquivo dados, final List<Pessoa> pessoas) throws IOException, URISyntaxException, InvalidFormatException {
        try (var documento = new XWPFDocument()) {
            addTitulo(documento, dados.getTitulo());
            addSubTitulo(documento, dados.getSubTitulo());
            addImagem(documento, dados.getImagem());
            addSaudacao(documento, dados.getSaudacao());
            dados.getParagrafos().forEach(p -> addParagrafo(documento, p));

            addTabela(documento, pessoas);

            try (var saida = new FileOutputStream(dados.getNomeArquivo())) {
                documento.write(saida);
            }
        }
    }

    private void addTitulo(final XWPFDocument documento, final String titulo) {
        XWPFParagraph paragrafoTitulo = documento.createParagraph();
        paragrafoTitulo.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun paragrafoTituloRun = paragrafoTitulo.createRun();
        paragrafoTituloRun.setText(titulo);
        paragrafoTituloRun.setColor("000000");
        paragrafoTituloRun.setBold(true);
        paragrafoTituloRun.setFontFamily(FONT_VERDANA);
        paragrafoTituloRun.setFontSize(20);
    }

    private void addSubTitulo(final XWPFDocument documento, final String subTitulo) {
        XWPFParagraph paragrafoSubTitulo = documento.createParagraph();
        paragrafoSubTitulo.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun paragrafoSubTituloRun = paragrafoSubTitulo.createRun();
        paragrafoSubTituloRun.setText(subTitulo);
        paragrafoSubTituloRun.setColor("000000");
        paragrafoSubTituloRun.setBold(true);
        paragrafoSubTituloRun.setFontFamily(FONT_VERDANA);
        paragrafoSubTituloRun.setFontSize(17);
    }

    private void addImagem(final XWPFDocument documento, final String imagem) throws IOException, URISyntaxException, InvalidFormatException{
        // definindo configurações da imagem no documento
        XWPFParagraph paragrafoImagem = documento.createParagraph();
        paragrafoImagem.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun imagemRun = paragrafoImagem.createRun();
        imagemRun.setTextPosition(20);
        // carregamento da imagem:
        var imagemPath= Paths.get(ClassLoader.getSystemResource(imagem).toURI());
        imagemRun.addPicture(Files.newInputStream(imagemPath), Document.PICTURE_TYPE_JPEG,
                imagemPath.getFileName().toString(), Units.toEMU(50), Units.toEMU(50));
    }

    private void addSaudacao(final XWPFDocument documento, final String saudacao) {
        XWPFParagraph paragrafoSaudacao = documento.createParagraph();
        XWPFRun saudacaoRun = paragrafoSaudacao.createRun();
        saudacaoRun.setText(saudacao);
        saudacaoRun.setColor("000000");
        saudacaoRun.setBold(true);
        saudacaoRun.setFontFamily(FONT_VERDANA);
    }

    private void addParagrafo(final XWPFDocument documento, final String conteudo) {
        XWPFParagraph paragrafo = documento.createParagraph();
        paragrafo.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun paragrafoRun = paragrafo.createRun();
        paragrafoRun.setText(conteudo);
    }

    // TABELA:
    private void addTabela(final XWPFDocument documento, final List<Pessoa> pessoas) {
        var tabela= documento.createTable();
        XWPFTableRow linha = tabela.getRow(0);
        linha.getCell(0).setText("Código");
        linha.addNewTableCell().setText("Nome");
        linha.addNewTableCell().setText("Idade");
        linha.addNewTableCell().setText("Endereço");
        linha.addNewTableCell().setText("Saldo");

        pessoas.forEach(p -> criarLinha(tabela, p)); // Para cada pessoa cria uma nova linha
    }

    private void criarLinha(XWPFTable tabela, Pessoa p) {
        XWPFTableRow tableRowTwo = tabela.createRow();
        tableRowTwo.getCell(0).setText("" + p.getCodigo());
        tableRowTwo.getCell(1).setText("" + p.getNome());
        tableRowTwo.getCell(2).setText("" + p.getIdade());
        tableRowTwo.getCell(3).setText("" + p.getEndereco());
        tableRowTwo.getCell(4).setText("" + p.getSaldo()); //.multiply(BigDecimal.valueOf(p.getQuantidade())));
    }

}
