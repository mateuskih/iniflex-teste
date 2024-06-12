package com.mateus.iniflex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.mateus.iniflex.model.Funcionario;

public class Principal {
	private static List<Funcionario> funcionarios = null;

	public static void main(String[] args) {

		funcionarios = carregarTabelaFuncionarios(funcionarios);
		
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nDesenvolvido por Mateus Oliveira de Souza");
            System.out.println("\nTeste Prático - Iniflex");
            System.out.println("1. Inserir todos os funcionários");
            System.out.println("2. Remover o funcionário \"João\"");
            System.out.println("3. Imprimir todos os funcionários");
            System.out.println("4. Aumentar salário em 10%");
            System.out.println("5. Agrupar funcionários por função");
            System.out.println("6. Imprimir funcionários agrupados por função");
            System.out.println("7. Imprimir funcionários que fazem aniversário em outubro e dezembro");
            System.out.println("8. Imprimir funcionário com a maior idade");
            System.out.println("9. Imprimir lista de funcionários em ordem alfabética");
            System.out.println("10. Imprimir total dos salários");
            System.out.println("11. Imprimir quantos salários mínimos ganha cada funcionário");
            System.out.println("12. Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    inserirFuncionarios();
                    break;
                case 2:
                    removerFuncionarioJoao();
                    break;
                case 3:
                    imprimirFuncionarios();
                    break;
                case 4:
                    aumentarSalario();
                    break;
                case 5:
                	 Map<String, List<Funcionario>> funcionariosPorFuncao = agruparPorFuncao();
                     break;
                 case 6:
                     imprimirAgrupadosPorFuncao(agruparPorFuncao());
                     break;
                case 7:
                    imprimirAniversariantes();
                    break;
                case 8:
                    imprimirFuncionarioMaisVelho();
                    break;
                case 9:
                    imprimirEmOrdemAlfabetica();
                    break;
                case 10:
                    imprimirTotalSalarios();
                    break;
                case 11:
                    imprimirSalariosMinimos();
                    break;
                case 12:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

	}
	
    public static List<Funcionario> carregarTabelaFuncionarios(List<Funcionario> funcionarios) {
    	return funcionarios = new ArrayList<>(Arrays.asList(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 05), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));
    }
    
    private static void inserirFuncionarios() {
        System.out.println("Funcionários já inseridos na inicialização.");
    }

    private static void removerFuncionarioJoao() {
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
        System.out.println("Funcionário 'João' removido.");
    }

    private static void imprimirFuncionarios() {
        System.out.println("Lista de Funcionários:");
        funcionarios.forEach(System.out::println);
    }

    private static void aumentarSalario() {
        funcionarios.forEach(funcionario -> funcionario.setSalario(funcionario.getSalario().multiply(BigDecimal.valueOf(1.1))));
        System.out.println("Salários aumentados em 10%.");
    }

    private static Map<String, List<Funcionario>> agruparPorFuncao() {
        System.out.println("Funcionários agrupados por função.");
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    private static void imprimirAgrupadosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(System.out::println);
        });
    }
    private static void imprimirAniversariantes() {
        System.out.println("\nFuncionários que fazem aniversário em outubro e dezembro:");
        funcionarios.stream()
                .filter(funcionario ->
                        funcionario.getDataNascimento().getMonthValue() == 10 ||
                                funcionario.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);
    }

    private static void imprimirFuncionarioMaisVelho() {
        System.out.println("\nFuncionário com maior idade:");
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);
        if (maisVelho != null) {
            System.out.println("Nome: " + maisVelho.getNome() +
                    ", Idade: " + (LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear()));
        }
    }

    private static void imprimirEmOrdemAlfabetica() {
        System.out.println("\nLista de funcionários em ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);
    }

    private static void imprimirTotalSalarios() {
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários dos funcionários: " + totalSalarios);
    }

    private static void imprimirSalariosMinimos() {
        System.out.println("\nSalários mínimos ganhos por cada funcionário:");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(funcionario -> {
	    	 BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.DOWN);
	         System.out.println(funcionario.getNome() + ": " + salariosMinimos);
        });
    }

}
