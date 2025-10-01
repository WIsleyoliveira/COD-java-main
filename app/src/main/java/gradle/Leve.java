package gradle;

import java.time.Duration;
import java.util.Date;

public abstract class Leve extends Tanque {
    int nome;
    int danoBase;
    double alcanceEficaz;
    Duration recarga;

    public Leve(int id, String codinome, int blindagem, int velocidade, int poderDeFogo, Date horaEntradaArena) {
        super(id, codinome, blindagem, velocidade, poderDeFogo, horaEntradaArena);
    }

    public Leve() {
        super();
    }

    @Override
    void armasCaract(String Metralhadora, String Missil, String Canhao, int escolha) {

        super.armasCaract(Metralhadora, Missil, Canhao, escolha);

        if (escolha == 1) {
            danoBase *= 2;
            alcanceEficaz = 8;
            recarga = Duration.ofSeconds(2);
        } else if (escolha == 2) {
            danoBase *= 6;
            alcanceEficaz = 10;
            recarga = Duration.ofSeconds(8);
        } else if (escolha == 3) {
            danoBase *= 12;
            alcanceEficaz = 20;
            recarga = Duration.ofSeconds(14);
        }
    }

    @Override
    public void Caracterisrticas() {
        danoBase = 10;
    }

}
