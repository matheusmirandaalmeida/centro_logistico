import java.util.*;

public class Quarto {
    private int andar;
    private int corredor;
    private int numero;
    private double tamanho;
    private int distanciaAoElevador;
    private boolean ocupado;

    public Quarto(int andar, int corredor, int posNoCorredor, Random rnd) {
        this.andar = andar;
        this.corredor = corredor;
        this.numero = andar * 100 + (corredor - 1) * 4 + posNoCorredor + 1;
        this.tamanho = 10 + rnd.nextDouble() * 30;
        this.distanciaAoElevador = Math.abs(corredor - 1) * 2 + posNoCorredor;
        this.ocupado = rnd.nextDouble() < 0.20;
    }

    public int getNumero() {
        return numero;
    }

    public int getAndar() {
        return andar;
    }

    public int getCorredor() {
        return corredor;
    }

    public double getTamanho() {
        return tamanho;
    }

    public int getDistanciaAoElevador() {
        return distanciaAoElevador;
    }

    public boolean isOcupado() {
        return ocupado;
    }
}
