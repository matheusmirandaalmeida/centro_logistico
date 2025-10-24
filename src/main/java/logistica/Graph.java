package logistica;

public class Graph {
    private final double[][] w;
    public Graph(double[][] w) { this.w = w; }
    public int size() { return w.length; }
    public double cost(int i, int j) { return w[i][j];
    }
}
