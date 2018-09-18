package entrelaçamento;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;

public class Merge {
	public static final String fileName1 = "C:\\Users\\Ricardo\\Downloads\\arquivo1.dat";
	public static final String fileName2 = "C:\\Users\\Ricardo\\Downloads\\arquivo2.dat";
	public static final String fileName3 = "C:\\Users\\Ricardo\\Downloads\\arquivo3.dat";
	public static void main(String[] args)throws FileNotFoundException, IOException {
        
        try{
            RandomAccessFile f = new RandomAccessFile("C:\\Users\\Ricardo\\Downloads\\cep_ordenado.dat", "r");
            
            Endereco e = new Endereco();
            Endereco g = new Endereco();
            Endereco h = new Endereco();
            long inicio = 0;
            long fim = f.length()/300 - 1;//usando .length sem subtrair um, acarretaria um out of bounds
            RandomAccessFile arquivo1 = new RandomAccessFile(fileName1, "rw");
            RandomAccessFile arquivo2 = new RandomAccessFile(fileName2, "rw");
            RandomAccessFile arquivo3 = new RandomAccessFile(fileName3, "rw");
            while (inicio < (fim / 10000 )) {
            	f.seek(inicio);
            	e.leEndereco(f);
            	Random gerador = new Random();
            	int i = gerador.nextInt();
            	if (i > 0) {
            		arquivo1.writeChars(e.getCep());
            		arquivo1.writeChars(e.getLogradouro());
            	}
            	else {
            		arquivo2.writeChars(e.getCep());
            		arquivo2.writeChars(e.getLogradouro());
            	}
            	inicio++;
            }
            int p = 0;
            int j = 0;
            int k = 0;
            if (arquivo1.length() > 0 && arquivo2.length() > 0) {
            	while (p < arquivo1.length() && j < arquivo2.length()) {
                    arquivo1.seek(inicio);	//comparar o cep do arquivo 1 e 2 para decidir o menor
                    g.leEndereco(arquivo1);
                    arquivo2.seek(inicio);
                    h.leEndereco(arquivo2);
                    if (g.compareTo(h) < 0){
                        arquivo3.writeChars(g.getCep());
                        arquivo3.writeChars(g.getLogradouro());
                        p++;
                    }
                    else{
                        arquivo3.writeChars(h.getCep());
                        arquivo3.writeChars(h.getLogradouro());
                        j++;
                    }
                    k++;
                }
            }
        while (inicio < (fim / 10000 )) {
            	arquivo3.seek(inicio);
            	e.leEndereco(arquivo3);
            	System.out.println(e);
            	inicio++;
            }	
        }
        
        catch(IOException ex){
        
            System.out.println("Arquivo não encontrado");
        
        }
    }
}
