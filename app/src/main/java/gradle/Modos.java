// Pacote do projeto
package gradle;

// Importações para manipulação de datas, listas e entrada do usuário
import java.time.LocalDateTime; // Para obter data e hora atual
import java.time.format.DateTimeFormatter; // Para formatar datas
import java.util.ArrayList; // Para listas de tanques e partidas
import java.util.Date; // Para datas (usado no construtor)
import java.util.Scanner; // Para entrada do usuário

// Classe Modos, responsável por gerenciar diferentes modos de jogo: Treino, PvP, TvT
public class Modos {

    // Atributos da classe
    Scanner sc = new Scanner(System.in); // Scanner para entrada do usuário
    Date dataHora; // Data e hora da partida
    double duracao; // Duração da partida em horas
    int arena; // ID da arena/mapa selecionada
    LocalDateTime agora = LocalDateTime.now(); // Data e hora atual
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // Formato para exibir data/hora
    String formatada = agora.format(formato); // String formatada da data/hora atual
    ArrayList<Tanque> tanquesAliado; // Lista de tanques aliados
    ArrayList<Tanque> tanqueInimigo; // Lista de tanques inimigos
    ArrayList<String> partidasAgendadas = new ArrayList<>(); // Lista de partidas agendadas

    // Construtor da classe Modos
    public Modos(Date dataHora, double duracao, int arena, ArrayList<Tanque> tanquesAliado, ArrayList<Tanque> tanqueInimigo) {
        this.dataHora = dataHora;
        this.duracao = duracao;
        this.arena = arena;
        this.tanquesAliado = tanquesAliado;
        this.tanqueInimigo = tanqueInimigo;
    }

    // Método para o modo Treino: simula uma partida rápida com modificadores de mapa
    void modoTreino() {
        // Verifica se há tanques criados
        if (tanquesAliado.size() == 0 || tanqueInimigo.size() == 0) {
            System.out.println("Você deve criar tanques aliados e inimigos para continuar!");
            return;
        }

        // Seleção do mapa
        System.out.println("Escolha um mapa: ");
        System.out.println("(1) Deserto");
        System.out.println("(2) Urbano");
        System.out.println("(3) Campo Aberto");
        arena = sc.nextInt();

        String nomeArena = ""; // Nome do mapa (não usado atualmente)

        // Obtém o primeiro tanque de cada lado
        Tanque tanqueAliado = tanquesAliado.get(0);
        Tanque tanqueInimigoObj = tanqueInimigo.get(0);

        // Aplica modificadores baseados no mapa selecionado
        switch (arena) {
            case 1: // Deserto: reduz velocidade
                System.out.println("Mapa selecionado: Deserto");
                System.out.println("Horário inicial da partida: " + formatada);
                tanqueAliado.setVelocidade((int)(tanqueAliado.getVelocidade() * 0.60));
                tanqueInimigoObj.setVelocidade((int)(tanqueInimigoObj.getVelocidade() * 0.60));
                break;
            case 2: // Urbano: aumenta blindagem
                System.out.println("Mapa selecionado: Urbano");
                System.out.println("Horário inicial da partida: " + formatada);
                tanqueAliado.setBlindagem((int)(tanqueAliado.getBlindagem() * 2));
                tanqueInimigoObj.setBlindagem((int)(tanqueInimigoObj.getBlindagem() * 2));
                break;
            case 3: // Campo Aberto: aumenta poder de fogo
                System.out.println("Mapa selecionado: Campo Aberto");
                System.out.println("Horário inicial da partida: " + formatada);
                tanqueAliado.setPoderDeFogo((int)(tanqueAliado.getPoderDeFogo() * 2));
                tanqueInimigoObj.setPoderDeFogo((int)(tanqueInimigoObj.getPoderDeFogo() * 2));
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }

        // Exibe informações dos tanques após modificadores
        System.out.println("#=# TANQUE ALIADO #=#");
        System.out.println("|Codinome: " + tanqueAliado.getCodinome() +
                        "|Blindagem: " + tanqueAliado.getBlindagem() +
                        "|Velocidade: " + tanqueAliado.getVelocidade() + "|");

        System.out.println("#=# TANQUE INIMIGO #=#");
        System.out.println("|Codinome: " + tanqueInimigoObj.getCodinome() +
            "|Blindagem: " + tanqueInimigoObj.getBlindagem() +
            "|Velocidade: " + tanqueInimigoObj.getVelocidade() + "|");

        // Agenda a partida
        partidasAgendadas.add("Treino - " + nomeArena + " - " + formatada);
        System.out.println("Partida de treino agendada!");

        // Código da partida (comentado, a ser implementado)
    }

    // Método para o modo PvP: agendamento de partida jogador vs jogador
    void modoPvP() {
        // Verifica tanques
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

        String nomeArena = ""; // Nome não definido

        // Agenda a partida PvP
        partidasAgendadas.add("PvP - " + nomeArena + " - " + dataStr + " " + horaStr);
        System.out.println("Partida PvP agendada!");

        // Código da partida (comentado)
    }

    // Método para o modo TvT: time vs time
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

        String nomeArena = ""; // Nome não definido

        // Agenda a partida TvT
        partidasAgendadas.add("TvT " + tamanhoTime + "v" + tamanhoTime + " - " + nomeArena + " - " + dataStr + " " + horaStr);
        System.out.println("Partida TvT agendada!");

        // Código da partida (comentado)
    }

    // Método para listar partidas agendadas
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
