package logistica;

import java.util.*;

/**
 * Simula o cálculo de uma matriz de rotas (sem usar API do Google).
 * Gera distâncias aleatórias entre os pontos.
 */
public class RoutesMatrixClient {
    private final Random random = new Random();

    public RoutesMatrixClient(String apiKeyIgnorado) {
        // apiKey não é mais usada, mas mantida por compatibilidade
    }

    public double[][] computeMinutesMatrix(List<Point> points) {
        int n = points.size();
        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) matrix[i][j] = 0;
                else matrix[i][j] = gerarDistanciaSimulada(i, j);
            }
        }
        return matrix;
    }

    private double gerarDistanciaSimulada(int i, int j) {
        // gera tempo médio de rota (em minutos) simulando distância aleatória
        return 5 + random.nextDouble() * 40; // entre 5 e 45 minutos
    }
}
