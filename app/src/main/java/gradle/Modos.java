// Declara o pacote do projeto
package gradle;

// Importa LocalDateTime para data e hora
import java.time.LocalDateTime;
// Importa DateTimeFormatter para formatar datas
import java.time.format.DateTimeFormatter;
// Importa ArrayList para listas
import java.util.ArrayList;
// Importa Date para datas
import java.util.Date;
// Importa Scanner para entrada
import java.util.Scanner;

// Define a classe Modos
public class Modos {

    // Declara Scanner sc
    Scanner sc = new Scanner(System.in);
    // Declara Date dataHora
    Date dataHora;
    // Declara double duracao
    double duracao;
    // Declara int arena
    int arena;
    // Declara LocalDateTime agora
    LocalDateTime agora = LocalDateTime.now();
    // Declara DateTimeFormatter formato
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    // Declara String formatada
    String formatada = agora.format(formato);
    // Declara ArrayList tanquesAliado
    ArrayList<Tanque> tanquesAliado;
    // Declara ArrayList tanqueInimigo
    ArrayList<Tanque> tanqueInimigo;
    // Declara ArrayList partidasAgendadas
    ArrayList<String> partidasAgendadas = new ArrayList<>();

    // Define o construtor da classe Modos
    public Modos(Date dataHora, double duracao, int arena, ArrayList<Tanque> tanquesAliado, ArrayList<Tanque> tanqueInimigo) {
        // Atribui dataHora
        this.dataHora = dataHora;
        // Atribui duracao
        this.duracao = duracao;
        // Atribui arena
        this.arena = arena;
        // Atribui tanquesAliado
        this.tanquesAliado = tanquesAliado;
        // Atribui tanqueInimigo
        this.tanqueInimigo = tanqueInimigo;
    }

    // Define o método modoTreino
    void modoTreino() {
        // Verifica se listas estão vazias
        if (tanquesAliado.size() == 0 || tanqueInimigo.size() == 0) {
            // Imprime mensagem
            System.out.println("Você deve criar tanques aliados e inimigos para continuar!");
            // Retorna
            return;
        }

        // Imprime escolha de mapa
        System.out.println("Escolha um mapa: ");
        // Imprime opção 1
        System.out.println("(1) Deserto");
        // Imprime opção 2
        System.out.println("(2) Urbano");
        // Imprime opção 3
        System.out.println("(3) Campo Aberto");
        // Lê arena
        arena = sc.nextInt();

        // Declara nomeArena
        String nomeArena = "";

        // Obtém tanqueAliado
        Tanque tanqueAliado = tanquesAliado.get(0);
        // Obtém tanqueInimigoObj
        Tanque tanqueInimigoObj = tanqueInimigo.get(0);

        // Inicia switch arena
        switch (arena) {
            // Caso 1
            case 1:
                // Imprime mapa Deserto
                System.out.println("Mapa selecionado: Deserto");
                // Imprime horário
                System.out.println("Horário inicial da partida: " + formatada);
                // Modifica velocidade aliado
                tanqueAliado.setVelocidade((int)(tanqueAliado.getVelocidade() * 0.60));
                // Modifica velocidade inimigo
                tanqueInimigoObj.setVelocidade((int)(tanqueInimigoObj.getVelocidade() * 0.60));
                // Sai
                break;
            // Caso 2
            case 2:
                // Imprime mapa Urbano
                System.out.println("Mapa selecionado: Urbano");
                // Imprime horário
                System.out.println("Horário inicial da partida: " + formatada);
                // Modifica blindagem aliado
                tanqueAliado.setBlindagem((int)(tanqueAliado.getBlindagem() * 2));
                // Modifica blindagem inimigo
                tanqueInimigoObj.setBlindagem((int)(tanqueInimigoObj.getBlindagem() * 2));
                // Sai
                break;
            // Caso 3
            case 3:
                // Imprime mapa Campo Aberto
                System.out.println("Mapa selecionado: Campo Aberto");
                // Imprime horário
                System.out.println("Horário inicial da partida: " + formatada);
                // Modifica poder de fogo aliado
                tanqueAliado.setPoderDeFogo((int)(tanqueAliado.getPoderDeFogo() * 2));
                // Modifica poder de fogo inimigo
                tanqueInimigoObj.setPoderDeFogo((int)(tanqueInimigoObj.getPoderDeFogo() * 2));
                // Sai
                break;
            // Default
            default:
                // Imprime inválida
                System.out.println("Opção inválida");
                // Sai
                break;
        }

        // Imprime tanque aliado
        System.out.println("#=# TANQUE ALIADO #=#");
        // Imprime info aliado
        System.out.println("|Codinome: " + tanqueAliado.getCodinome() +
                        "|Blindagem: " + tanqueAliado.getBlindagem() +
                        "|Velocidade: " + tanqueAliado.getVelocidade() + "|");

        // Imprime tanque inimigo
        System.out.println("#=# TANQUE INIMIGO #=#");
        // Imprime info inimigo
        System.out.println("|Codinome: " + tanqueInimigoObj.getCodinome() +
            "|Blindagem: " + tanqueInimigoObj.getBlindagem() +
            "|Velocidade: " + tanqueInimigoObj.getVelocidade() + "|");

        // Adiciona à agendadas
        partidasAgendadas.add("Treino - " + nomeArena + " - " + formatada);
        // Imprime agendada
        System.out.println("Partida de treino agendada!");
    }

