package gradle;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ImportExportUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .enableDefaultTyping(); // For polymorphism

    // Data class for teams
    public static class TeamData {
        public List<Tanque> alliedTeam;
        public List<Tanque> enemyTeam;

        public TeamData() {}

        public TeamData(List<Tanque> alliedTeam, List<Tanque> enemyTeam) {
            this.alliedTeam = alliedTeam;
            this.enemyTeam = enemyTeam;
        }
    }

    // Export to JSON
    public static void exportToJSON(List<Tanque> alliedTeam, List<Tanque> enemyTeam, String filePath) throws IOException {
        TeamData data = new TeamData(alliedTeam, enemyTeam);
        objectMapper.writeValue(new java.io.File(filePath), data);
    }

    // Import from JSON
    public static TeamData importFromJSON(String filePath) throws IOException {
        return objectMapper.readValue(new java.io.File(filePath), TeamData.class);
    }

    // Export to CSV
    public static void exportToCSV(List<Tanque> alliedTeam, List<Tanque> enemyTeam, String filePath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Header
            writer.writeNext(new String[]{"Team", "Type", "Id", "Codinome", "Blindagem", "Velocidade", "PoderDeFogo", "Vida", "Arma", "HoraEntradaArena"});

            // Allied team
            for (Tanque tank : alliedTeam) {
                writer.writeNext(getTankData("Allied", tank));
            }

            // Enemy team
            for (Tanque tank : enemyTeam) {
                writer.writeNext(getTankData("Enemy", tank));
            }
        }
    }

    private static String[] getTankData(String team, Tanque tank) {
        String type = tank instanceof Leve ? "Leve" : tank instanceof Medio ? "Medio" : "Pesado";
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

    // Import from CSV
    public static TeamData importFromCSV(String filePath) throws IOException, CsvException {
        List<Tanque> alliedTeam = new ArrayList<>();
        List<Tanque> enemyTeam = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> lines = reader.readAll();
            boolean isHeader = true;
            for (String[] line : lines) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String team = line[0];
                String type = line[1];
                int id = Integer.parseInt(line[2]);
                String codinome = line[3];
                int blindagem = Integer.parseInt(line[4]);
                int velocidade = Integer.parseInt(line[5]);
                int poderDeFogo = Integer.parseInt(line[6]);
                int vida = Integer.parseInt(line[7]);
                String arma = line[8];
                Date horaEntradaArena = line[9].isEmpty() ? new Date() : new Date(Long.parseLong(line[9])); // Assuming timestamp

                Tanque tank;
                switch (type) {
                    case "Leve":
                        tank = new Leve(id, codinome, blindagem, velocidade, poderDeFogo, horaEntradaArena);
                        break;
                    case "Medio":
                        tank = new Medio(id, codinome, blindagem, velocidade, poderDeFogo, horaEntradaArena, 0, 0, 0.0, null);
                        break;
                    case "Pesado":
                        tank = new Pesado(id, codinome, blindagem, velocidade, poderDeFogo, horaEntradaArena);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown tank type: " + type);
                }
                tank.setVida(vida);
                tank.setArma(arma);
                tank.Caracterisrticas();

                if ("Allied".equals(team)) {
                    alliedTeam.add(tank);
                } else if ("Enemy".equals(team)) {
                    enemyTeam.add(tank);
                }
            }
        }
        return new TeamData(alliedTeam, enemyTeam);
    }
}
