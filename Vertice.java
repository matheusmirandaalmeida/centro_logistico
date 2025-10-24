import java.util.*;

public class Vertice {
    private String nome;
    private List<Aresta> arestas = new ArrayList<>();

    public Vertice(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void adicionarAresta(Aresta a) {
        arestas.add(a);
    }
}
