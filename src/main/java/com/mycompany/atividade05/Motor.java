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
    
    private int pistao;
    private int potencia;

    public Motor() {
        this.pistao = 0;
        this.potencia = 0;
    }

    public Motor(int pistaoistao, int potencia) {
        this.pistao = pistao;
        this.potencia = potencia;
    }

    public int getPistao() {
        return pistao;
    }

   

    public final void setQtdpistao(int qtdpistao) {
        this.pistao = pistao;
    }

    public int getPotencia() {
        return potencia;
    }

    public final void setPotencia(int potencia) {
        this.potencia = potencia;
    }
    
    

    @Override
    public String toString() {
        return "Motor{" + "Quantidade de pistao do motor: = " + pistao + ",Valor da  potencia = " + potencia + '}';
    }
    
}
