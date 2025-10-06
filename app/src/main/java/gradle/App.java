// Declara o pacote do projeto
package gradle;

// Importa a classe ArrayList para listas
import java.util.ArrayList;
// Importa a classe Date para datas
import java.util.Date;
// Importa a classe Scanner para entrada do usuário
import java.util.Scanner;
// Importa a classe Random para números aleatórios
import java.util.Random;

// Define a classe App
public class App {

    // Define o método getGreeting que retorna uma String
    public String getGreeting() {
        // Retorna "Hello World!"
        return "Hello World!";
    }

    // Declara atributo id como inteiro
    int id;
    // Declara atributo codinome como String
    String codinome;
    // Declara atributo blindagem como inteiro
    int blindagem;
    // Declara atributo velocidade como inteiro
    int velocidade;
    // Declara atributo poderDeFogo como inteiro
    int poderDeFogo;
    // Declara atributo horaEntradaArena como Date
    Date horaEntradaArena;
    // Declara atributos opTanque e opAliado como inteiros
    int opTanque, opAliado;

    // Define o método main
    public static void main(String[] args) {
        // Imprime a saudação
        System.out.println(new App().getGreeting());

        // Cria um Scanner para entrada
        Scanner sc = new Scanner(System.in);

        // Cria um Random para IDs
        Random random = new Random();

        // Cria lista para tanques aliados
        ArrayList<Tanque> tanquesAliado = new ArrayList<>();
        // Cria lista para tanques inimigos
        ArrayList<Tanque> tanqueInimigo = new ArrayList<>();

        // Instancia Modos com listas
        Modos modos = new Modos(null, 0, 0, tanquesAliado, tanqueInimigo);

        // Declara variável opcao como inteiro
        int opcao;

        // Inicia loop while infinito
        while (true) {
            // Inicia bloco try
            try {
                // Imprime cabeçalho do menu
                System.out.println("#===# Menu #===#");

                // Imprime quantidade de aliados
                System.out.printf("\n Tanques Aliados: %d", tanquesAliado.size());
                // Imprime quantidade de inimigos
                System.out.printf("\n Tanques Inimigos: %d", tanqueInimigo.size());

                // Imprime opção 1
                System.out.println("\n(1) Criar tanque");
                // Imprime opção 2
                System.out.println("(2) Começar partida");

                // Imprime prompt para escolha
                System.out.print("Escolha uma opção: ");
                // Lê a opção
                opcao = sc.nextInt();

                // Inicia switch com opcao
                switch (opcao) {
                    // Caso 1
                    case 1:
                        // Limpa buffer do scanner
                        sc.nextLine();

                        // Imprime pergunta sobre aliado ou inimigo
                        System.out.println("Você quer criar um tanque aliado ou inimigo?");
                        // Imprime opção 1
                        System.out.println("(1) Aliado");
                        // Imprime opção 2
                        System.out.println("(2) Inimigo");
                        // Lê opAliado
                        int opAliado = sc.nextInt();

                        // Se opAliado é 1
                        if (opAliado == 1) {
                            // Gera id aleatório
                            int id = random.nextInt(999) + 100;

                            // Imprime prompt para codinome
                            System.out.print("Digite o codinome: ");
                            // Limpa buffer
                            sc.nextLine();
                            // Lê codinome
                            String codinome = sc.nextLine();

                            // Imprime prompt para blindagem
                            System.out.print("Digite a blindagem: (Numero)");
                            // Lê blindagem
                            int blindagem = sc.nextInt();

                            // Imprime prompt para velocidade
                            System.out.print("Digite a velocidade: ");
                            // Lê velocidade
                            int velocidade = sc.nextInt();

                            // Imprime prompt para poder de fogo
                            System.out.print("Digite o poder de fogo: ");
                            // Lê poderDeFogo
                            int poderDeFogo = sc.nextInt();

                            // Imprime opções de tipo de tanque
                            System.out.println("Escolha o tipo de tanque: \n (1) Leve \n (2) Médio \n (3) Pesado");
                            // Lê opTanque
                            int opTanque = sc.nextInt();

                            // Cria Date para horaEntrada
                            Date horaEntrada = new Date();

                            // Se opTanque é 1
                            if (opTanque == 1) {
                                // Adiciona Leve à lista de aliados
                                tanquesAliado.add(new Leve(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada) {
                                });
                                // Imprime sucesso
                                System.out.println("Tanque Leve adicionado com sucesso!");
                            // Se opTanque é 2
                            } else if (opTanque == 2) {
                                // Adiciona Medio à lista de aliados
                                tanquesAliado.add(new Medio(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada, 0, 0, 0.0, null) {
                                });
                                // Imprime sucesso
                                System.out.println("Tanque Médio adicionado com sucesso!");
                            // Se opTanque é 3
                            } else if (opTanque == 3) {
                                // Adiciona Pesado à lista de aliados
                                tanquesAliado.add(new Pesado(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada) {
                                });
                                // Imprime sucesso
                                System.out.println("Tanque Pesado adicionado com sucesso!");
                            }
                        // Se opAliado é 2
                        } else if (opAliado == 2) {
                            // Gera id aleatório
                            int id = random.nextInt(999) + 100;

                            // Imprime prompt para codinome
                            System.out.print("Digite o codinome: ");
                            // Limpa buffer
                            sc.nextLine();
                            // Lê codinome
                            String codinome = sc.nextLine();

                            // Imprime prompt para blindagem
                            System.out.print("Digite a blindagem: (Numero)");
                            // Lê blindagem
                            int blindagem = sc.nextInt();

                            // Imprime prompt para velocidade
                            System.out.print("Digite a velocidade: ");
                            // Lê velocidade
                            int velocidade = sc.nextInt();

                            // Imprime prompt para poder de fogo
                            System.out.print("Digite o poder de fogo: ");
                            // Lê poderDeFogo
                            int poderDeFogo = sc.nextInt();

                            // Imprime opções de tipo de tanque
                            System.out.println("Escolha o tipo de tanque: \n (1) Leve \n (2) Médio \n (3) Pesado");
                            // Lê opTanque
                            int opTanque = sc.nextInt();

                            // Cria Date para horaEntrada
                            Date horaEntrada = new Date();

                            // Se opTanque é 1
                            if (opTanque == 1) {
                                // Adiciona Leve à lista de inimigos
                                tanqueInimigo.add(new Leve(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada) {
                                });
                                // Imprime sucesso
                                System.out.println("Tanque Leve inimigo adicionado com sucesso!");
                            // Se opTanque é 2
                            } else if (opTanque == 2) {
                                // Adiciona Medio à lista de inimigos
                                tanqueInimigo.add(new Medio(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada, 0, 0, 0.0, null) {
                                });
                                // Imprime sucesso
                                System.out.println("Tanque Médio inimigo adicionado com sucesso!");
                            // Se opTanque é 3
                            } else if (opTanque == 3) {
                                // Adiciona Pesado à lista de inimigos
                                tanqueInimigo.add(new Pesado(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada) {
                                });
                                // Imprime sucesso
                                System.out.println("Tanque Pesado inimigo adicionado com sucesso!");
                            }
                        }
                        // Sai do case
                        break;

                    // Caso 2
                    case 2:
                        // Imprime seleção de modo
                        System.out.println("Selecione um modo");
                        // Imprime opção 1
                        System.out.println("(1) Treino");
                        // Imprime opção 2
                        System.out.println("(2) PvP");
                        // Imprime opção 3
                        System.out.println("(3) TvT");

                        // Lê opModo
                        int opModo = sc.nextInt();

                        // Se opModo é 1
                        if (opModo == 1) {
                            // Chama modoTreino
                            modos.modoTreino();
                        // Se opModo é 2
                        } else if (opModo == 2) {
                            // Chama modoPvP
                            modos.modoPvP();
                        // Se opModo é 3
                        } else if (opModo == 3) {
                            // Chama modoTvT
                            modos.modoTvT();
                        }
                        // Sai do case
                        break;

                    // Caso default
                    default:
                        // Imprime opção inválida
                        System.out.println("Opção inválida! Tente novamente.");
                        // Sai do case
                        break;
                }
            // Catch para InputMismatchException
            } catch (java.util.InputMismatchException e) {
                // Imprime entrada inválida
                System.out.println("Entrada inválida! Por favor, digite um número válido.");
                // Limpa buffer
                sc.nextLine();
            // Catch para NoSuchElementException
            } catch (java.util.NoSuchElementException e) {
                // Imprime encerrando
                System.out.println("\nEncerrando o programa...");
                // Sai do loop
                break;
            // Catch para Exception geral
            } catch (Exception e) {
                // Imprime erro inesperado
                System.out.println("Erro inesperado: " + e.getMessage());
                // Limpa buffer
                sc.nextLine();
            }
        }
    }
}
