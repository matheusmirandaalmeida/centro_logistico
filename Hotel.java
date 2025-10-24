import java.util.*;

public class Hotel {
    public static final int ANDARES = 3;
    public static final int CORREDORES_POR_ANDAR = 5;
    public static final int QUARTOS_POR_ANDAR = 20;
    public static final int QUARTOS_POR_CORREDOR = QUARTOS_POR_ANDAR / CORREDORES_POR_ANDAR;

    private List<Corredor> corredores = new ArrayList<>();

    public Hotel() {
        Random rnd = new Random();
        for (int andar = 1; andar <= ANDARES; andar++) {
            for (int corredor = 1; corredor <= CORREDORES_POR_ANDAR; corredor++) {
                corredores.add(new Corredor(andar, corredor, rnd));
            }
        }
    }

    public List<Corredor> getCorredoresDoAndar(int andar) {
        List<Corredor> lista = new ArrayList<>();
        for (Corredor c : corredores) {
            if (c.getAndar() == andar) lista.add(c);
        }
        return lista;
    }

    public List<Corredor> getCorredores() {
        return corredores;
    }
}
