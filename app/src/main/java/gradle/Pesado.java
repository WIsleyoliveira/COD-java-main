// Pacote do projeto
package gradle;

// Importações
import java.time.Duration; // Para durações
import java.util.Date; // Para datas

// Classe Pesado, subclasse de Tanque, representa tanques pesados com alta blindagem e poder de fogo
public class Pesado extends Tanque {

    // Atributos específicos
    int nome; // Identificador
    int danoBase; // Dano base alto
    double alcanceEficaz; // Alcance efetivo
    Duration recarga; // Recarga longa devido ao peso

    // Construtor parametrizado
    public Pesado(int id, String codinome, int blindagem, int velocidade, int poderDeFogo, Date horaEntradaArena) {
        super(id, codinome, blindagem, velocidade, poderDeFogo, horaEntradaArena);
    }

    // Construtor padrão
    public Pesado() {
        super();
    }

    // Sobrescreve método para armas
    @Override
    void armasCaract(String Metralhadora, String Missil, String Canhao, int escolha) {
        super.armasCaract(Metralhadora, Missil, Canhao, escolha);

        // Características para armas pesadas
        if (escolha == 1) { // Metralhadora
            danoBase *= 3; // Menos dano relativo
            alcanceEficaz = 6; // Alcance curto
            recarga = Duration.ofSeconds(4); // Recarga rápida
        } else if (escolha == 2) { // Míssil
            danoBase *= 10; // Dano alto
            alcanceEficaz = 8; // Alcance médio
            recarga = Duration.ofSeconds(12); // Recarga longa
        } else if (escolha == 3) { // Canhão
            danoBase *= 15; // Dano muito alto
            alcanceEficaz = 25; // Alcance longo
            recarga = Duration.ofSeconds(18); // Recarga muito longa
        }
    }

    // Implementa características
    @Override
    public void Caracterisrticas() {
        danoBase = 20; // Dano base mais alto
    }
}
