// Pacote do projeto, organizando as classes relacionadas aos tanques
package gradle;

// Importações necessárias para o funcionamento da classe
import java.time.Duration; // Para representar durações de tempo, como recarga de armas
import java.util.Date; // Para datas, herdado da classe pai

// Classe Leve, subclasse de Tanque, representa tanques leves com características específicas
// Tanques leves têm alta velocidade, baixo blindagem e poder de fogo médio
public class Leve extends Tanque {

    // Atributos específicos para tanques leves
    int nome; // Possivelmente um identificador adicional (não usado atualmente)
    int danoBase; // Dano base causado pelas armas
    double alcanceEficaz; // Alcance efetivo da arma em unidades
    Duration recarga; // Tempo de recarga da arma

    // Construtor parametrizado que chama o construtor da classe pai
    public Leve(int id, String codinome, int blindagem, int velocidade, int poderDeFogo, Date horaEntradaArena) {
        super(id, codinome, blindagem, velocidade, poderDeFogo, horaEntradaArena);
    }

    // Construtor padrão que chama o construtor padrão da classe pai
    public Leve() {
        super();
    }

    // Sobrescreve o método armasCaract da classe pai para definir características baseadas na arma escolhida
    @Override
    void armasCaract(String Metralhadora, String Missil, String Canhao, int escolha) {
        // Chama o método da classe pai primeiro
        super.armasCaract(Metralhadora, Missil, Canhao, escolha);

        // Define características baseadas na escolha da arma
        if (escolha == 1) { // Metralhadora
            danoBase *= 2; // Dano dobrado
            alcanceEficaz = 8; // Alcance de 8 unidades
            recarga = Duration.ofSeconds(2); // Recarga de 2 segundos
        } else if (escolha == 2) { // Míssil
            danoBase *= 6; // Dano sextuplicado
            alcanceEficaz = 10; // Alcance de 10 unidades
            recarga = Duration.ofSeconds(8); // Recarga de 8 segundos
        } else if (escolha == 3) { // Canhão
            danoBase *= 12; // Dano doze vezes maior
            alcanceEficaz = 20; // Alcance de 20 unidades
            recarga = Duration.ofSeconds(14); // Recarga de 14 segundos
        }
    }

    // Implementa o método abstrato Caracterisrticas da classe pai
    @Override
    public void Caracterisrticas() {
        danoBase = 10; // Define o dano base padrão para tanques leves
    }

}
