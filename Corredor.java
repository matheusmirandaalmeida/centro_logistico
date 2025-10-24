import java.util.*;

public class Corredor {
    private int andar;
    private int numero;
    private List<Quarto> quartos = new ArrayList<>();

    public Corredor(int andar, int numero, Random rnd) {
        this.andar = andar;
        this.numero = numero;
        for (int i = 0; i < Hotel.QUARTOS_POR_CORREDOR; i++) {
            quartos.add(new Quarto(andar, numero, i, rnd));
        }
    }

    public int getAndar() {
        return andar;
    }

    public int getNumero() {
        return numero;
    }

    public List<Quarto> getQuartos() {
        return quartos;
    }
}
