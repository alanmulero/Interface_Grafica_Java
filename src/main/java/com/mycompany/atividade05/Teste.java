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
 * @author alan toledo alan.mulero@gmail.com
 *         <p>
 *         Atividade 7, dando continuidade na atividade 05. Implantando o
 *         tratamento de Exceções.
 */
public class Teste {

	private static JFrame inicial = new JFrame("Janela Inicial");
	private static JFrame passeio = new JFrame("Cadastro de Passeio");
	private static JFrame listaPasseio = new JFrame("Lista de Passeio");
	private static JFrame listaCarga = new JFrame("Lista de Carga");
	private static JFrame carga = new JFrame("Cadastro de Carga");
	private static JLabel optRotulo = new JLabel("Escolha um tipo de veiculo:  ");
	private static JLabel rotuloEscolha = new JLabel("Escolha uma opção abaixo:  ");
	private static JLabel rotuloLista = new JLabel("Escolha uma opção para apresentar a Lista completa:  ");

	private static BDVeiculos bdPasseio = new BDVeiculos();
	private static BDVeiculos bdCarga = new BDVeiculos();

	static ArrayList<String> placas = new ArrayList<>();

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

	// Metodo para verificar placa
	public static String verificaPlaca(String testaPlaca) throws VeiculoExistException {

		if (placas.contains(testaPlaca)) {
			throw new VeiculoExistException(" Já existe um veículo com esta placa  ");
		} else {
			return testaPlaca;
		}
	}

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

		bdPasseio.getArrayPasseio()
				.add(new Passeio(qtdPassageiros, placa, marca, modelo, cor, velocidade, rodas, pistao, potencia));
		bdPasseio.getArrayPasseio().forEach(System.out::println);

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

		bdCarga.getArrayCarga().add(new Carga(cargaMaxima, tara, placaCarga, marcaCarga, modeloCarga, corCarga,
				velocidadeCarga, rodasCarga, pistaoCarga, potenciaCarga));

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
	// ***************************************************************************************************************************************************
	public static void main(String[] args) throws VeiculoExistException, VelocException {

//        Teste teste = new Teste();
//        teste.exibeMenu();
//        
		// Instanciando janelas

		inicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicial.setSize(300, 350);
		inicial.setLayout(null);
		inicial.add(optRotulo);

		// Botão de radio para escolher tipo de veiculo

		// Criando botões de rádio
		JRadioButton passeio = new JRadioButton("Veiculo Passeio");
		JRadioButton carga = new JRadioButton("Veiculo Carga");
		JRadioButton listaPasseio = new JRadioButton("Listar/Excluir Veiculo Passeio");
		JRadioButton listaCarga = new JRadioButton("Listar/Excluir Veiculo Carga");


		JButton sair = new JButton("Sair");
		// Posicionando
		passeio.setBounds(30, 20, 200, 20);
		carga.setBounds(30, 50, 200, 20);
		listaPasseio.setBounds(30, 50, 200, 20);
		listaCarga.setBounds(30, 50, 200, 20);
		sair.setBounds(30, 70, 200, 20);

		// Agrupando os botões
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(passeio);
		grupo.add(carga);
		grupo.add(sair);
		grupo.add(listaPasseio);
		grupo.add(listaCarga);

		//                                  Janela 1  Passeio
		// *********************************************************************************
		// Veiculo Passeio
		class JanelaPasseio extends JFrame {

			public JanelaPasseio() {

				// Campos de texto
				JTextField campoPlaca = new JTextField(8);
				JTextField consultaPlaca = new JTextField(8);
				JTextField campoMarca = new JTextField(8);
				JTextField campoModelo = new JTextField(8);
				JTextField campoCor = new JTextField(8);
				JTextField campoQtdPassageiros = new JTextField(8);
				JTextField campoVelocidade = new JTextField(8);
				JTextField campoRodas = new JTextField(8);
				JTextField campoPistao = new JTextField(8);
				JTextField campoPotencia = new JTextField(8);
				JTextField excluirPlaca = new JTextField(8);
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

				add(excluirPlaca);
				JButton botaoCadastrar = new JButton("Cadastrar");
				JButton limparCampos = new JButton("Limpar Campos");
				JButton botaoFechar = new JButton("Fechar Janela");
				JButton BotaoConsultarPlaca = new JButton("Consulta Placa");
				JButton excluirPorPlaca = new JButton("Excluir Placa");

				botaoCadastrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						// Cadastrando
						
						try {
							String placa = campoPlaca.getText();

							if (placas.contains(placa)) {
								throw new VeiculoExistException(" Já existe um veículo com esta placa  ");
							}
							if (placa.isEmpty()) {
								JOptionPane.showMessageDialog(null, "O campo PLACA não pode ficar em branco.",
										"Erro de validação", JOptionPane.WARNING_MESSAGE);
								campoPlaca.requestFocus();
								return;
							} else {
								placas.add(placa);
							}
							String marca = campoMarca.getText();
							String modelo = campoModelo.getText();
							String cor = campoCor.getText();

							int qtdPassageiros = Integer.parseInt(campoQtdPassageiros.getText());
							float velocidade = Float.parseFloat(campoVelocidade.getText());
							if (velocidade < 80 || velocidade > 110) {
								throw new VelocException(
										"A Velocidade Minima ou  Máxima está fora dos limites brasileiros");
							}
							if (velocidade > 100) {
								throw new VelocException(
										"A Velocidade maxima permitida para veiculos de passeio é de 100KM");
							}
							int rodas = Integer.parseInt(campoRodas.getText());
							int pistao = Integer.parseInt(campoPistao.getText());
							int potencia = Integer.parseInt(campoPotencia.getText());
							bdPasseio.getArrayPasseio().add(new Passeio(qtdPassageiros, placa, marca, modelo, cor,
									velocidade, rodas, pistao, potencia));
							JOptionPane.showMessageDialog(null, "Veiculo cadastrado com sucesso!");

							bdPasseio.getArrayPasseio().forEach(System.out::println);
							campoPlaca.requestFocus();
							placas.add(placa);
						} catch (NumberFormatException | VeiculoExistException | VelocException ex) {
							JOptionPane.showMessageDialog(null,
									"Erro de VeiculoExistException ou Entrada de daos inconpativél : "
											+ ex.getMessage());
							return;
						}

					}
				});

