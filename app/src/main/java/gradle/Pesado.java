// Declara o pacote do projeto
package gradle;

// Importa a classe Duration para durações
import java.time.Duration;
// Importa a classe Date para datas
import java.util.Date;

// Define a classe Pesado que herda de Tanque
public class Pesado extends Tanque {

    // Declara atributo nome como inteiro
    int nome;
    // Declara atributo danoBase como inteiro
    int danoBase;
    // Declara atributo alcanceEficaz como double
    double alcanceEficaz;
    // Declara atributo recarga como Duration
    Duration recarga;

    // Define o construtor parametrizado da classe Pesado
    public Pesado(int id, String codinome, int blindagem, int velocidade, int poderDeFogo, Date horaEntradaArena) {
        // Chama o construtor da classe pai Tanque
        super(id, codinome, blindagem, velocidade, poderDeFogo, horaEntradaArena);
    }

    // Define o construtor padrão da classe Pesado
    public Pesado() {
        // Chama o construtor padrão da classe pai Tanque
        super();
    }

    // Sobrescreve o método armasCaract da classe pai
    @Override
    void armasCaract(String Metralhadora, String Missil, String Canhao, int escolha) {
        // Chama o método armasCaract da classe pai
        super.armasCaract(Metralhadora, Missil, Canhao, escolha);

        // Verifica se a escolha é 1 (Metralhadora)
        if (escolha == 1) {
            // Multiplica danoBase por 3
            danoBase *= 3;
            // Define alcanceEficaz como 6
            alcanceEficaz = 6;
            // Define recarga como 4 segundos
            recarga = Duration.ofSeconds(4);
        // Verifica se a escolha é 2 (Míssil)
        } else if (escolha == 2) {
            // Multiplica danoBase por 10
            danoBase *= 10;
            // Define alcanceEficaz como 8
            alcanceEficaz = 8;
            // Define recarga como 12 segundos
            recarga = Duration.ofSeconds(12);
        // Verifica se a escolha é 3 (Canhão)
        } else if (escolha == 3) {
            // Multiplica danoBase por 15
            danoBase *= 15;
            // Define alcanceEficaz como 25
            alcanceEficaz = 25;
            // Define recarga como 18 segundos
            recarga = Duration.ofSeconds(18);
        }
    }

    // Implementa o método Caracterisrticas
    @Override
    public void Caracterisrticas() {
        // Define danoBase como 20
        danoBase = 20;
    }
}
