// Declara o pacote do projeto
package gradle;

// Importa ObjectMapper para JSON
import com.fasterxml.jackson.databind.ObjectMapper;
// Importa SerializationFeature para JSON
import com.fasterxml.jackson.databind.SerializationFeature;
// Importa JavaTimeModule para datas
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
// Importa CSVReader para CSV
import com.opencsv.CSVReader;
// Importa CSVWriter para CSV
import com.opencsv.CSVWriter;
// Importa CsvException para CSV
import com.opencsv.exceptions.CsvException;

// Importa FileReader para arquivos
import java.io.FileReader;
// Importa FileWriter para arquivos
import java.io.FileWriter;
// Importa IOException para exceções
import java.io.IOException;
// Importa ArrayList para listas
import java.util.ArrayList;
// Importa Date para datas
import java.util.Date;
// Importa List para listas
import java.util.List;
// Importa Map para mapas
import java.util.Map;

// Define a classe ImportExportUtil
public class ImportExportUtil {

    // Declara atributo objectMapper
    private static final ObjectMapper objectMapper = new ObjectMapper()
            // Registra módulo JavaTime
            .registerModule(new JavaTimeModule())
            // Configura serialização de datas
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            // Habilita tipagem padrão
            .enableDefaultTyping();

    // Define a classe TeamData
    public static class TeamData {
        // Declara atributo alliedTeam
        public List<Tanque> alliedTeam;
        // Declara atributo enemyTeam
        public List<Tanque> enemyTeam;

        // Define construtor vazio
        public TeamData() {}

        // Define construtor com parâmetros
        public TeamData(List<Tanque> alliedTeam, List<Tanque> enemyTeam) {
            // Atribui alliedTeam
            this.alliedTeam = alliedTeam;
            // Atribui enemyTeam
            this.enemyTeam = enemyTeam;
        }
    }

    // Define o método exportToJSON
    public static void exportToJSON(List<Tanque> alliedTeam, List<Tanque> enemyTeam, String filePath) throws IOException {
        // Cria TeamData
        TeamData data = new TeamData(alliedTeam, enemyTeam);
        // Escreve no arquivo
        objectMapper.writeValue(new java.io.File(filePath), data);
    }

    // Define o método importFromJSON
    public static TeamData importFromJSON(String filePath) throws IOException {
        // Lê do arquivo
        return objectMapper.readValue(new java.io.File(filePath), TeamData.class);
    }

    // Define o método exportToCSV
    public static void exportToCSV(List<Tanque> alliedTeam, List<Tanque> enemyTeam, String filePath) throws IOException {
        // Inicia bloco try com CSVWriter
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Escreve cabeçalho
            writer.writeNext(new String[]{"Team", "Type", "Id", "Codinome", "Blindagem", "Velocidade", "PoderDeFogo", "Vida", "Arma", "HoraEntradaArena"});

            // Para cada tanque aliado
            for (Tanque tank : alliedTeam) {
                // Escreve dados
                writer.writeNext(getTankData("Allied", tank));
            }

            // Para cada tanque inimigo
            for (Tanque tank : enemyTeam) {
                // Escreve dados
                writer.writeNext(getTankData("Enemy", tank));
            }
        }
    }

    // Define o método getTankData
    private static String[] getTankData(String team, Tanque tank) {
        // Determina tipo
        String type = tank instanceof Leve ? "Leve" : tank instanceof Medio ? "Medio" : "Pesado";
        // Retorna array
        return new String[]{
                team,
                type,
                String.valueOf(tank.getId()),
                tank.getCodinome(),
                String.valueOf(tank.getBlindagem()),
                String.valueOf(tank.getVelocidade()),
                String.valueOf(tank.getPoderDeFogo()),
                String.valueOf(tank.getVida()),
                tank.getArma(),
                tank.getHoraEntradaArena() != null ? String.valueOf(tank.getHoraEntradaArena().getTime()) : ""
        };
    }

    // Define o método importFromCSV
    public static TeamData importFromCSV(String filePath) throws IOException, CsvException {
        // Cria lista alliedTeam
        List<Tanque> alliedTeam = new ArrayList<>();
        // Cria lista enemyTeam
        List<Tanque> enemyTeam = new ArrayList<>();

        // Inicia bloco try com CSVReader
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            // Lê todas as linhas
            List<String[]> lines = reader.readAll();
            // Declara isHeader
            boolean isHeader = true;
            // Para cada linha
            for (String[] line : lines) {
                // Se é cabeçalho
                if (isHeader) {
                    // Define como false
                    isHeader = false;
                    // Continua
                    continue;
                }
                // Obtém team
                String team = line[0];
                // Obtém type
                String type = line[1];
                // Parse id
                int id = Integer.parseInt(line[2]);
                // Obtém codinome
                String codinome = line[3];
                // Parse blindagem
                int blindagem = Integer.parseInt(line[4]);
                // Parse velocidade
                int velocidade = Integer.parseInt(line[5]);
                // Parse poderDeFogo
                int poderDeFogo = Integer.parseInt(line[6]);
                // Parse vida
                int vida = Integer.parseInt(line[7]);
                // Obtém arma
                String arma = line[8];
                // Cria Date
                Date horaEntradaArena = line[9].isEmpty() ? new Date() : new Date(Long.parseLong(line[9]));

                // Declara tank
                Tanque tank;
                // Inicia switch type
                switch (type) {
                    // Caso Leve
                    case "Leve":
                        // Instancia Leve
                        tank = new Leve(id, codinome, blindagem, velocidade, poderDeFogo, horaEntradaArena);
                        // Sai
                        break;
                    // Caso Medio
                    case "Medio":
                        // Instancia Medio
                        tank = new Medio(id, codinome, blindagem, velocidade, poderDeFogo, horaEntradaArena, 0, 0, 0.0, null);
                        // Sai
                        break;
                    // Caso Pesado
                    case "Pesado":
                        // Instancia Pesado
                        tank = new Pesado(id, codinome, blindagem, velocidade, poderDeFogo, horaEntradaArena);
                        // Sai
                        break;
                    // Default
                    default:
                        // Lança exceção
                        throw new IllegalArgumentException("Unknown tank type: " + type);
                }
                // Define vida
                tank.setVida(vida);
                // Define arma
                tank.setArma(arma);
                // Chama Caracterisrticas
                tank.Caracterisrticas();

                // Se Allied
                if ("Allied".equals(team)) {
                    // Adiciona a alliedTeam
                    alliedTeam.add(tank);
                // Senão se Enemy
                } else if ("Enemy".equals(team)) {
                    // Adiciona a enemyTeam
                    enemyTeam.add(tank);
                }
            }
        }
        // Retorna TeamData
        return new TeamData(alliedTeam, enemyTeam);
    }
}