				// sets da janela **********************************8
				setExtendedState(JFrame.MAXIMIZED_BOTH);
				//setSize(900, 400);
				add(botaoCadastrar);
				add(limparCampos);
				add(botaoFechar);
				add(new JLabel(" Consulta Placa:"));
				add(consultaPlaca);
				add(BotaoConsultarPlaca);
				add(new JLabel("Excluir por placa:"));
				add(excluirPlaca);
				add(excluirPorPlaca);

				setTitle("Cadastro Passeio");
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
				consultaPlaca.setPreferredSize(new Dimension(150, 25));

				// Botão para limpar campos
				limparCampos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						campoPlaca.setText("");
						campoMarca.setText("");
						campoModelo.setText("");
						campoCor.setText("");
						campoQtdPassageiros.setText("");
						campoVelocidade.setText("");
						campoRodas.setText("");
						campoPistao.setText("");
						campoPotencia.setText("");
						consultaPlaca.setText("");
						excluirPlaca.setText("");

					}
				});

				// fechar janela

				botaoFechar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JanelaPasseio.this.dispose();
					}
				});

				// Consultar placa

				BotaoConsultarPlaca.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String consPlaca = consultaPlaca.getText();
						Passeio passeio = new Passeio();
						for (int i = 0; i < bdPasseio.getArrayPasseio().size(); i++)
							passeio = (Passeio) bdPasseio.getArrayPasseio().get(i);
						if (passeio.getPlaca().equalsIgnoreCase(consPlaca)) {
							JOptionPane.showMessageDialog(null, "Placa encontrada", "Placa", 1);
							JOptionPane.showMessageDialog(null, passeio.toString());

						} else {
							JOptionPane.showMessageDialog(null, "Placa não encontrada", "Placa", 0);
						}
						setVisible(true);
					}
				});
				
				
				
				// Listar/Excluir todos os veiculos de passeio  ********************************************************888
				
				
				
				
				
				
				
				
				
				
				
				
				
				

				// Excluir por placa  Passeio

				excluirPorPlaca.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String consPlaca = excluirPlaca.getText();
						Passeio passeio = new Passeio();
						for (int i = 0; i < bdPasseio.getArrayPasseio().size(); i++)
							passeio = (Passeio) bdPasseio.getArrayPasseio().get(i);
						if (passeio.getPlaca().equalsIgnoreCase(consPlaca)) {
							JOptionPane.showMessageDialog(null, "Veiculo correspondente que vai ser excluido.", "Placa",
									1);
							JOptionPane.showMessageDialog(null, passeio.toString());
							bdPasseio.getArrayPasseio().remove(passeio);
							JOptionPane.showMessageDialog(null, "Veiculo excluido com sucesso!", "Placa", 1);
						} else {
							JOptionPane.showMessageDialog(null, "Placa não encontrada", "Placa", 0);
						}

						setVisible(true);
					}
				});

				setVisible(true);

			}
		}

		// Janela 2 Carga
		// ###################################################################################################################3
		class JanelaCarga extends JFrame {
			public JanelaCarga() {

				JTextField campoPlaca = new JTextField(8);
				JTextField campoMarca = new JTextField(8);
				JTextField campoModelo = new JTextField(8);
				JTextField campoCor = new JTextField(8);
				JTextField campoCargaMaxima = new JTextField(8);
				JTextField campoTara = new JTextField(8);
				JTextField campoVelocidade = new JTextField(8);
				JTextField campoRodas = new JTextField(8);
				JTextField campoPistao = new JTextField(8);
				JTextField campoPotencia = new JTextField(8);
				JTextField consultaPlacaCarga = new JTextField(8);
				JTextField excluirPlaca = new JTextField(8);

				// Adicionando os campos
				add(new JLabel("Placa:"));
				add(campoPlaca);
				add(new JLabel("Marca:"));
				add(campoMarca);
				add(new JLabel("Modelo:"));
				add(campoModelo);
				add(new JLabel("Cor:"));
				add(campoCor);
				add(new JLabel("CargaMaxima"));
				add(campoCargaMaxima);
				add(new JLabel("Tara"));
				add(campoTara);
				add(new JLabel("Velocidade Máxima:"));
				add(campoVelocidade);
				add(new JLabel("Rodas:"));
				add(campoRodas);
				add(new JLabel("Pistão:"));
				add(campoPistao);
				add(new JLabel("Potência:"));
				add(campoPotencia);
				add(excluirPlaca);

				// Botão
				JButton botaoCadastrarCarga = new JButton("Cadastrar Carga");
				JButton limparCampos = new JButton("Limpar Campos");
				JButton botaoFechar = new JButton("Fechar Janela");
				JButton BotaoConsultarPlacaCarga = new JButton("Consulta Placa");
				JButton excluirPorPlaca = new JButton("Excluir Placa");

				botaoCadastrarCarga.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						// Cadastrando
						try {
							String placa = campoPlaca.getText();

							if (placas.contains(placa)) {
								throw new VeiculoExistException(" Já existe um veículo com esta placa  ");
							}
							if (placa.isEmpty()) {
								JOptionPane.showMessageDialog(null, "O campo PLACA não pode ficar em branco.",
										"Erro de validação", JOptionPane.WARNING_MESSAGE);
								campoPlaca.requestFocus();
								return;
							} else {
								placas.add(placa);
							}
							String marca = campoMarca.getText();
							String modelo = campoModelo.getText();
							String cor = campoCor.getText();

							float velocidade = Float.parseFloat(campoVelocidade.getText());
							if (velocidade < 80 || velocidade > 110) {
								throw new VelocException(
										"A Velocidade Minima ou  Máxima está fora dos limites brasileiros");
							}
							if (velocidade > 90) {
								throw new VelocException(
										"A Velocidade maxima permitida para veiculos de Carga é de 90KM");
							}
							int rodas = Integer.parseInt(campoRodas.getText());
							int pistao = Integer.parseInt(campoPistao.getText());
							int potencia = Integer.parseInt(campoPotencia.getText());
							int carga = Integer.parseInt(campoCargaMaxima.getText());
							int tara = Integer.parseInt(campoTara.getText());
							bdCarga.getArrayCarga().add(new Carga(carga, tara, placa, marca, modelo, cor, velocidade,
									rodas, pistao, potencia));
							JOptionPane.showMessageDialog(null, "Veiculo cadastrado com sucesso!");

							bdCarga.getArrayCarga().forEach(System.out::println);
							campoPlaca.requestFocus();

						} catch (NumberFormatException | VeiculoExistException | VelocException ex) {
							JOptionPane.showMessageDialog(null,
									"Erro de VeiculoExistException ou Entrada de daos inconpativél : "
											+ ex.getMessage());
							return;
						}

					}
				});

				// Metodo para consulta da placa
				// Carga********************************************

				BotaoConsultarPlacaCarga.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String consPlacaCarga = consultaPlacaCarga.getText();
						Carga carga = new Carga();

						for (int i = 0; i < bdCarga.getArrayCarga().size(); i++)
							carga = (Carga) bdCarga.getArrayCarga().get(i);

						if (carga.getPlaca().equalsIgnoreCase(consPlacaCarga)) {

							JOptionPane.showMessageDialog(null, "Placa encontrada", "Placa", 1);
							JOptionPane.showMessageDialog(null, carga.toString());

						} else {
							JOptionPane.showMessageDialog(null, "Placa não encontrada", "carga", 0);
						}

						setVisible(true);
					}
				});
				
				
				// Excluir por placa
				
				
				
				
				
				excluirPorPlaca.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String consPlacaCarga = excluirPlaca.getText();
						Carga carga = new Carga();

						for (int i = 0; i < bdCarga.getArrayCarga().size(); i++)
							carga = (Carga) bdCarga.getArrayCarga().get(i);

						if (carga.getPlaca().equalsIgnoreCase(consPlacaCarga)) {
							JOptionPane.showMessageDialog(null, "Veiculo correspondente que vai ser excluido.", "Placa",
									1);
							JOptionPane.showMessageDialog(null, carga.toString());
							bdCarga.getArrayCarga().remove(carga);
							JOptionPane.showMessageDialog(null, "Veiculo excluido com sucesso!", "Placa", 1);
						} else {
							JOptionPane.showMessageDialog(null, "Placa não encontrada", "carga", 0);
						}

						setVisible(true);
					}
				});

				// sets da janela
				setExtendedState(JFrame.MAXIMIZED_BOTH);
				//setSize(700, 300);
				add(botaoCadastrarCarga);
				add(limparCampos);
				add(botaoFechar);
				add(new JLabel(" Consulta Placa:"));
				add(consultaPlacaCarga);
				add(BotaoConsultarPlacaCarga);
				add(new JLabel("Excluir por placa:"));
				add(excluirPlaca);
				add(excluirPorPlaca);
				setTitle("Cadastro Carga");
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setLayout(new FlowLayout());

				campoPlaca.setPreferredSize(new Dimension(150, 25));
				campoMarca.setPreferredSize(new Dimension(150, 25));
				campoModelo.setPreferredSize(new Dimension(150, 25));
				campoCor.setPreferredSize(new Dimension(150, 25));
				campoCargaMaxima.setPreferredSize(new Dimension(150, 25));
				campoTara.setPreferredSize(new Dimension(150, 25));
				campoVelocidade.setPreferredSize(new Dimension(150, 25));
				campoRodas.setPreferredSize(new Dimension(150, 25));
				campoPistao.setPreferredSize(new Dimension(150, 25));
				campoPotencia.setPreferredSize(new Dimension(150, 25));

				// Botão para limpar campos
				limparCampos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						campoPlaca.setText("");
						campoMarca.setText("");
						campoModelo.setText("");
						campoCor.setText("");
						campoCargaMaxima.setText("");
						campoTara.setText("");
						campoVelocidade.setText("");
						campoRodas.setText("");
						campoPistao.setText("");
						campoPotencia.setText("");

					}
				});

				// botão sair
				botaoFechar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JanelaCarga.this.dispose();
					}
				});

				setVisible(true);
			}
		}

		// sair
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		
		//*************************************************************************************************

		// Botão de confirmação
		JButton confirmar = new JButton("Abrir");
		confirmar.setBounds(100, 80, 80, 25);

		confirmar.addActionListener(e -> {
			if (passeio.isSelected()) {
				new JanelaPasseio();
			} else if (carga.isSelected()) {
				new JanelaCarga();
			}else if (listaPasseio.isSelected()) {
				bdPasseio.listarPasseio();
			} else if (listaCarga.isSelected()) {
				bdCarga.listarCarga();
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma opção válida.");
			}
		});

		// Adicionando componentes e rotulos
		inicial.add(passeio);
		inicial.add(carga); 
		inicial.add(rotuloEscolha);
		inicial.add(listaPasseio);
		inicial.add(listaCarga);
		inicial.add(confirmar);
		inicial.add(sair);

		// Ajustando os posicionamentos
		// inicial.setLayout(new GridLayout(4, 4));
		inicial.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 15));
		inicial.setVisible(true);

	}

}
