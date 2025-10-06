package gradle;

import java.time.Duration;
import java.util.Date;

public final class Pesado extends Tanque {
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
    public void Caracterisrticas() {
        danoBase = 20;
        // Definindo atributos conforme especificação do usuário
        setVelocidade(40);  // menor velocidade
        setPoderDeFogo(40); // mesmo ataque que Medio
        setBlindagem(60);   // maior blindagem
    }

    @Override
    void armasCaract(String Metralhadora, String Missil, String Canhao, int escolha) {
        super.armasCaract(Metralhadora, Missil, Canhao, escolha);

        if (escolha == 1) {
            danoBase *= 5;
            alcanceEficaz = 4;
            recarga = Duration.ofSeconds(6);
        } else if (escolha == 2) {
            danoBase *= 10;
            alcanceEficaz = 5;
            recarga = Duration.ofSeconds(12);
        } else if (escolha == 3) {
            danoBase *= 15;
            alcanceEficaz = 15;
            recarga = Duration.ofSeconds(16);
        }
    }
}
