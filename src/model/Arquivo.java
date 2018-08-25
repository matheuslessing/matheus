package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
  * @author Gilberto Toledo
 */
public class Arquivo {
     
    public static String Read(String Caminho){
        String conteudo = "";
        try {
            FileReader arq = new FileReader(Caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha="";
            try {
                linha = lerArq.readLine();
                while(linha!=null){
                    conteudo += linha+"\n";
                    linha = lerArq.readLine();
                }
                arq.close();
                return conteudo;
            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
                return "";
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
            return "";
        }
    }   
    
    public static boolean Write(String texto){
        try {
            String caminho = "teste.txt";
            FileWriter arq = new FileWriter(caminho);
            try (PrintWriter gravarArq = new PrintWriter(arq)) {
                gravarArq.println(texto);
                gravarArq.close();
            }
            return true;
        }catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
