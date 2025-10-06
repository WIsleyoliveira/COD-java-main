// Pacote do projeto, organizando as classes relacionadas aos tanques
package gradle;

// Importações necessárias para o funcionamento da classe
import java.util.ArrayList; // Embora não usado diretamente aqui, pode ser útil para listas de tanques
import java.util.Date; // Para registrar datas e horas, como entrada na arena

// Classe abstrata que representa um tanque genérico no jogo
// Serve como base para subclasses como Leve, Medio e Pesado
public abstract class Tanque {

    // Atributos privados para encapsulamento dos dados do tanque
    private int id; // Identificador único do tanque
    private String codinome; // Nome ou codinome do tanque
    private int blindagem; // Valor de blindagem, afeta resistência a danos
    private int velocidade; // Valor de velocidade, afeta mobilidade
    private int poderDeFogo; // Valor de poder de fogo, afeta dano causado
    private int vida = 100; // Pontos de vida do tanque, padrão 100
    private Date horaEntradaArena; // Data e hora de entrada na arena
    private String arma = "Metralhadora"; // Arma equipada, padrão Metralhadora

    // Construtor padrão sem parâmetros
    public Tanque() {
        // Inicializa um tanque vazio, atributos podem ser setados posteriormente
    }

    // Métodos getters e setters para acessar e modificar os atributos privados

    // Retorna o ID do tanque
    public int getId() {
        return id;
    }

    // Define o ID do tanque
    public void setId(int id) {
        this.id = id;
    }

    // Retorna o codinome do tanque
    public String getCodinome() {
        return codinome;
    }

    // Define o codinome do tanque
    public void setCodinome(String codinome) {
        this.codinome = codinome;
    }

    // Retorna o valor de blindagem
    public int getBlindagem() {
        return blindagem;
    }

    // Define o valor de blindagem
    public void setBlindagem(int blindagem) {
        this.blindagem = blindagem;
    }

    // Retorna o valor de velocidade
    public int getVelocidade() {
        return velocidade;
    }

    // Define o valor de velocidade
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    // Retorna o valor de poder de fogo
    public int getPoderDeFogo() {
        return poderDeFogo;
    }

    // Define o valor de poder de fogo
    public void setPoderDeFogo(int poderDeFogo) {
        this.poderDeFogo = poderDeFogo;
    }

    // Retorna a data e hora de entrada na arena
    public Date getHoraEntradaArena() {
        return horaEntradaArena;
    }

    // Define a data e hora de entrada na arena
    public void setHoraEntradaArena(Date horaEntradaArena) {
        this.horaEntradaArena = horaEntradaArena;
    }

    // Retorna os pontos de vida atuais
    public int getVida() {
        return vida;
    }

    // Define os pontos de vida
    public void setVida(int vida) {
        this.vida = vida;
    }

    // Retorna a arma equipada
    public String getArma() {
        return arma;
    }

    // Define a arma equipada
    public void setArma(String arma) {
        this.arma = arma;
    }

    // Método que retorna o poder de fogo como ataque (alias para getPoderDeFogo)
    public int getAtaque() {
        return poderDeFogo;
    }

    // Construtor parametrizado para inicializar um tanque com valores específicos
    public Tanque(int id, String codinome, int blindagem, int velocidade, int poderDeFogo, Date horaEntradaArena) {
        this.id = id;
        this.codinome = codinome;
        this.blindagem = blindagem;
        this.velocidade = velocidade;
        this.poderDeFogo = poderDeFogo;
        this.horaEntradaArena = horaEntradaArena;
    }

    // Método vazio para características de armas (a ser implementado nas subclasses)
    void armasCaract(String Metralhadora, String Missil, String Canhao, int escolha) {
        // Este método pode ser usado para definir características baseadas na arma escolhida
    }

    // Método que retorna o loadout baseado na escolha (simples retorno da escolha)
    int Armas(int escolha) {
        int loadout = escolha;
        return loadout;
    }

    // Método abstrato que deve ser implementado nas subclasses para definir características específicas
    public abstract void Caracterisrticas();

}
