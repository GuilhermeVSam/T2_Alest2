import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Os arquivos de teste devem ser colocados dentro da pasta 'Casos de Teste'");
        System.out.println("Digite o nome do arquivo a ser testado (Ex: caixas.txt)");
        String filePath = "Casos de Teste/" + sc.nextLine();
        long start = System.nanoTime();
        ArrayList<Vertice> vertices = new ArrayList<Vertice>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            Digrafo digrafo = new Digrafo();
            while ((line = br.readLine()) != null) {
                List<Integer> values = new ArrayList<>();
                for (String value : line.split(" ")) {
                    values.add(Integer.parseInt(value));
                }
                values.sort(null);
                Vertice v = new Vertice(values.get(0), values.get(1), values.get(2));
                vertices.add(v);
            }
            for (Vertice v : vertices) {
                digrafo.addVertice(v);
            }
            digrafo.makeArestas();
            vertices.sort(Comparator.comparingInt(Vertice::getMin).reversed());
            digrafo.maxInceptionRecursivo(vertices.get(0));
            System.out.println(digrafo.getMaxInception());
            long end = System.nanoTime();
            long tempoDeExecucao = end - start;
            System.out.println("Tempo de Execução: " + tempoDeExecucao / 1000000 + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}