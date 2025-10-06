// Declara o pacote do projeto
package gradle;

// Importa a classe Duration para durações
import java.time.Duration;
// Importa a classe Date para datas
import java.util.Date;

// Define a classe Medio que herda de Tanque
public class Medio extends Tanque {

    // Declara atributo nome como inteiro
    int nome;
    // Declara atributo danoBase como inteiro
    int danoBase;
    // Declara atributo alcanceEficaz como double
    double alcanceEficaz;
    // Declara atributo recarga como Duration
    Duration recarga;

    // Define o construtor completo da classe Medio
    public Medio(int id, String codinome, int blindagem, int velocidade, int poderDeFogo, Date horaEntradaArena,
            int nome, int danoBase, double alcanceEficaz, Duration recarga) {
        // Chama o construtor da classe pai Tanque
        super(id, codinome, blindagem, velocidade, poderDeFogo, horaEntradaArena);
        // Inicializa o atributo nome
        this.nome = nome;
        // Inicializa o atributo danoBase
        this.danoBase = danoBase;
        // Inicializa o atributo alcanceEficaz
        this.alcanceEficaz = alcanceEficaz;
        // Inicializa o atributo recarga
        this.recarga = recarga;
    }

    // Define o construtor padrão da classe Medio
    public Medio() {
        // Chama o construtor padrão da classe pai
        super();
    }

    // Implementa o método Caracterisrticas
    @Override
    public void Caracterisrticas() {
        // Define danoBase como 15
        danoBase = 15;
    }

    // Sobrescreve o método armasCaract
    @Override
    void armasCaract(String Metralhadora, String Missil, String Canhao, int escolha) {
        // Chama o método da classe pai
        super.armasCaract(Metralhadora, Missil, Canhao, escolha);

        // Verifica se a escolha é 1 (Metralhadora)
        if (escolha == 1) {
            // Multiplica danoBase por 4
            danoBase *= 4;
            // Define alcanceEficaz como 5
            alcanceEficaz = 5;
            // Define recarga como 5 segundos
            recarga = Duration.ofSeconds(5);
        // Verifica se a escolha é 2 (Míssil)
        } else if (escolha == 2) {
            // Multiplica danoBase por 8
            danoBase *= 8;
            // Define alcanceEficaz como 6
            alcanceEficaz = 6;
            // Define recarga como 10 segundos
            recarga = Duration.ofSeconds(10);
        // Verifica se a escolha é 3 (Canhão)
        } else if (escolha == 3) {
            // Multiplica danoBase por 12
            danoBase *= 12;
            // Define alcanceEficaz como 20
            alcanceEficaz = 20;
            // Define recarga como 14 segundos
            recarga = Duration.ofSeconds(14);
        }
    }

}
