import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Hotel hotel = new Hotel();
    static RotaLimpezaHotel rota = new RotaLimpezaHotel(hotel);

    public static void main(String[] args) {
        menuPrincipal();
    }

    static void menuPrincipal() {
        while (true) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Escolher andar");
            System.out.println("2 - Explicar lógica da rota");
            System.out.println("3 - Encerrar");
            System.out.print("Escolha uma opção: ");
            int opc = sc.nextInt();

            switch (opc) {
                case 1 -> escolherAndar();
                case 2 -> explicarLogica();
                case 3 -> {
                    System.out.println("Encerrando programa...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    static void escolherAndar() {
        System.out.print("\nDigite o número do andar (1 a " + Hotel.ANDARES + "): ");
        int andar = sc.nextInt();

        if (andar < 1 || andar > Hotel.ANDARES) {
            System.out.println("Andar inválido!");
            return;
        }

        while (true) {
            System.out.println("\n===== ANDAR " + andar + " =====");
            System.out.println("1 - Mostrar situação dos quartos");
            System.out.println("2 - Mostrar rota de limpeza");
            System.out.println("3 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int opc = sc.nextInt();

            switch (opc) {
                case 1 -> mostrarSituacaoQuartos(andar);
                case 2 -> mostrarRotaLimpeza(andar);
                case 3 -> { return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    static void mostrarSituacaoQuartos(int andar) {
        System.out.println("\n--- Situação dos Quartos do Andar " + andar + " ---");
        for (Corredor c : hotel.getCorredoresDoAndar(andar)) {
            for (Quarto q : c.getQuartos()) {
                System.out.println("Quarto " + q.getNumero() + " (Corredor " + c.getNumero() + ") - " +
                        (q.isOcupado() ? "OCUPADO" : "LIVRE") + ", tam=" + String.format("%.1f", q.getTamanho()) + "m²");
            }
        }
    }

    static void mostrarRotaLimpeza(int andar) {
        List<Quarto> livres = rota.gerarRota(andar);
        if (livres.isEmpty()) {
            System.out.println("Nenhum quarto livre para limpeza neste andar!");
            return;
        }

        System.out.println("\n--- Rota de Limpeza - Andar " + andar + " ---");
        double distanciaTotal = 0.0;

        for (int i = 0; i < livres.size(); i++) {
            Quarto atual = livres.get(i);
            System.out.printf("Quarto %d (Andar %d, Corredor %d, tam=%.1fm², dist=%dm, LIVRE)\n",
                    atual.getNumero(), atual.getAndar(), atual.getCorredor(),
                    atual.getTamanho(), atual.getDistanciaAoElevador());

            if (i < livres.size() - 1) {
                Quarto proximo = livres.get(i + 1);
                double dist = Math.abs(proximo.getDistanciaAoElevador() - atual.getDistanciaAoElevador()) * 2.5;
                distanciaTotal += dist;
                System.out.printf("→ Distância até próximo quarto: %.1f metros\n", dist);
            }
        }

        System.out.printf("\nDistância total percorrida: %.1f metros\n", distanciaTotal);

        System.out.println("\n1 - Voltar ao menu principal");
        System.out.println("2 - Encerrar programa");
        System.out.print("Escolha: ");
        int opc = sc.nextInt();
        if (opc == 2) {
            System.out.println("Encerrando...");
            System.exit(0);
        }
    }

    static void explicarLogica() {
        System.out.println("\n--- Lógica da Rota ---");
        System.out.println("""
        O hotel é modelado como um grafo:
        - Cada quarto é um vértice (nó).
        - As ligações entre quartos são arestas (corredores).
        - Cada quarto tem um número único: andar * 100 + (corredor - 1) * 4 + pos + 1.

        A rota de limpeza percorre apenas os quartos LIVRES
        em ordem crescente de distância ao elevador,
        mostrando o deslocamento aproximado em metros entre cada um.
        """);
    }
}

