// Declara o pacote do projeto
package gradle;

// Importa a classe Duration para representar durações de tempo
import java.time.Duration;
// Importa a classe Date para trabalhar com datas
import java.util.Date;

// Define a classe Leve que herda da classe Tanque
public class Leve extends Tanque {

    // Declara atributo nome como inteiro
    int nome;
    // Declara atributo danoBase como inteiro
    int danoBase;
    // Declara atributo alcanceEficaz como double
    double alcanceEficaz;
    // Declara atributo recarga como Duration
    Duration recarga;

    // Define o construtor parametrizado da classe Leve
    public Leve(int id, String codinome, int blindagem, int velocidade, int poderDeFogo, Date horaEntradaArena) {
        // Chama o construtor da classe pai Tanque com os parâmetros fornecidos
        super(id, codinome, blindagem, velocidade, poderDeFogo, horaEntradaArena);
    }

    // Define o construtor padrão da classe Leve
    public Leve() {
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
            // Multiplica o danoBase por 2
            danoBase *= 2;
            // Define o alcanceEficaz como 8
            alcanceEficaz = 8;
            // Define a recarga como 2 segundos
            recarga = Duration.ofSeconds(2);
        // Verifica se a escolha é 2 (Míssil)
        } else if (escolha == 2) {
            // Multiplica o danoBase por 6
            danoBase *= 6;
            // Define o alcanceEficaz como 10
            alcanceEficaz = 10;
            // Define a recarga como 8 segundos
            recarga = Duration.ofSeconds(8);
        // Verifica se a escolha é 3 (Canhão)
        } else if (escolha == 3) {
            // Multiplica o danoBase por 12
            danoBase *= 12;
            // Define o alcanceEficaz como 20
            alcanceEficaz = 20;
            // Define a recarga como 14 segundos
            recarga = Duration.ofSeconds(14);
        }
    }

    // Implementa o método abstrato Caracterisrticas da classe pai
    @Override
    public void Caracterisrticas() {
        // Define o danoBase como 10
        danoBase = 10;
    }

}
