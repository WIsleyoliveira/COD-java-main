// Declara o pacote do projeto
package gradle;

// Importa a classe ArrayList para listas
import java.util.ArrayList;
// Importa a classe Date para datas
import java.util.Date;

// Define a classe abstrata Tanque
public abstract class Tanque {

    // Declara atributo id como privado inteiro
    private int id;
    // Declara atributo codinome como privado String
    private String codinome;
    // Declara atributo blindagem como privado inteiro
    private int blindagem;
    // Declara atributo velocidade como privado inteiro
    private int velocidade;
    // Declara atributo poderDeFogo como privado inteiro
    private int poderDeFogo;
    // Declara atributo vida como privado inteiro inicializado com 100
    private int vida = 100;
    // Declara atributo horaEntradaArena como privado Date
    private Date horaEntradaArena;
    // Declara atributo arma como privado String inicializado com "Metralhadora"
    private String arma = "Metralhadora";

    // Define o construtor padrão da classe Tanque
    public Tanque() {
        // Corpo vazio do construtor
    }

    // Define o método getId que retorna o id
    public int getId() {
        // Retorna o valor do atributo id
        return id;
    }

    // Define o método setId que define o id
    public void setId(int id) {
        // Atribui o valor passado ao atributo id
        this.id = id;
    }

    // Define o método getCodinome que retorna o codinome
    public String getCodinome() {
        // Retorna o valor do atributo codinome
        return codinome;
    }

    // Define o método setCodinome que define o codinome
    public void setCodinome(String codinome) {
        // Atribui o valor passado ao atributo codinome
        this.codinome = codinome;
    }

    // Define o método getBlindagem que retorna a blindagem
    public int getBlindagem() {
        // Retorna o valor do atributo blindagem
        return blindagem;
    }

    // Define o método setBlindagem que define a blindagem
    public void setBlindagem(int blindagem) {
        // Atribui o valor passado ao atributo blindagem
        this.blindagem = blindagem;
    }

    // Define o método getVelocidade que retorna a velocidade
    public int getVelocidade() {
        // Retorna o valor do atributo velocidade
        return velocidade;
    }

    // Define o método setVelocidade que define a velocidade
    public void setVelocidade(int velocidade) {
        // Atribui o valor passado ao atributo velocidade
        this.velocidade = velocidade;
    }

    // Define o método getPoderDeFogo que retorna o poderDeFogo
    public int getPoderDeFogo() {
        // Retorna o valor do atributo poderDeFogo
        return poderDeFogo;
    }

    // Define o método setPoderDeFogo que define o poderDeFogo
    public void setPoderDeFogo(int poderDeFogo) {
        // Atribui o valor passado ao atributo poderDeFogo
        this.poderDeFogo = poderDeFogo;
    }

    // Define o método getHoraEntradaArena que retorna a horaEntradaArena
    public Date getHoraEntradaArena() {
        // Retorna o valor do atributo horaEntradaArena
        return horaEntradaArena;
    }

    // Define o método setHoraEntradaArena que define a horaEntradaArena
    public void setHoraEntradaArena(Date horaEntradaArena) {
        // Atribui o valor passado ao atributo horaEntradaArena
        this.horaEntradaArena = horaEntradaArena;
    }

    // Define o método getVida que retorna a vida
    public int getVida() {
        // Retorna o valor do atributo vida
        return vida;
    }

    // Define o método setVida que define a vida
    public void setVida(int vida) {
        // Atribui o valor passado ao atributo vida
        this.vida = vida;
    }

    // Define o método getArma que retorna a arma
    public String getArma() {
        // Retorna o valor do atributo arma
        return arma;
    }

    // Define o método setArma que define a arma
    public void setArma(String arma) {
        // Atribui o valor passado ao atributo arma
        this.arma = arma;
    }

    // Define o método getAtaque que retorna o poderDeFogo
    public int getAtaque() {
        // Retorna o valor do atributo poderDeFogo
        return poderDeFogo;
    }

    // Define o construtor parametrizado da classe Tanque
    public Tanque(int id, String codinome, int blindagem, int velocidade, int poderDeFogo, Date horaEntradaArena) {
        // Atribui o parâmetro id ao atributo id
        this.id = id;
        // Atribui o parâmetro codinome ao atributo codinome
        this.codinome = codinome;
        // Atribui o parâmetro blindagem ao atributo blindagem
        this.blindagem = blindagem;
        // Atribui o parâmetro velocidade ao atributo velocidade
        this.velocidade = velocidade;
        // Atribui o parâmetro poderDeFogo ao atributo poderDeFogo
        this.poderDeFogo = poderDeFogo;
        // Atribui o parâmetro horaEntradaArena ao atributo horaEntradaArena
        this.horaEntradaArena = horaEntradaArena;
    }

    // Define o método armasCaract
    void armasCaract(String Metralhadora, String Missil, String Canhao, int escolha) {
        // Corpo vazio do método
    }

    // Define o método Armas que retorna um inteiro
    int Armas(int escolha) {
        // Declara variável loadout e atribui o valor de escolha
        int loadout = escolha;
        // Retorna o valor de loadout
        return loadout;
    }

    // Declara o método abstrato Caracterisrticas
    public abstract void Caracterisrticas();

}
