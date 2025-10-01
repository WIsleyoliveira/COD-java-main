package gradle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Modos {
    Scanner sc = new Scanner(System.in);
    Date dataHora;
    double duracao;
    int arena;
    LocalDateTime agora = LocalDateTime.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String formatada = agora.format(formato);
    ArrayList<Tanque> tanquesAliado;
    ArrayList<Tanque> tanqueInimigo;
    ArrayList<String> partidasAgendadas = new ArrayList<>();

    public Modos(Date dataHora, double duracao, int arena, ArrayList<Tanque> tanquesAliado, ArrayList<Tanque> tanqueInimigo) {
        this.dataHora = dataHora;
        this.duracao = duracao;
        this.arena = arena;
        this.tanquesAliado = tanquesAliado;
        this.tanqueInimigo = tanqueInimigo;
    }

    void modoTreino() {
        if (tanquesAliado.size() == 0 || tanqueInimigo.size() == 0) {
            System.out.println("Você deve criar tanques aliados e inimigos para continuar!");
            return;
        }

        System.out.println("Escolha um mapa: ");
        System.out.println("(1) Deserto");
        System.out.println("(2) Urbano");
        System.out.println("(3) Campo Aberto");
        arena = sc.nextInt();
        
        String nomeArena = "";

        Tanque tanqueAliado = tanquesAliado.get(0);
        Tanque tanqueInimigoObj = tanqueInimigo.get(0);

        switch (arena) {
            case 1:
                System.out.println("Mapa selecionado: Deserto");
                System.out.println("Horário inicial da partida: " + formatada);

                tanqueAliado.setVelocidade((int)(tanqueAliado.getVelocidade() * 0.60));
                tanqueInimigoObj.setVelocidade((int)(tanqueInimigoObj.getVelocidade() * 0.60));
                break;

            case 2:
                System.out.println("Mapa selecionado: Urbano");
                System.out.println("Horário inicial da partida: " + formatada);

                tanqueAliado.setBlindagem((int)(tanqueAliado.getBlindagem() * 2));
                tanqueInimigoObj.setBlindagem((int)(tanqueInimigoObj.getBlindagem() * 2));
                break;

            case 3:
                System.out.println("Mapa selecionado: Campo Aberto");
                System.out.println("Horário inicial da partida: " + formatada);
                
                tanqueAliado.setPoderDeFogo((int)(tanqueAliado.getPoderDeFogo() * 2));
                tanqueInimigoObj.setPoderDeFogo((int)(tanqueInimigoObj.getPoderDeFogo() * 2));
                break;
                
            default:
                System.out.println("Opção inválida");
                break;
        }

        System.out.println("#=# TANQUE ALIADO #=#");
        System.out.println("|Codinome: " + tanqueAliado.getCodinome() + 
                        "|Blindagem: " + tanqueAliado.getBlindagem() + 
                        "|Velocidade: " + tanqueAliado.getVelocidade() + "|");

        System.out.println("#=# TANQUE INIMIGO #=#");
        System.out.println("|Codinome: " + tanqueInimigoObj.getCodinome() + 
            "|Blindagem: " + tanqueInimigoObj.getBlindagem() + 
            "|Velocidade: " + tanqueInimigoObj.getVelocidade() + "|");
        
        partidasAgendadas.add("Treino - " + nomeArena + " - " + formatada);
        System.out.println("Partida de treino agendada!");

        //código partida
    }

    void modoPvP() {
        if (tanquesAliado.size() == 0 || tanqueInimigo.size() == 0) {
            System.out.println("Você deve criar um Tanque para continuar!");
            return;
        }

        System.out.println("Agendamento de Partida PvP");
        System.out.print("Data (dd/MM/yyyy): ");
        String dataStr = sc.next();
        System.out.print("Hora (HH:mm): ");
        String horaStr = sc.next();
        System.out.print("Duração (horas): ");
        double duracaoPvP = sc.nextDouble();
        
        System.out.println("Escolha um mapa: ");
        System.out.println("(1) Deserto");
        System.out.println("(2) Urbano");
        System.out.println("(3) Campo Aberto");
        int arenaPvP = sc.nextInt();
        
        String nomeArena = "";
        
        partidasAgendadas.add("PvP - " + nomeArena + " - " + dataStr + " " + horaStr);
        System.out.println("Partida PvP agendada!");

        //código partida
    }

    void modoTvT() {
        System.out.println("Agendamento de Partida Time vs Time");
        System.out.print("Quantos tanques por time? ");
        int tamanhoTime = sc.nextInt();
        
        System.out.print("Data (dd/MM/yyyy): ");
        String dataStr = sc.next();
        System.out.print("Hora (HH:mm): ");
        String horaStr = sc.next();
        System.out.print("Duração (horas): ");
        double duracaoTvT = sc.nextDouble();
        
        System.out.println("Escolha um mapa: ");
        System.out.println("(1) Deserto");
        System.out.println("(2) Urbano");
        System.out.println("(3) Campo Aberto");
        int arenaTvT = sc.nextInt();
        
        String nomeArena = "";
        
        partidasAgendadas.add("TvT " + tamanhoTime + "v" + tamanhoTime + " - " + nomeArena + " - " + dataStr + " " + horaStr);
        System.out.println("Partida TvT agendada!");

        //código partida
    }

    public void listarPartidas() {
        System.out.println("PARTIDAS AGENDADAS:");
        if (partidasAgendadas.isEmpty()) {
            System.out.println("Nenhuma partida agendada.");
        } else {
            for (String partida : partidasAgendadas) {
                System.out.println(" - " + partida);
            }
        }
    }
}