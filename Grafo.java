import java.util.*;

public class Grafo {
    private Map<String, Vertice> vertices = new HashMap<>();

    public void adicionarVertice(String nome) {
        vertices.putIfAbsent(nome, new Vertice(nome));
    }

    public void adicionarAresta(String origem, String destino, double peso) {
        Vertice vOrigem = vertices.get(origem);
        Vertice vDestino = vertices.get(destino);
        if (vOrigem != null && vDestino != null) {
            Aresta aresta = new Aresta(vOrigem, vDestino, peso);
            vOrigem.adicionarAresta(aresta);
        }
    }

    public Vertice getVertice(String nome) {
        return vertices.get(nome);
    }

    public Collection<Vertice> getVertices() {
        return vertices.values();
    }
}
