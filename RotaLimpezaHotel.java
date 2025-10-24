import java.util.*;

public class RotaLimpezaHotel {
    private Hotel hotel;

    public RotaLimpezaHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<Quarto> gerarRota(int andar) {
        List<Quarto> rota = new ArrayList<>();
        for (Corredor c : hotel.getCorredoresDoAndar(andar)) {
            for (Quarto q : c.getQuartos()) {
                if (!q.isOcupado()) rota.add(q);
            }
        }
        rota.sort(Comparator.comparingInt(Quarto::getDistanciaAoElevador));
        return rota;
    }
}
