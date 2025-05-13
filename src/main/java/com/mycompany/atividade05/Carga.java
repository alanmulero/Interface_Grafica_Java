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
public final class Carga extends Veiculo implements Calcular {

    private int cargaMax;
    private int tara;
    DecimalFormat df = new DecimalFormat("#");
    public Carga(int cargaMax, int tara) {
        this.cargaMax = 0;
        this.tara = 0;
    }
    
   


    public Carga(int cargaMax, int tara, String placa, String marca, String modelo, String cor, float velocMax, int qtdRodas, int qtdPistao, int potencia) {
        super(placa, marca, modelo, cor, velocMax, qtdRodas, qtdPistao, potencia);
        this.cargaMax = cargaMax;
        this.tara = tara;
        this.calcVel(velocMax);
        System.out.println("Quantidade total de soma dos inteiros:  "+this.calcular());
    }
    
    public Carga() {}

    public Carga(String placa, String marca, String modelo, String cor, int carga, int tara, float velocidade, int rodas, int qtdPistao, int potencia) {
    }


    public int getCargaMax() {
        return cargaMax;
    }

    public final void setCargaMax(int cargaMax) {
        this.cargaMax = cargaMax;
    }

    public int getTara() {
        return tara;
    }

    public final void setTara(int tara) {
        this.tara = tara;
    }

    @Override
    public final  float calcVel(float veloMax) {
        System.out.println("************************************************************************************");
        
        System.out.println("Velocidade do veiculo de CARGA: ==>" + this.getMarca() + " Convertida para CENTIMETROS por hora:  " + df.format(veloMax * 100000));
        return veloMax * 100000;
    }

    @Override
    public String toString() {
        return "Carga{" + 
                "Carga Maxima: " + cargaMax + "\n"
                + "Tara: " + tara + "\n"
                + "Placa " + super.getPlaca() + "\n"
                + "Marca: " + super.getMarca() + "\n"
                + "Modelo: " + super.getModelo() + "\n"
                + "Cor: " + super.getCor() + "\n"
                + "Velocidade: " + super.getVelocMax() + "  Convertida para CENTIMETROS por hora:  " + df.format(getVelocMax() * 100000)+ "\n"
                + "Quantidade de Rodas: " + super.getQtdRodas() + "\n"
                + "Quantidade de pistoes: "  + super.getMotor().getPistao()+ "\n"
                + "Potencia do motor: " + super.getMotor().getPotencia()+"\n"
                + "Metodo calcular Somando inteiros: " + this.calcular()+"\n"
                + "*******************************************"
                + '}';

    }

    @Override
    public final int calcular() {
        int somaNum = 0;
        
        somaNum += getMotor().getPotencia();
        somaNum += getMotor().getPistao();
        somaNum += getQtdRodas();
        somaNum += getVelocMax();
        somaNum += getTara();
        somaNum += getCargaMax();
        System.out.println(somaNum);
        return somaNum;
    }

}
