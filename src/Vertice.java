import java.util.ArrayList;

public class Vertice {
    private int min;
    private int med;
    private int max;
    private ArrayList<Vertice> adjacentes;

    public Vertice(int min, int med, int max){
        this.min = min;
        this.med = med;
        this.max = max;
        this.adjacentes = new ArrayList<Vertice>();
    }

    public void addAdjacente(Vertice v){
        this.adjacentes.add(v);
    }

    public int getAdjacenteSize(){
        return this.adjacentes.size();
    }

    public ArrayList<Vertice> getAdjacentes(){
        return this.adjacentes;
    }

    public int getMin(){
        return this.min;
    }

    public int getMed(){
        return this.med;
    }

    public int getMax(){
        return this.max;
    }

    public int getVolume(){
        return this.min * this.med * this.max;
    }
}
