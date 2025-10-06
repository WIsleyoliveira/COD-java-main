// Pacote do projeto, organizando as classes relacionadas aos tanques
package gradle;

// Importações necessárias para o funcionamento da classe
import java.time.Duration; // Para durações de recarga
import java.util.Date; // Para datas

// Classe Medio, subclasse de Tanque, representa tanques médios com equilíbrio entre atributos
public class Medio extends Tanque {

    // Atributos específicos para tanques médios
    int nome; // Identificador adicional
    int danoBase; // Dano base das armas
    double alcanceEficaz; // Alcance efetivo
    Duration recarga; // Tempo de recarga

    // Construtor completo com todos os parâmetros, incluindo os específicos
    public Medio(int id, String codinome, int blindagem, int velocidade, int poderDeFogo, Date horaEntradaArena,
            int nome, int danoBase, double alcanceEficaz, Duration recarga) {
        // Chama o construtor da classe pai
        super(id, codinome, blindagem, velocidade, poderDeFogo, horaEntradaArena);
        // Inicializa atributos específicos
        this.nome = nome;
        this.danoBase = danoBase;
        this.alcanceEficaz = alcanceEficaz;
        this.recarga = recarga;
    }

    // Construtor padrão
    public Medio() {
        super();
    }

    // Implementa o método abstrato para definir características
    @Override
    public void Caracterisrticas() {
        danoBase = 15; // Dano base maior que o leve
    }

    // Sobrescreve o método para características de armas
    @Override
    void armasCaract(String Metralhadora, String Missil, String Canhao, int escolha) {
        // TODO: Método gerado automaticamente, pode ser refinado
        super.armasCaract(Metralhadora, Missil, Canhao, escolha);

        // Define características baseadas na arma
        if (escolha == 1) { // Metralhadora
            danoBase *= 4; // Multiplica por 4
            alcanceEficaz = 5; // Alcance menor
            recarga = Duration.ofSeconds(5); // Recarga média
        } else if (escolha == 2) { // Míssil
            danoBase *= 8; // Multiplica por 8
            alcanceEficaz = 6; // Alcance um pouco maior
            recarga = Duration.ofSeconds(10); // Recarga mais longa
        } else if (escolha == 3) { // Canhão
            danoBase *= 12; // Mesmo multiplicador
            alcanceEficaz = 20; // Alcance alto
            recarga = Duration.ofSeconds(14); // Recarga longa
        }
    }

}
