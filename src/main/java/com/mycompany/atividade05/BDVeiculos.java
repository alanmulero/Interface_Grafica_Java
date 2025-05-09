/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atividade05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author alan
 */
public class BDVeiculos {

    private List<Passeio> arrayPasseio = new ArrayList<>();
    private List<Carga> arrayCarga = new ArrayList<>();
    ArrayList<String> placas = new ArrayList<>();
    Scanner leitura = new Scanner(System.in);

    public List getArrayPasseio() {
        return arrayPasseio;
    }

    public void setArrayPasseio(List arrayPasseio) {
        this.arrayPasseio = arrayPasseio;
    }

    public List getArrayCarga() {
        return arrayCarga;
    }

    public void setArrayCarga(List arrayCarga) {
        this.arrayCarga = arrayCarga;
    }

// Métodos para manipular a List
    
       // Metodo para verificar placa
    public String verificaPlaca(String testaPlaca) throws VeiculoExistException {

        if (placas.contains(testaPlaca)) {
            throw new VeiculoExistException(" Já existe um veículo com esta placa  ");
        } else {
            return testaPlaca;
        }
    }
    
    
    
    
    // Listar todos passeio
    public void listarPasseio() {
        System.out.println("Listando os veiculos de passeio cadastrados.");
        for (int i = 0; i < arrayPasseio.size(); i++) {
            System.out.println(arrayPasseio.get(i));
        }
    }

    //lista todos carga
    public void listarCarga() {
        System.out.println("Listando os veiculos de carga");
        for (int i = 0; i < arrayCarga.size(); i++) {
            System.out.println(arrayCarga.get(i));
        }
    }

    // Imprime placa Passeio
    public void imprimirPlacaPasseio() {
        System.out.println("Digite a placa do veiculo de passeio que deseja encontrar: ");
        var placaPasseio = leitura.nextLine();
        for (int i = 0; i < arrayPasseio.size(); i++) {
            if (arrayPasseio.get(i).getPlaca().equalsIgnoreCase(placaPasseio)) {

                System.out.println("PLACA ENCONTRADA!");
                System.out.println("Imprimindo modelo com placa compativel:");
                System.out.println(arrayPasseio.get(i));
                break;
            } else {
                System.out.println("Placa NÃO encontrada!");
                System.out.println("Voltando ao Menu");
                break;
            }

        }
    }

    // Imprimir placa Carga
    public void imprimirPlacaCarga() {
        System.out.println("Digite a placa do veiculo de Carga que deseja encontrar: ");
        var placaCarga = leitura.nextLine();
        for (int i = 0; i < arrayCarga.size(); i++) {
            if (arrayCarga.get(i).getPlaca().equalsIgnoreCase(placaCarga)) {

                System.out.println("PLACA DE CARGA ENCONTRADA!");
                System.out.println("Imprimindo modelo com placa compativel:");
                System.out.println(arrayCarga.get(i));
                break;
            } else {
                System.out.println("Placa NÃO encontrada!");
                System.out.println("Voltando ao Menu");
                break;
            }

        }
    }

    void excluirCargaPlaca() {
        System.out.println("Digite a placa do veiculo de Carga que deseja EXCLUIR: ");
        var placaCarga = leitura.next();
        boolean placaEncontrada = false;
        for (int i = 0; i < arrayCarga.size(); i++) {
            if (arrayCarga.get(i).getPlaca().equalsIgnoreCase(placaCarga)) {

                System.out.println("PLACA DE CARGA ENCONTRADA no indice: " + i);
                System.out.println("EXCLUINDO modelo com placa compativel:");
                arrayCarga.remove(i);
                placas.remove(i);
                placaEncontrada = true;

            }
            if (!placaEncontrada) {
                System.out.println("Placa NÃO encontrada no indece: " + i);

            }

        }
        System.out.println("Voltando ao Menu");
    }

    void excluirPasseioPlaca() {

        System.out.println("Digite a placa do veiculo de Passeio que deseja EXCLUIR: ");
        var placaPasseio = leitura.next();
        boolean placaEncontrada = false;
        for (int i = 0; i < arrayPasseio.size(); i++) {
            if (arrayPasseio.get(i).getPlaca().equalsIgnoreCase(placaPasseio)) {

                System.out.println("PLACA DE Passeio ENCONTRADA no indice:  " + i);
                System.out.println("EXCLUINDO modelo com placa compativel:");
                arrayPasseio.remove(i);
                placas.remove(i);
                placaEncontrada = true;

            }
            if (!placaEncontrada) {
                System.out.println("Placa NÃO encontrada no indice: " + i);

            }

        }
        System.out.println("Voltando ao Menu");

    }

}
