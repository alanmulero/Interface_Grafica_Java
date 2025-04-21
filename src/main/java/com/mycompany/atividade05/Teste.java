/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.atividade05;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 *
 * @author alan toledo 
 * alan.mulero@gmail.com
 * 
 * Atividade 7, dando continuidade na atividade 05.
 * Implantando o tratamento de Exceções.
 * 
 */
public class Teste {

    Passeio[] arrayPasseio = new Passeio[5];
    Carga[] arrayCarga = new Carga[5];
    ArrayList<String> placas = new ArrayList<>();
    Leitura leituraClasse = new Leitura();

    int opcao = -1;
    int countPasseio = 0;
    int countCarga = 0;
    boolean placaEncontrada = false;

    public void exibeMenu() {

        while (opcao != 7) {

            var menu = """
            ###################### Escolha uma opcao abaixo ####################
                       
					1 - Cadastrar Veículo de Passeio
					2 - Cadastrar Veículo de Carga
					3 - Imprimir Todos os Veículos de Passeio
					4 - Imprimir Todos os Veículos de Carga
					5 - Imprimir Veículo de Passeio pela Placa
					6 - Imprimir Veículo de Carga pela Placa
					7 - Sair       
                       
            ####################################################################
					""";

            System.out.println(menu);
            try {
                var leitura = leituraClasse.scaneer;
                opcao = leitura.nextInt();
                leitura.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Use apenas numeros inteiros." + e.getMessage());
                break;
            }

            switch (opcao) {

                case 1:
                    cadastrarPasseio();
                    break;
                case 2:
                    cadastrarCarga();
                    break;
                case 3:
                    listarPasseio();
                    break;
                case 4:
                    listarCarga();
                    break;
                case 5:
                    imprimirPlacaPasseio();
                    break;
                case 6:
                    imprimirPlacaCarga();
                    break;
                case 7:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida. Digite numeros inteiros entre 0 e 7");
                    break;
            }

        }
    }

