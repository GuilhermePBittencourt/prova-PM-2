package src.pm_prova2;

import java.util.List;
import java.util.Scanner;

import Aluguel;

public class Menu {

    private final Locadora locadora;
    private final Scanner scanner;

    public Menu(Locadora locadora) {
        this.locadora = locadora;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            mostrarOpcoes();
            opcao = lerInteiro("Opção: ");
            System.out.println();
            switch (opcao) {
                case 1:
                    associarVeiculos();
                    break;
                case 2:
                    exibirVeiculos(locadora.getVeiculos(), "Nenhum veículo cadastrado.");
                    break;
                case 3:
                    exibirAlugueis();
                    break;
                case 4:
                    exibirVeiculos(locadora.getVeiculosDisponiveis(), "Nenhum veículo disponível.");
                    break;
                case 5:
                    avaliar();
                    break;
                case 6:
                    inspecionar();
                    break;
                case 7:
                    removerEmManutencao();
                    break;
                case 0:
                    System.out.println("Sistema encerrado");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
        scanner.close();
    }

    private void mostrarOpcoes() {
        System.out.println();
        System.out.println("         SISTEMA DE ALUGUEL DE VEÍCULOS");
        System.out.println("                                               ");
        System.out.println("   1. Associar veículos a um aluguel");
        System.out.println("   2. Exibir todos os veículos");
        System.out.println("   3. Exibir todos os aluguéis");
        System.out.println("   4. Exibir veículos disponíveis");
        System.out.println("   5. Avaliar");
        System.out.println("   6. Realizar inspeção");
        System.out.println("   7. Remover veículos em manutenção");
        System.out.println("   0. Sair");
    }

    private void associarVeiculos() {
        Aluguel aluguel = escolher(locadora.getAlugueis(), "Escolha o aluguel");
        if (aluguel == null) {
            return;
        }
        System.out.println("Aluguel selecionado: " + aluguel);

        while (true) {
            List<Veiculo> disponiveis = locadora.getVeiculosDisponiveis();
            if (disponiveis.isEmpty()) {
                System.out.println("Não há veículos disponíveis.");
                return;
            }
            System.out.println("\nVeículos disponíveis:");
            for (Veiculo v : disponiveis) {
                System.out.printf("  %s | diária R$ %.2f%n", v, v.getValorDiaria());
            }
            System.out.print("Digite a placa do veículo (ENTER para finalizar): ");
            String placa = scanner.nextLine().trim();
            if (placa.isEmpty()) {
                return;
            }
            try {
                locadora.associarVeiculo(aluguel, placa);
                System.out.printf("Veículo associado! Valor total do aluguel: R$ %.2f%n", aluguel.getValorTotal());
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void exibirVeiculos(List<Veiculo> lista, String mensagemVazia) {
        if (lista.isEmpty()) {
            System.out.println(mensagemVazia);
            return;
        }
        for (Veiculo v : lista) {
            v.exibirDetalhes();
        }
    }

    private void exibirAlugueis() {
        List<Aluguel> alugueis = locadora.getAlugueis();
        if (alugueis.isEmpty()) {
            System.out.println("Nenhum aluguel cadastrado.");
            return;
        }
        for (Aluguel a : alugueis) {
            a.exibirDetalhes();
            System.out.println();
        }
    }

    private void avaliar() {
        Avaliavel item = escolher(locadora.getAvaliaveis(), "Escolha o que deseja avaliar");
        if (item == null) {
            return;
        }
        int nota = lerInteiro("Nota (1 a 5 estrelas): ");
        try {
            item.avaliar(nota);
            System.out.println("Avaliação registrada para: " + item);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void inspecionar() {
        Inspecionavel item = escolher(locadora.getInspecionaveis(), "Escolha o veículo a inspecionar");
        if (item == null) {
            return;
        }
        try {
            item.realizarInspecao();
            System.out.println("Inspeção concluída. Resultado: " + item);
        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void removerEmManutencao() {
        List<Veiculo> removidos = locadora.removerVeiculosEmManutencao();
        if (removidos.isEmpty()) {
            System.out.println("Nenhum veículo em manutenção.");
            return;
        }
        System.out.println("Veículos removidos:");
        for (Veiculo v : removidos) {
            System.out.println("  " + v.getModelo() + " [" + v.getPlaca() + "]");
        }
    }

    private <T> T escolher(List<T> opcoes, String titulo) {
        if (opcoes.isEmpty()) {
            System.out.println("Não há itens para esta operação.");
            return null;
        }
        System.out.println(titulo + ":");
        for (int i = 0; i < opcoes.size(); i++) {
            System.out.println("  " + (i + 1) + ") " + opcoes.get(i));
        }
        int escolha = lerInteiro("Número (0 para voltar): ");
        if (escolha == 0) {
            return null;
        }
        if (escolha < 1 || escolha > opcoes.size()) {
            System.out.println("Número inválido.");
            return null;
        }
        return opcoes.get(escolha - 1);
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números inteiros.");
            }
        }
    }
}
