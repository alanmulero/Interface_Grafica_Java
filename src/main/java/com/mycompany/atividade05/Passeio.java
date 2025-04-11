/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atividade05;

import java.text.DecimalFormat;

/**
 *
 * @author alan
 */
public final class Passeio extends Veiculo implements Calcular {

    private int qtdPassageiros;
    
    public Passeio(int qtdPassageiros) {
        this.qtdPassageiros = 0;
    }

    public Passeio(int qtdPassageiros, String placa, String marca, String modelo, String cor, float velocMax, int qtdRodas, int qtdPistao, int potencia) {
        super(placa, marca, modelo, cor, velocMax, qtdRodas, qtdPistao, potencia);
        this.qtdPassageiros = qtdPassageiros;
        this.calcVel(velocMax);
        System.out.println("Quantidade total de letra:  "+this.calcular());
        

    }

    public int getQtdPassageiros() {
        return qtdPassageiros;
    }

    public final void setQtdPassageiros(int qtdPassageiros) {
        this.qtdPassageiros = qtdPassageiros;
    }

    @Override
    public final float calcVel(float veloMax) {
        System.out.println("************************************************************************************");
        System.out.println("Velocidade do veiculo de PASSEIO: ==> " + this.getMarca() + " Convertida para metros por hora:  " + veloMax * 1000);
        return veloMax * 1000;
    }

    @Override
    public String toString() {
        return "Passeio{" + 
                "Quantidade de passageiros: " + qtdPassageiros + "\n"
                + "Placa " + super.getPlaca() + "\n"
                + "Marca: " + super.getMarca() + "\n"
                + "Modelo: " + super.getModelo() + "\n"
                + "Cor: " + super.getCor() + "\n"
                + "Velocidade: " + super.getVelocMax() +  "==> Convertida para metros por hora:  " + (getVelocMax() * 1000) +"\n"
                + "Quantidade de Rodas: " + super.getQtdRodas() + "\n"
                + "Quantidade de pistoes: " + super.getMotor().getQtdPistao() + "\n"
                + "Potencia do motor: " + super.getMotor().getPotencia()+"\n"
                 + "Metodo calcular Somando Letras: " + this.calcular()+"\n"
                + "*******************************************"
                
                + '}';
    }

    @Override
    public final int calcular() {
        int somaLetra = 0;
        
        somaLetra += getPlaca().length();
        somaLetra += getMarca().length();
        somaLetra += getModelo().length();
        somaLetra += getCor().length();
        return somaLetra;
            
    }

}
