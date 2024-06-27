import java.util.*;

public class Digrafo {
    private HashMap<Vertice, Integer> vertices;
    List<Vertice> visitados;
    Vertice raiz;

    public Digrafo(){
        this.vertices = new HashMap<>();
        this.visitados = new ArrayList<>();
        this.raiz = null;
    }

    public void addVertice(Vertice v){
        vertices.put(v, 0);
    }

    public void addAresta(Vertice v, Vertice w){
        v.addAdjacente(w);
    }

    public int getVertice(Vertice key){
        return vertices.get(key);
    }

    public void makeArestas(){
        for(Vertice v : vertices.keySet()){
            for(Vertice w : vertices.keySet()){
                if(canFit(w, v)){
                    addAresta(v, w);
                }
            }
        }
    }

    public void maxInceptionRecursivo(Vertice v){
        if(vertices.get(v) != 0) {
            return;
        }
        ArrayList<Vertice> adjacentes = v.getAdjacentes();
        if(adjacentes.isEmpty()){
            vertices.put(v, 1);
            return;
        }
        adjacentes.sort(Comparator.comparingInt(Vertice::getVolume).reversed()
                .thenComparingInt(Vertice::getMin)
                .thenComparingInt(Vertice::getMed)
                .thenComparingInt(Vertice::getMax));
        int maxInception = 0;
        for(Vertice vertice : adjacentes){
            maxInceptionRecursivo(vertice);
            int verticeInception = vertices.get(vertice);
            if(verticeInception > maxInception){
                maxInception = verticeInception;
            }
        }
        vertices.put(v, maxInception + 1);
    }

    public int getMaxInception(){
        int max = 0;
        for(Vertice v : vertices.keySet()){
            if(vertices.get(v) > max){
                max = vertices.get(v);
            }
        }
        return max;
    }

    public boolean canFit(Vertice v1, Vertice v2){
        return v1.getMin() < v2.getMin() && v1.getMed() < v2.getMed() && v1.getMax() < v2.getMax();
    }
}
