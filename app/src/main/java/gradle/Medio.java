package gradle;

import java.time.Duration;
import java.util.Date;

public abstract class Medio extends Tanque {
    int nome;
    int danoBase;
    double alcanceEficaz;
    Duration recarga;

    public Medio(int id, String codinome, int blindagem, int velocidade, int poderDeFogo, Date horaEntradaArena,
            int nome, int danoBase, double alcanceEficaz, Duration recarga) {
        super(id, codinome, blindagem, velocidade, poderDeFogo, horaEntradaArena);
        this.nome = nome;
        this.danoBase = danoBase;
        this.alcanceEficaz = alcanceEficaz;
        this.recarga = recarga;
    }

    public Medio() {
        super();
    }

    @Override
    public void Caracterisrticas() {
        danoBase = 15;

    }

    @Override
    void armasCaract(String Metralhadora, String Missil, String Canhao, int escolha) {
        // TODO Auto-generated method stub
        super.armasCaract(Metralhadora, Missil, Canhao, escolha);

        if (escolha == 1) {
            danoBase *= 4;
            alcanceEficaz = 5;
            recarga = Duration.ofSeconds(5);
        } else if (escolha == 2) {
            danoBase *= 8;
            alcanceEficaz = 6;
            recarga = Duration.ofSeconds(10);
        } else if (escolha == 3) {
            danoBase *= 12;
            alcanceEficaz = 20;
            recarga = Duration.ofSeconds(14);
        }

    }

}
