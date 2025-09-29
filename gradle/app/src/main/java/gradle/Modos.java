package gradle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Modos {
    Date dataHora;
    double duracao;
    int arena;
    LocalDateTime agora = LocalDateTime.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String formatada = agora.format(formato);
    ArrayList<Tanque> tanquesAliado;
    ArrayList<Tanque> tanqueInimigo;
    ArrayList<String> partidasAgendadas = new ArrayList<>();

    public Modos(ArrayList<Tanque> tanquesAliado, ArrayList<Tanque> tanqueInimigo) {
        this.tanquesAliado = tanquesAliado;
        this.tanqueInimigo = tanqueInimigo;
    }

    String modoTreino(int arena) {
        if (tanquesAliado.size() == 0 || tanqueInimigo.size() == 0) {
            return "Você deve criar tanques aliados e inimigos para continuar!";
        }

        String nomeArena = "";
        StringBuilder result = new StringBuilder();

        Tanque tanqueAliado = tanquesAliado.get(0);
        Tanque tanqueInimigoObj = tanqueInimigo.get(0);

        switch (arena) {
            case 1:
                nomeArena = "Deserto";
                result.append("Mapa selecionado: Deserto\n");
                result.append("Horário inicial da partida: " + formatada + "\n");

                tanqueAliado.setVelocidade((int)(tanqueAliado.getVelocidade() * 0.60));
                tanqueInimigoObj.setVelocidade((int)(tanqueInimigoObj.getVelocidade() * 0.60));
                break;

            case 2:
                nomeArena = "Urbano";
                result.append("Mapa selecionado: Urbano\n");
                result.append("Horário inicial da partida: " + formatada + "\n");

                tanqueAliado.setBlindagem((int)(tanqueAliado.getBlindagem() * 2));
                tanqueInimigoObj.setBlindagem((int)(tanqueInimigoObj.getBlindagem() * 2));
                break;

            case 3:
                nomeArena = "Campo Aberto";
                result.append("Mapa selecionado: Campo Aberto\n");
                result.append("Horário inicial da partida: " + formatada + "\n");

                tanqueAliado.setPoderDeFogo((int)(tanqueAliado.getPoderDeFogo() * 2));
                tanqueInimigoObj.setPoderDeFogo((int)(tanqueInimigoObj.getPoderDeFogo() * 2));
                break;

            default:
                return "Opção inválida";
        }

        result.append("#=# TANQUE ALIADO #=#\n");
        result.append("|Codinome: " + tanqueAliado.getCodinome() +
                        "|Blindagem: " + tanqueAliado.getBlindagem() +
                        "|Velocidade: " + tanqueAliado.getVelocidade() + "|\n");

        result.append("#=# TANQUE INIMIGO #=#\n");
        result.append("|Codinome: " + tanqueInimigoObj.getCodinome() +
            "|Blindagem: " + tanqueInimigoObj.getBlindagem() +
            "|Velocidade: " + tanqueInimigoObj.getVelocidade() + "|\n");

        partidasAgendadas.add("Treino - " + nomeArena + " - " + formatada);
        result.append("Partida de treino agendada!\n");

        return result.toString();
    }

    String modoPvP(String dataStr, String horaStr, double duracao, int arena) {
        if (tanquesAliado.size() == 0 || tanqueInimigo.size() == 0) {
            return "Você deve criar um Tanque para continuar!";
        }

        String nomeArena = getNomeArena(arena);

        partidasAgendadas.add("PvP - " + nomeArena + " - " + dataStr + " " + horaStr);
        return "Partida PvP agendada!";
    }

    private String getNomeArena(int arena) {
        switch (arena) {
            case 1: return "Deserto";
            case 2: return "Urbano";
            case 3: return "Campo Aberto";
            default: return "";
        }
    }

    String modoTvT(int tamanhoTime, String dataStr, String horaStr, double duracao, int arena) {
        String nomeArena = getNomeArena(arena);

        partidasAgendadas.add("TvT " + tamanhoTime + "v" + tamanhoTime + " - " + nomeArena + " - " + dataStr + " " + horaStr);
        return "Partida TvT agendada!";
    }

    public String listarPartidas() {
        StringBuilder sb = new StringBuilder("PARTIDAS AGENDADAS:\n");
        if (partidasAgendadas.isEmpty()) {
            sb.append("Nenhuma partida agendada.");
        } else {
            for (String partida : partidasAgendadas) {
                sb.append(" - ").append(partida).append("\n");
            }
        }
        return sb.toString();
    }
}