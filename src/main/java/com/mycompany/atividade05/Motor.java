/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atividade05;

/**
 *
 * @author alan
 */
public class Motor {
    
    private int qtdPistao;
    private int potencia;

    public Motor() {
        this.qtdPistao = 0;
        this.potencia = 0;
    }

    public Motor(int qtdPistao, int potencia) {
        this.qtdPistao = qtdPistao;
        this.potencia = potencia;
    }

    public int getQtdPistao() {
        return qtdPistao;
    }

   

    public final void setQtdpistao(int qtdpistao) {
        this.qtdPistao = qtdPistao;
    }

    public int getPotencia() {
        return potencia;
    }

    public final void setPotencia(int potencia) {
        this.potencia = potencia;
    }
    
    

    @Override
    public String toString() {
        return "Motor{" + "Quantidade de pistao do motor: = " + qtdPistao + ",Valor da  potencia = " + potencia + '}';
    }
    
}
