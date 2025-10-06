// Pacote do projeto, organizando as classes em um diretório específico
package gradle;

// Importações necessárias para o funcionamento da aplicação
import java.util.ArrayList; // Para usar listas dinâmicas de objetos Tanque
import java.util.Date; // Para registrar a data e hora de entrada dos tanques na arena
import java.util.Scanner; // Para ler entradas do usuário via console
import java.util.Random; // Para gerar IDs aleatórios para os tanques

// Classe principal da aplicação, responsável pelo menu e gerenciamento de tanques
public class App {

    // Método simples que retorna uma saudação, usado para teste inicial
    public String getGreeting() {
        return "Hello World!";
    }

    // Campos da classe (atributos), embora não sejam usados diretamente na lógica principal,
    // podem ser utilizados para armazenar dados temporários ou configurações
    int id; // ID do tanque
    String codinome; // Nome ou codinome do tanque
    int blindagem; // Valor de blindagem do tanque
    int velocidade; // Valor de velocidade do tanque
    int poderDeFogo; // Valor de poder de fogo do tanque
    Date horaEntradaArena; // Data e hora de entrada na arena
    int opTanque, opAliado; // Variáveis para armazenar opções do usuário (tipo de tanque e aliado/inimigo)

    // Método principal que inicia a aplicação
    public static void main(String[] args) {
        // Imprime uma saudação inicial
        System.out.println(new App().getGreeting());

        // Inicializa o scanner para leitura de entradas do usuário
        Scanner sc = new Scanner(System.in);

        // Inicializa o gerador de números aleatórios para IDs
        Random random = new Random();

        // Listas para armazenar os tanques aliados e inimigos
        ArrayList<Tanque> tanquesAliado = new ArrayList<>();
        ArrayList<Tanque> tanqueInimigo = new ArrayList<>();

        // Instancia a classe Modos, que gerencia os modos de batalha, passando as listas de tanques
        Modos modos = new Modos(null, 0, 0, tanquesAliado, tanqueInimigo);

        // Variável para armazenar a opção escolhida pelo usuário no menu
        int opcao;

        // Loop infinito para manter o menu ativo até o usuário encerrar
        while (true) {
            try {
                // Exibe o cabeçalho do menu
                System.out.println("#===# Menu #===#");

                // Mostra a quantidade atual de tanques aliados e inimigos
                System.out.printf("\n Tanques Aliados: %d", tanquesAliado.size());
                System.out.printf("\n Tanques Inimigos: %d", tanqueInimigo.size());

                // Opções do menu principal
                System.out.println("\n(1) Criar tanque");
                System.out.println("(2) Começar partida");

                // Solicita a escolha da opção
                System.out.print("Escolha uma opção: ");
                opcao = sc.nextInt();

                // Estrutura switch para tratar as opções do menu
                switch (opcao) {
                    case 1: // Opção para criar um novo tanque
                        // Limpa o buffer do scanner para evitar problemas com nextLine após nextInt
                        sc.nextLine();

                        // Pergunta se o tanque é aliado ou inimigo
                        System.out.println("Você quer criar um tanque aliado ou inimigo?");
                        System.out.println("(1) Aliado");
                        System.out.println("(2) Inimigo");
                        int opAliado = sc.nextInt();

                        if (opAliado == 1) { // Se escolher aliado
                            // Gera um ID aleatório entre 100 e 999
                            int id = random.nextInt(999) + 100;

                            // Solicita o codinome do tanque
                            System.out.print("Digite o codinome: ");
                            sc.nextLine(); // Limpa o buffer novamente
                            String codinome = sc.nextLine();

                            // Solicita os atributos do tanque
                            System.out.print("Digite a blindagem: (Numero)");
                            int blindagem = sc.nextInt();

                            System.out.print("Digite a velocidade: ");
                            int velocidade = sc.nextInt();

                            System.out.print("Digite o poder de fogo: ");
                            int poderDeFogo = sc.nextInt();

                            // Solicita o tipo de tanque
                            System.out.println("Escolha o tipo de tanque: \n (1) Leve \n (2) Médio \n (3) Pesado");
                            int opTanque = sc.nextInt();

                            // Registra a hora de entrada
                            Date horaEntrada = new Date();

                            // Cria o tanque baseado no tipo escolhido e adiciona à lista de aliados
                            if (opTanque == 1) {
                                tanquesAliado.add(new Leve(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada) {
                                });
                                System.out.println("Tanque Leve adicionado com sucesso!");
                            } else if (opTanque == 2) {
                                tanquesAliado.add(new Medio(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada, 0, 0, 0.0, null) {
                                });
                                System.out.println("Tanque Médio adicionado com sucesso!");
                            } else if (opTanque == 3) {
                                tanquesAliado.add(new Pesado(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada) {
                                });
                                System.out.println("Tanque Pesado adicionado com sucesso!");
                            }
                        } else if (opAliado == 2) { // Se escolher inimigo
                            // Mesmo processo para tanques inimigos
                            int id = random.nextInt(999) + 100;

                            System.out.print("Digite o codinome: ");
                            sc.nextLine(); // Limpa o buffer
                            String codinome = sc.nextLine();

                            System.out.print("Digite a blindagem: (Numero)");
                            int blindagem = sc.nextInt();

                            System.out.print("Digite a velocidade: ");
                            int velocidade = sc.nextInt();

                            System.out.print("Digite o poder de fogo: ");
                            int poderDeFogo = sc.nextInt();

                            System.out.println("Escolha o tipo de tanque: \n (1) Leve \n (2) Médio \n (3) Pesado");
                            int opTanque = sc.nextInt();

                            Date horaEntrada = new Date();

                            if (opTanque == 1) {
                                tanqueInimigo.add(new Leve(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada) {
                                });
                                System.out.println("Tanque Leve inimigo adicionado com sucesso!");
                            } else if (opTanque == 2) {
                                tanqueInimigo.add(new Medio(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada, 0, 0, 0.0, null) {
                                });
                                System.out.println("Tanque Médio inimigo adicionado com sucesso!");
                            } else if (opTanque == 3) {
                                tanqueInimigo.add(new Pesado(id, codinome, blindagem, velocidade, poderDeFogo, horaEntrada) {
                                });
                                System.out.println("Tanque Pesado inimigo adicionado com sucesso!");
                            }
                        }
                        break; // Fim do case 1

                    case 2: // Opção para começar uma partida
                        // Solicita o modo de jogo
                        System.out.println("Selecione um modo");
                        System.out.println("(1) Treino");
                        System.out.println("(2) PvP");
                        System.out.println("(3) TvT");

                        int opModo = sc.nextInt();

                        // Chama o método correspondente na classe Modos baseado na escolha
                        if (opModo == 1) {
                            modos.modoTreino();
                        } else if (opModo == 2) {
                            modos.modoPvP();
                        } else if (opModo == 3) {
                            modos.modoTvT();
                        }
                        break; // Fim do case 2

                    default: // Caso a opção seja inválida
                        System.out.println("Opção inválida! Tente novamente.");
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                // Trata entrada inválida (não numérica)
                System.out.println("Entrada inválida! Por favor, digite um número válido.");
                sc.nextLine(); // Limpa o buffer para evitar loop infinito
            } catch (java.util.NoSuchElementException e) {
                // Trata fim de entrada (ex: Ctrl+D), encerrando o programa
                System.out.println("\nEncerrando o programa...");
                break;
            } catch (Exception e) {
                // Trata qualquer outro erro inesperado
                System.out.println("Erro inesperado: " + e.getMessage());
                sc.nextLine(); // Limpa o buffer
            }
        }
    }
}
