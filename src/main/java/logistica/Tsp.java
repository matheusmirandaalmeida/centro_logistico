package logistica;

import java.util.ArrayList;
import java.util.List;

public class Tsp {
    public static List<Integer> nearestNeighbor(Graph g, int start, boolean returnToStart) {
        int n = g.size();
        boolean[] vis = new boolean[n];
        List<Integer> route = new ArrayList<>(n + (returnToStart ? 1 : 0));

        int cur = start;
        route.add(cur);
        vis[cur] = true;

        for (int k = 1; k < n; k++) {
            int nxt = -1;
            double best = Double.POSITIVE_INFINITY;
            for (int v = 0; v < n; v++) {
                if (!vis[v] && g.cost(cur, v) < best) {
                    best = g.cost(cur, v);
                    nxt = v;
                }
            }
            if (nxt == -1) break;
            route.add(nxt);
            vis[nxt] = true;
            cur = nxt;
        }
        if (returnToStart) route.add(start);
        return route;
    }

    public static List<Integer> twoOpt(Graph g, List<Integer> route, boolean cycle) {
        List<Integer> best = new ArrayList<>(route);
        boolean improved = true;
        while (improved) {
            improved = false;
            int size = best.size();
            for (int i = 1; i < size - 2; i++) {
                for (int k = i + 1; k < size - 1; k++) {
                    double delta = deltaSwap(g, best, i, k, cycle);
                    if (delta < -1e-9) {
                        reverse(best, i, k);
                        improved = true;
                    }
                }
            }
        }
        return best;
    }

    private static double deltaSwap(Graph g, List<Integer> r, int i, int k, boolean cycle) {
        int a = r.get(i - 1);
        int b = r.get(i);
        int c = r.get(k);
        Integer d = (k + 1 < r.size() ? r.get(k + 1) : (cycle ? r.get(0) : null));
        double before = g.cost(a, b) + (d != null ? g.cost(c, d) : 0.0);
        double after  = g.cost(a, c) + (d != null ? g.cost(b, d) : 0.0);
        return after - before;
    }

    private static void reverse(List<Integer> r, int i, int k) {
        while (i < k) {
            int tmp = r.get(i);
            r.set(i, r.get(k));
            r.set(k, tmp);
            i++; k--;
        }
    }

    public static double cost(Graph g, List<Integer> route) {
        double total = 0;
        for (int i = 0; i < route.size() - 1; i++)
            total += g.cost(route.get(i), route.get(i + 1));
        return total;
    }
}