package br.com.rafaeboff.simples;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;

public class poemaMarioQuintana {
    private static String arquivo = "PoeminhoDoContra.docx"; // definindo nome do arquivo que será gerado junto com sua extensão
    private static final String FONT_VERDANA = "Verdana"; // definindo a fonte que será usada no documento

    public static void main(String[] args) throws Exception {
        try (var document = new XWPFDocument()) {
            // tem que ser usado o XWPF para ler e escrever arquivos docx

            //Título:
            XWPFParagraph titulo = document.createParagraph(); //criando parágrafo
            titulo.setAlignment(ParagraphAlignment.CENTER); // título centralizado
            XWPFRun tituloRun = titulo.createRun(); // cria o adicionar texto ao parágrafo
            tituloRun.setText("POEMINHO DO CONTRA");
            tituloRun.setColor("000000");
            tituloRun.setFontFamily(FONT_VERDANA);

            // Nome do autor:
            XWPFParagraph autor = document.createParagraph();
            autor.setAlignment(ParagraphAlignment.RIGHT);
            XWPFRun autorRun = autor.createRun();
            autorRun.setText("Mário Quintana");
            tituloRun.setColor("000000");
            tituloRun.setFontFamily(FONT_VERDANA);

            // verso1
            XWPFParagraph verso1 = document.createParagraph();
            verso1.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun verso1Run = verso1.createRun();
            verso1Run.setText("Todos esses que aí estão\n");
            verso1Run.setColor("000000");
            verso1Run.setFontFamily(FONT_VERDANA);

            //verso2
            XWPFParagraph verso2 = document.createParagraph();
            verso2.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun verso2Run = verso2.createRun();
            verso2Run.setText("Atravancando meu caminho,\n");
            verso2Run.setColor("000000");
            verso2Run.setFontFamily(FONT_VERDANA);

            //verso3
            XWPFParagraph verso3 = document.createParagraph();
            verso3.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun verso3Run = verso3.createRun();
            verso3Run.setText("Eles passarão...\n");
            verso3Run.setColor("000000");
            verso3Run.setFontFamily(FONT_VERDANA);

            //verso4
            XWPFParagraph verso4 = document.createParagraph();
            verso4.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun verso4Run = verso4.createRun();
            verso4Run.setText("Eu passarinho!");
            verso4Run.setColor("000000");
            verso4Run.setFontFamily(FONT_VERDANA);

            try (var out = new FileOutputStream(arquivo)) {
                document.write(out);
            }
        }
    }
}
