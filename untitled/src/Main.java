import java.nio.file.*;
import java.util.Random;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        ArvoreAVL arvore = new ArvoreAVL();
        ArvorePretoVermelho arvore2 = new ArvorePretoVermelho();

        int[] inserir_1000 = numerosAleatorios(1000, rand);
        int[] inserir_10000 = numerosAleatorios(10000, rand);
        int[] inserir_25000 = numerosAleatorios(25000,rand);

        long inicio = System.nanoTime();
        for (int i : inserir_1000) {
            arvore.inserir(i);
        }
        long fim = System.nanoTime();

        Path arquivoSaida = Paths.get("saida.txt").toAbsolutePath();
        try {
            if (!Files.exists(arquivoSaida)) {
                Files.createFile(arquivoSaida); // Cria o arquivo se ele não existir
            }
            String texto = "Tempo de inserção na AVL: " + (fim - inicio) / 1e9 + "\n";
            Files.write(arquivoSaida, texto.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro.");
            e.printStackTrace();
        }


        inicio = System.nanoTime();
        for (int i : inserir_1000) {
            arvore2.inserir(i);
        }
        fim = System.nanoTime();
        try {
            String texto = "Tempo de inserção na RB: " + (fim - inicio) / 1e9 + "\n";
            Files.write(Paths.get("saida.txt"), texto.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro.");
            e.printStackTrace();
        }


    }
    public static int[] numerosAleatorios(int size, Random rand) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }

        for (int i = 0; i < size; i++) {
            int j = rand.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        return array;
    }

    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
    }
}