    private void cadastrarPasseio() {
        var leitura = leituraClasse.scaneer;
        System.out.println("Digite a quantidade de passageiros: ");
        var qtdPassageiros = leitura.nextInt();
        leitura.nextLine();

        System.out.println("Digite a placa do veiculo de Passeio: ");
        var placa = leitura.nextLine();

        System.out.println("Digite a marca do veiculo: ");
        var marca = leitura.nextLine();
        System.out.println("Digite o modelo do veiculo: ");
        var modelo = leitura.nextLine();
        System.out.println("Digite a cor do veiculo: ");
        var cor = leitura.nextLine();
        System.out.println("Digite a velocidade maxima do veiculo: ");
        var velocidade = leitura.nextFloat();
        System.out.println("Digite a quantidade de rodas do veiculo: ");
        var rodas = leitura.nextInt();
        System.out.println("Digite a quantidade de pistoes do veiculo: ");
        var pistao = leitura.nextInt();
        System.out.println("Digite a potencia do motor: ");
        var potencia = leitura.nextInt();
        System.out.println("*********************************************");

        try {
            if (countPasseio > 0 && placas.contains(placa)) {
                System.out.println("***********************");
                System.out.println("Veiculo ja cadastrado!");
                System.out.println("************************");
                exibeMenu();

            } else {
                arrayPasseio[countPasseio] = new Passeio(qtdPassageiros, placa, marca, modelo, cor, velocidade, rodas, pistao, potencia);
            }
            if (countPasseio > 5) {
                throw new ArrayIndexOutOfBoundsException("O Vetor está cheio!!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Vetor cheio!!" + e.getMessage());
        }
        countPasseio += 1;
        placas.add(placa);
        System.out.println("Voce pode registrar mais:  " + (5 - countPasseio) + " Veiculos de passeio.");

        System.out.println("Deseja cadastrar um novo veiculo de passeio? Digite 1 para SIM ou 0 Para Não");
        var novoCadastro = leitura.nextInt();

        if (novoCadastro == 1) {
            cadastrarPasseio();
        } else {
            exibeMenu();
        }

        if (opcao == 7) {
            System.out.println("Fim do programa");
            System.exit(0);
        } else {
            exibeMenu();
        }

    }

    private void cadastrarCarga() {
        var leitura = leituraClasse.scaneer;
        System.out.println("Digite a carga Maxima");
        var cargaMaxima = leitura.nextInt();
        leitura.nextLine();
        System.out.println("Digite a Tara: ");
        var tara = leitura.nextInt();
        System.out.println("Digite a placa do veiculo de Carga: ");
        leitura.nextLine();
        var placaCarga = leitura.nextLine();

        System.out.println("Digite a marca do veiculo: ");
        var marcaCarga = leitura.nextLine();
        System.out.println("Digite o modelo do veiculo: ");
        var modeloCarga = leitura.nextLine();
        System.out.println("Digite a cor do veiculo: ");
        var corCarga = leitura.nextLine();
        System.out.println("Digite a velocidade maxima do veiculo: ");
        var velocidadeCarga = leitura.nextFloat();
        System.out.println("Digite a quantidade de rodas do veiculo: ");
        var rodasCarga = leitura.nextInt();
        System.out.println("Digite a quantidade de pistoes do veiculo: ");
        var pistaoCarga = leitura.nextInt();
        System.out.println("Digite a potencia do motor: ");
        var potenciaCarga = leitura.nextInt();
        System.out.println("*********************************************");

        try {
            if (countPasseio > 0 && placas.contains(placaCarga)) {
                System.out.println("***********************");
                System.out.println("Veiculo ja cadastrado!");
                System.out.println("************************");
                exibeMenu();
            } else {

                arrayCarga[countCarga] = new Carga(cargaMaxima, tara, placaCarga, marcaCarga, modeloCarga, corCarga, velocidadeCarga, rodasCarga, pistaoCarga, potenciaCarga);
            }
            if (countCarga > 5) {
                throw new ArrayIndexOutOfBoundsException("O Vetor está cheio!!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Vetor cheio!!" + e.getMessage());
        }
        countCarga += 1;
        placas.add(placaCarga);

        System.out.println("Voce pode registrar mais:  " + (5 - countCarga) + " Veiculos de Carga.");
        System.out.println("Deseja cadastrar um novo veiculo de Carga? Digite 1 para SIM ou 0 Para Não");
        var novoCadastro = leitura.nextInt();

        if (novoCadastro == 1) {
            cadastrarCarga();
        } else {
            exibeMenu();
        }

        if (opcao == 7) {
            System.out.println("Fim do programa");
            System.exit(0);
        }
        if (opcao != 7) {

            exibeMenu();
        }
    }

    private void listarPasseio() {
        System.out.println("Listando os veiculos de passeio cadastrados.");
        for (Passeio p : arrayPasseio) {
            System.out.println(p);
        }
    }

    private void listarCarga() {
        System.out.println("Listando os veiculos de carga");
        for (Carga c : arrayCarga) {
            System.out.println(c);
        }
    }

    private void imprimirPlacaPasseio() {
        System.out.println("Digite a placa do veiculo de passeio que deseja encontrar: ");
        var leitura = leituraClasse.scaneer;
        var placaPasseio = leitura.next();
        for (int i = 0; i < arrayPasseio.length; i++) {
            if (arrayPasseio[i] != null && arrayPasseio[i].getPlaca().equalsIgnoreCase(placaPasseio)) {
                
                System.out.println("PLACA ENCONTRADA!");
                System.out.println("Imprimindo modelo com placa compativel:");
                System.out.println(arrayPasseio[i]);
                break;
            }

            else {
                System.out.println("Placa NÃO encontrada!");
                System.out.println("Voltando ao Menu");
                break;
            }

        }
    }

    private void imprimirPlacaCarga() {
        System.out.println("Digite a placa do veiculo de CARGA que deseja encontrar: ");
        var leitura = leituraClasse.scaneer;
        var placaCarga = leitura.next();
        for (int i = 0; i < arrayCarga.length; i++) {
            if (arrayCarga[i] != null && arrayCarga[i].getPlaca().equalsIgnoreCase(placaCarga)) {
                
                System.out.println("PLACA ENCONTRADA!");
                System.out.println("Imprimindo modelo com placa compativel:");
                System.out.println(arrayCarga[i]);
                break;
            }

            else {
                System.out.println("Placa NÃo encontrada!!");
                System.out.println("Voltando ao Menu");
                break;

            }

        }
    }

    // Instanciando:
    public static void main(String[] args) {

        Teste teste = new Teste();
        teste.exibeMenu();
    }

}
