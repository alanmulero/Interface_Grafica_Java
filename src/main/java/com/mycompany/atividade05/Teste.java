/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.atividade05;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author alan toledo alan.mulero@gmail.com
 *
 * Atividade 7, dando continuidade na atividade 05. Implantando o tratamento de
 * Exceções.
 *
 */
public class Teste {

    private static JFrame inicial = new JFrame("Janela Inicial");
    private static JFrame passeio = new JFrame("Cadastro de Passeio");
    private static JFrame carga = new JFrame("Cadastro de Carga");
    private static JLabel optRotulo = new JLabel("Escolha um tipo de veiculo:  ");
    private static JLabel rotuloEscolha = new JLabel("Escolha uma opção abaixo:  ");
    private static BDVeiculos bdPasseio = new BDVeiculos();
    private static BDVeiculos bdCarga = new BDVeiculos();

    //ArrayList<String> placas = new ArrayList<>();
    Leitura leituraClasse = new Leitura();

    int opcao = -1;
    int countPasseio = 0;
    int countCarga = 0;
    boolean placaEncontrada = false;

    public void exibeMenu() throws VeiculoExistException, VelocException {

        while (opcao != 9) {

            var menu = """
            ###################### Escolha uma opcao abaixo ####################
                       
                       
            1 - Cadastrar Veículo de Passeio
            2 - Cadastrar Veículo de Carga
            3 - Imprimir Todos os Veículos de Passeio
            4 - Imprimir Todos os Veículos de Carga
            5 - Imprimir Veículo de Passeio pela Placa
            6 - Imprimir Veículo de Carga pela Placa
            7 - Excluir Veículo de Passeio pela Placa   
            8 - Excluir Veículo de Carga pela Placa  
            9 - Sair 
                       
                       
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
                    try {
                        cadastrarPasseio();
                        break;
                    } catch (VeiculoExistException ex) {
                        System.out.println("Lançando VeiculoExistException: " + ex.getMessage());
                        exibeMenu();
                        break;
                    }
                case 2:
                    try {
                        cadastrarCarga();
                        break;
                    } catch (VeiculoExistException ex) {
                        System.out.println("Lançando VeiculoExistException: " + ex.getMessage());
                        exibeMenu();
                        break;
                    }
                case 3:
                    bdPasseio.listarPasseio();
                    break;
                case 4:
                    bdCarga.listarCarga();
                    break;
                case 5:
                    bdPasseio.imprimirPlacaPasseio();
                    break;
                case 6:
                    bdCarga.imprimirPlacaCarga();
                    break;
                case 7:
                    bdPasseio.excluirPasseioPlaca();
                    break;
                case 8:
                    bdCarga.excluirCargaPlaca();
                    break;
                case 9:
                    System.out.println("Saindo do programa...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Digite numeros inteiros entre 0 e 9");
                    break;
            }

        }
    }

//    // Metodo para verificar placa
//    public String verificaPlaca(String testaPlaca) throws VeiculoExistException {
//
//        if (placas.contains(testaPlaca)) {
//            throw new VeiculoExistException(" Já existe um veículo com esta placa  ");
//        } else {
//            return testaPlaca;
//        }
//    }
    private void cadastrarPasseio() throws VeiculoExistException, VelocException {
        var leitura = leituraClasse.scaneer;
        System.out.println("Digite a placa do veiculo de passeio: ");
        var testaPlaca = leitura.next();

        var placa = bdPasseio.verificaPlaca(testaPlaca);

        System.out.println("Digite a marca do veiculo: ");
        var marca = leitura.next();
        leitura.nextLine();

        System.out.println("Digite o modelo do veiculo: ");
        var modelo = leitura.next();
        leitura.nextLine();
        System.out.println("Digite a cor do veiculo: ");
        var cor = leitura.nextLine();
        System.out.println("Digite a quantidade de passageiros: ");
        var qtdPassageiros = leitura.nextInt();

        System.out.println("Digite a velocidade maxima do veiculo: ");
        var velocidade = leitura.nextFloat();

        if (velocidade < 80 || velocidade > 110) {
            throw new VelocException("A Velocidade máxima está fora dos limites brasileiros");

        }

        if (velocidade > 100) {
            throw new VelocException("A Velocidade maxima permitida para veiculos de passeio é de 100KM");
        }

        System.out.println("Digite a quantidade de rodas do veiculo: ");
        var rodas = leitura.nextInt();
        System.out.println("Digite a quantidade de pistoes do veiculo: ");
        var pistao = leitura.nextInt();
        System.out.println("Digite a potencia do motor: ");
        var potencia = leitura.nextInt();
        System.out.println("*********************************************");

        bdPasseio.getArrayPasseio().add(new Passeio(qtdPassageiros, placa, marca, modelo, cor, velocidade, rodas, pistao, potencia));

        countPasseio += 1;
        bdPasseio.placas.add(placa);
        System.out.println("Voce ja registrou:  " + (countPasseio) + " Veiculos de passeio.");

        System.out.println("Deseja cadastrar um novo veiculo de passeio? Digite 1 para SIM ou 0 Para Não");
        var novoCadastro = leitura.nextInt();

        if (novoCadastro == 1) {
            cadastrarPasseio();
        } else {
            exibeMenu();
        }

        if (opcao == 9) {
            System.out.println("Fim do programa");
            System.exit(0);
        } else {
            exibeMenu();
        }

    }

    private void cadastrarCarga() throws VeiculoExistException, VelocException {
        var leitura = leituraClasse.scaneer;
        System.out.println("Digite a placa do veiculo de carga: ");
        var testaPlacaCarga = leitura.next();
        var placaCarga = bdCarga.verificaPlaca(testaPlacaCarga);

        System.out.println("Digite a marca do veiculo: ");
        var marcaCarga = leitura.next();

        System.out.println("Digite o modelo do veiculo: ");
        var modeloCarga = leitura.next();
        leitura.nextLine();
        System.out.println("Digite a cor do veiculo: ");
        var corCarga = leitura.nextLine();

        System.out.println("Digite a carga Maxima");
        var cargaMaxima = leitura.nextInt();
        leitura.nextLine();
        System.out.println("Digite a Tara: ");
        var tara = leitura.nextInt();
        System.out.println("Digite a velocidade maxima do veiculo: ");
        var velocidadeCarga = leitura.nextFloat();
        System.out.println("Digite a quantidade de rodas do veiculo: ");
        var rodasCarga = leitura.nextInt();
        System.out.println("Digite a quantidade de pistoes do veiculo: ");
        var pistaoCarga = leitura.nextInt();
        System.out.println("Digite a potencia do motor: ");
        var potenciaCarga = leitura.nextInt();
        System.out.println("*********************************************");

        bdCarga.getArrayCarga().add(new Carga(cargaMaxima, tara, placaCarga, marcaCarga, modeloCarga, corCarga, velocidadeCarga, rodasCarga, pistaoCarga, potenciaCarga));

        countCarga += 1;
        bdCarga.placas.add(placaCarga);

        System.out.println("Voce ja registrou:  " + (countCarga) + " Veiculos de Carga.");
        System.out.println("Deseja cadastrar um novo veiculo de Carga? Digite 1 para SIM ou 0 Para Não");
        var novoCadastro = leitura.nextInt();

        if (novoCadastro == 1) {
            cadastrarCarga();
        } else {
            exibeMenu();
        }

        if (opcao == 9) {
            System.out.println("Fim do programa");
            System.exit(0);
        }
        if (opcao != 7) {

            exibeMenu();
        }
    }

    // Instanciando:
    public static void main(String[] args) throws VeiculoExistException, VelocException {

//        Teste teste = new Teste();
//        teste.exibeMenu();
//        
        // Instanciando janelas

        inicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicial.setSize(300, 200);
        inicial.setLayout(null);
        inicial.add(optRotulo);
        
        // Botão de radio para escolher tipo de veiculo
     

        // Criando botões de rádio
        JRadioButton passeio = new JRadioButton("Veiculo Passeio");
        JRadioButton carga = new JRadioButton("Veiculo Carga");
        JRadioButton sair = new JRadioButton("Sair");
        // Posicionando
        passeio.setBounds(30, 20, 200, 20);
        carga.setBounds(30, 50, 200, 20);
        sair.setBounds(30, 70, 200, 20);

        // Agrupando os botões
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(passeio);
        grupo.add(carga);
        grupo.add(sair);
        
            // Janela 1
    class JanelaPasseio extends JFrame {
        public JanelaPasseio() {

            // Campos de texto
            JTextField campoPlaca = new JTextField();
            JTextField campoMarca = new JTextField();
            JTextField campoModelo = new JTextField();
            JTextField campoCor = new JTextField();
            JTextField campoQtdPassageiros = new JTextField();
            JTextField campoVelocidade = new JTextField();
            JTextField campoRodas = new JTextField();
            JTextField campoPistao = new JTextField();
            JTextField campoPotencia = new JTextField();

            // Adicionando os campos
            add(new JLabel("Placa:"));
            add(campoPlaca);
            add(new JLabel("Marca:"));
            add(campoMarca);
            add(new JLabel("Modelo:"));
            add(campoModelo);
            add(new JLabel("Cor:"));
            add(campoCor);
            add(new JLabel("Quantidade de Passageiros:"));
            add(campoQtdPassageiros);
            add(new JLabel("Velocidade Máxima:"));
            add(campoVelocidade);
            add(new JLabel("Rodas:"));
            add(campoRodas);
            add(new JLabel("Pistão:"));
            add(campoPistao);
            add(new JLabel("Potência:"));
            add(campoPotencia);
            

            // Botão
            JButton botaoCadastrar = new JButton("Cadastrar");

            add(botaoCadastrar);
            setTitle("Cadastro Passeio");
            setSize(200, 100);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new FlowLayout());

            campoPlaca.setPreferredSize(new Dimension(150, 25));
            campoMarca.setPreferredSize(new Dimension(150, 25));
            campoModelo.setPreferredSize(new Dimension(150, 25));
            campoCor.setPreferredSize(new Dimension(150, 25));
            campoQtdPassageiros.setPreferredSize(new Dimension(150, 25));
            campoVelocidade.setPreferredSize(new Dimension(150, 25));
            campoRodas.setPreferredSize(new Dimension(150, 25));
            campoPistao.setPreferredSize(new Dimension(150, 25));
            campoPotencia.setPreferredSize(new Dimension(150, 25));



            setVisible(true);

        }
    }

    // Janela 2
    class JanelaCarga extends JFrame {
        public JanelaCarga() {
            setTitle("Cadastro Carga");
            setSize(200, 100);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            add(new JLabel("Você abriu a Janela Carga"));
            setVisible(true);
        }
    }

    // sair
        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               System.exit(0);

            }
        });
       
        

        // Botão de confirmação
        JButton confirmar = new JButton("Abrir");
        confirmar.setBounds(100, 80, 80, 25);

        confirmar.addActionListener(e -> {
            if (passeio.isSelected()) {
                new JanelaPasseio();
            } else if (carga.isSelected()) {
                new JanelaCarga();
            }
        });

        // Adicionando componentes
        inicial.add(passeio);
        inicial.add(carga); // mudar aqui
        inicial.add(confirmar);
        inicial.add(sair);
              // Ajustando os posicionamentos
        inicial.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 15));
        inicial.setVisible(true);
        
    }

 
   

       
    }


