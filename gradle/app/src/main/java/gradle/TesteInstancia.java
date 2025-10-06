package gradle;

import java.util.Date;

public class TesteInstancia {
    public static void main(String[] args) {
        Date hora = new Date();

        // Testar instanciação das classes
        Leve leve = new Leve(1, "Teste Leve", 30, 80, 20, hora);
        Medio medio = new Medio(2, "Teste Medio", 30, 60, 40, hora, 0, 0, 0.0, null);
        Pesado pesado = new Pesado(3, "Teste Pesado", 60, 40, 40, hora);

        System.out.println("Instanciação bem-sucedida!");
        System.out.println("Leve: " + leve.getCodinome());
        System.out.println("Medio: " + medio.getCodinome());
        System.out.println("Pesado: " + pesado.getCodinome());
    }
}
