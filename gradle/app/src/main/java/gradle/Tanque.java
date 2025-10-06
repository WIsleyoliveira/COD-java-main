
package gradle;

import java.util.ArrayList;
import java.util.Date;

public class Tanque {

    private int id;
    private String codinome;
    private int blindagem;
    private int velocidade;
    private int poderDeFogo;
    private Date horaEntradaArena;
    
        

    public Tanque() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodinome() {
        return codinome;
    }

    public void setCodinome(String codinome) {
        this.codinome = codinome;
    }

    public int getBlindagem() {
        return blindagem;
    }

    public void setBlindagem(int blindagem) {
        this.blindagem = blindagem;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getPoderDeFogo() {
        return poderDeFogo;
    }

    public void setPoderDeFogo(int poderDeFogo) {
        this.poderDeFogo = poderDeFogo;
    }

    public Date getHoraEntradaArena() {
        return horaEntradaArena;
    }

    public void setHoraEntradaArena(Date horaEntradaArena) {
        this.horaEntradaArena = horaEntradaArena;
    }

    public Tanque(int id, String codinome, int blindagem, int velocidade, int poderDeFogo, Date horaEntradaArena) {
        this.id = id;
        this.codinome = codinome;
        this.blindagem = blindagem;
        this.velocidade = velocidade;
        this.poderDeFogo = poderDeFogo;
        this.horaEntradaArena = horaEntradaArena;
    }

    void armasCaract(String Metralhadora, String Missil, String Canhao, int escolha) {

    }

    int Armas(int escolha) {
        int loadout = escolha;
        return loadout;
    }

    public void Caracterisrticas() {
        // Implementação padrão vazia
    }

}
