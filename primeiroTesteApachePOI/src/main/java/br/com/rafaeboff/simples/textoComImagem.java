package br.com.rafaeboff.simples;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class textoComImagem {
    private static String arquivo = "TextoIlustracao.docx";
    private static final String FONT_VERDANA = "Verdana";
    private static final String FONT_FORTE = "Forte";

    public static void main(String[] args) throws Exception {
        try(var document = new XWPFDocument()){
            //Título:
            XWPFParagraph titulo = document.createParagraph();
            titulo.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun tituloRun = titulo.createRun();
            tituloRun.setText("Lorem Ipsum");
            tituloRun.setColor("000000");
            tituloRun.setFontFamily(FONT_FORTE);
            tituloRun.setFontSize(25);

            XWPFParagraph subtitulo = document.createParagraph();
            subtitulo.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun subtituloRun = subtitulo.createRun();
            subtituloRun.setText("What is Lorem Ipsum?");
            subtituloRun.setColor("000000");
            subtituloRun.setFontFamily(FONT_VERDANA);
            subtituloRun.setFontSize(14);

            XWPFParagraph texto = document.createParagraph();
            texto.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun textoRun = texto.createRun();
            textoRun.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry.\n" +
                    "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,\n" +
                    "when an unknown printer took a galley of type and scrambled it to make a type specimen book. \n" +
                    "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.\n" +
                    "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages,\n" +
                    "and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
            textoRun.setColor("000000");
            textoRun.setFontFamily(FONT_VERDANA);
            textoRun.setFontSize(12);

            // adicionando imagem:
            XWPFParagraph imagem = document.createParagraph();
            imagem.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun imagemRun = imagem.createRun();
            imagemRun.setTextPosition(20);
            var imagemPath = Paths.get(ClassLoader.getSystemResource("doguinhos.jpeg").toURI()); //carregando a imagem e linkando ela
            imagemRun.addPicture(Files.newInputStream(imagemPath), Document.PICTURE_TYPE_JPEG, imagemPath.getFileName().toString(), Units.toEMU(200), Units.toEMU(150));

            try (var out = new FileOutputStream(arquivo)) {
                document.write(out);
            }
        }
    }
}
