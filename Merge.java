package entrelaçamento;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;

public class Merge {
	public static final String fileName1 = "/home/aluno/Downloads/arquivo1.dat";
	public static final String fileName2 = "/home/aluno/Downloads/arquivo2.dat";
	public static final String fileName3 = "/home/aluno/Downloads/arquivo3.dat";
	public static void main(String[] args)throws FileNotFoundException, IOException {
        
        try{
            RandomAccessFile f = new RandomAccessFile("/home/aluno/Downloads/cep_ordenado.dat", "r");
            
            Endereco e = new Endereco();
            Endereco g = new Endereco();
            Endereco h = new Endereco();
            long inicio = 0;
            long fim = f.length()/300 - 1;//usando .length sem subtrair um, acarretaria um out of bounds
            RandomAccessFile arquivo1 = new RandomAccessFile(fileName1, "rw");
            RandomAccessFile arquivo2 = new RandomAccessFile(fileName2, "rw");
            RandomAccessFile arquivo3 = new RandomAccessFile(fileName3, "rw");
            f.seek(inicio);
            while (inicio < fim) {
            	e.leEndereco(f);
            	Random gerador = new Random();
            	int i = gerador.nextInt();
            	if (i > 0) {
                    e.escreveEndereco(arquivo1);
                }	
            	else {
                    e.escreveEndereco(arquivo2);
                }
            	inicio++;
            }
            int p = 0;
            int j = 0;
            int k = 0;
            long tam1 = arquivo1.length()/300;
            long tam2 = arquivo2.length()/300;
            if (arquivo1.length() > 0 && arquivo2.length() > 0) {
                    arquivo1.seek(inicio);	//comparar o cep do arquivo 1 e 2 para decidir o menor
                    g.leEndereco(arquivo1);//usa seek porque arquivo está no final do seu tamanho, precisa retornar ao início
                    arquivo2.seek(inicio);
                    h.leEndereco(arquivo2);
            	while (p < tam1 && j < tam2) {
                        if (g.compareTo(h) < 0){
                        g.escreveEndereco(arquivo3);
                        g.leEndereco(arquivo1);   
                        p++;
                    }
                    else{
                        h.escreveEndereco(arquivo3);
                        h.leEndereco(arquivo2);   
                        j++;
                    }
                    k++;
                }


              while (p < tam1) {
                        g.escreveEndereco(arquivo3);
                        g.leEndereco(arquivo1);   
                    }
              while (j < tam2) {
                        h.escreveEndereco(arquivo3);
                        h.leEndereco(arquivo2);   
                    }
              
                }
 
                

            }
              
        catch(IOException ex){
        
            System.out.println("Arquivo não encontrado");
        
        }
    }
}