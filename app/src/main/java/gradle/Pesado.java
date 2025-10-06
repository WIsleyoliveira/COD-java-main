package gradle;

import java.time.Duration;
import java.util.Date;

public class Pesado extends Tanque {
    int nome;
    int danoBase;
    double alcanceEficaz;
    Duration recarga;

    public Pesado(int id, String codinome, int blindagem, int velocidade, int poderDeFogo, Date horaEntradaArena) {
        super(id, codinome, blindagem, velocidade, poderDeFogo, horaEntradaArena);
    }

    public Pesado() {
        super();
    }

    @Override
    void armasCaract(String Metralhadora, String Missil, String Canhao, int escolha) {
        super.armasCaract(Metralhadora, Missil, Canhao, escolha);

        if (escolha == 1) {
            danoBase *= 3;
            alcanceEficaz = 6;
            recarga = Duration.ofSeconds(4);
        } else if (escolha == 2) {
            danoBase *= 10;
            alcanceEficaz = 8;
            recarga = Duration.ofSeconds(12);
        } else if (escolha == 3) {
            danoBase *= 15;
            alcanceEficaz = 25;
            recarga = Duration.ofSeconds(18);
        }
    }

    @Override
    public void Caracterisrticas() {
        danoBase = 20;
    }
}
