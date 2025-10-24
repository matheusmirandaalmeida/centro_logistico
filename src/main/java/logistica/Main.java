package logistica;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Sistema de Roteamento Logístico (versão offline)
 * -----------------------------------------------
 * - Centros de distribuição fixos.
 * - Usuário escolhe o centro inicial.
 * - Digita os destinos manualmente.
 * - Distâncias simuladas automaticamente.
 * - Calcula a melhor rota (Nearest Neighbor + 2-opt) com base em um grafo interno.
 */
public class Main {

    private static final boolean RETURN_TO_START = false;

    // Centros fixos (exemplo)
    private static final List<Center> CENTERS = List.of(
            new Center(0, "CD Centro"),
            new Center(1, "CD Norte"),
            new Center(2, "CD Leste"),
            new Center(3, "CD Oeste"),
            new Center(4, "CD Sul")
    );

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== CENTROS DE LOGÍSTICA ===");
        for (Center c : CENTERS)
            System.out.printf("%d) %s%n", c.id(), c.name());

        System.out.print("Escolha o centro inicial (id): ");
        int startId = Integer.parseInt(sc.nextLine().trim());
        Center start = CENTERS.stream()
                .filter(c -> c.id() == startId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Centro inválido."));

        System.out.print("Quantos destinos de entrega deseja inserir? ");
        int qtd = Integer.parseInt(sc.nextLine().trim());

        List<Point> pontos = new ArrayList<>();
        pontos.add(Point.fromCenter(start)); // ponto inicial

        for (int i = 1; i <= qtd; i++) {
            System.out.print("Digite o nome do destino " + i + ": ");
            String nome = sc.nextLine().trim();
            pontos.add(new Point(nome));
        }

        // --- Simular pesos de distâncias ---
        double[][] W = gerarMatrizDistancia(pontos);

        // --- Criar grafo e calcular rotas ---
        Graph g = new Graph(W);
        List<Integer> nn = Tsp.nearestNeighbor(g, 0, RETURN_TO_START);
        List<Integer> opt = Tsp.twoOpt(g, nn, RETURN_TO_START);

        System.out.println("\n=== Pontos considerados ===");
        for (int i = 0; i < pontos.size(); i++)
            System.out.printf("%d -> %s%n", i, pontos.get(i).name());

        System.out.println("\n=== Rota (Nearest Neighbor) ===");
        printRoute(pontos, g, nn);

        System.out.println("\n=== Rota após 2-opt ===");
        printRoute(pontos, g, opt);
    }

    /** Gera uma matriz de distâncias simulada */
    private static double[][] gerarMatrizDistancia(List<Point> pontos) {
        Random r = new Random();
        int n = pontos.size();
        double[][] W = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) W[i][j] = 0.0;
                else W[i][j] = 5 + r.nextDouble() * 45; // 5 a 50 km simulados
            }
        }
        return W;
    }

    /** Exibe a rota e custo total */
    private static void printRoute(List<Point> pts, Graph g, List<Integer> route) {
        for (int i = 0; i < route.size(); i++) {
            System.out.print(pts.get(route.get(i)).name());
            if (i < route.size() - 1) System.out.print(" -> ");
        }
        System.out.printf("%nDistância total: %.1f km%n", Tsp.cost(g, route));
    }
}