    // Define o método modoPvP
    void modoPvP() {
        // Verifica tanques
        if (tanquesAliado.size() == 0 || tanqueInimigo.size() == 0) {
            // Imprime mensagem
            System.out.println("Você deve criar um Tanque para continuar!");
            // Retorna
            return;
        }

        // Imprime agendamento PvP
        System.out.println("Agendamento de Partida PvP");
        // Imprime prompt data
        System.out.print("Data (dd/MM/yyyy): ");
        // Lê dataStr
        String dataStr = sc.next();
        // Imprime prompt hora
        System.out.print("Hora (HH:mm): ");
        // Lê horaStr
        String horaStr = sc.next();
        // Imprime prompt duração
        System.out.print("Duração (horas): ");
        // Lê duracaoPvP
        double duracaoPvP = sc.nextDouble();

        // Imprime escolha mapa
        System.out.println("Escolha um mapa: ");
        // Imprime 1
        System.out.println("(1) Deserto");
        // Imprime 2
        System.out.println("(2) Urbano");
        // Imprime 3
        System.out.println("(3) Campo Aberto");
        // Lê arenaPvP
        int arenaPvP = sc.nextInt();

        // Declara nomeArena
        String nomeArena = "";

        // Adiciona à agendadas
        partidasAgendadas.add("PvP - " + nomeArena + " - " + dataStr + " " + horaStr);
        // Imprime agendada
        System.out.println("Partida PvP agendada!");
    }

    // Define o método modoTvT
    void modoTvT() {
        // Imprime agendamento TvT
        System.out.println("Agendamento de Partida Time vs Time");
        // Imprime prompt tamanho
        System.out.print("Quantos tanques por time? ");
        // Lê tamanhoTime
        int tamanhoTime = sc.nextInt();

        // Imprime prompt data
        System.out.print("Data (dd/MM/yyyy): ");
        // Lê dataStr
        String dataStr = sc.next();
        // Imprime prompt hora
        System.out.print("Hora (HH:mm): ");
        // Lê horaStr
        String horaStr = sc.next();
        // Imprime prompt duração
        System.out.print("Duração (horas): ");
        // Lê duracaoTvT
        double duracaoTvT = sc.nextDouble();

        // Imprime escolha mapa
        System.out.println("Escolha um mapa: ");
        // Imprime 1
        System.out.println("(1) Deserto");
        // Imprime 2
        System.out.println("(2) Urbano");
        // Imprime 3
        System.out.println("(3) Campo Aberto");
        // Lê arenaTvT
        int arenaTvT = sc.nextInt();

        // Declara nomeArena
        String nomeArena = "";

        // Adiciona à agendadas
        partidasAgendadas.add("TvT " + tamanhoTime + "v" + tamanhoTime + " - " + nomeArena + " - " + dataStr + " " + horaStr);
        // Imprime agendada
        System.out.println("Partida TvT agendada!");
    }

    // Define o método listarPartidas
    public void listarPartidas() {
        // Imprime cabeçalho
        System.out.println("PARTIDAS AGENDADAS:");
        // Se vazia
        if (partidasAgendadas.isEmpty()) {
            // Imprime nenhuma
            System.out.println("Nenhuma partida agendada.");
        // Senão
        } else {
            // Para cada partida
            for (String partida : partidasAgendadas) {
                // Imprime partida
                System.out.println(" - " + partida);
            }
        }
    }
}
