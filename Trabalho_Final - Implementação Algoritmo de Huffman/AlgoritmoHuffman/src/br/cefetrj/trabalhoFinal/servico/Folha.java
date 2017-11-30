package br.cefetrj.trabalhoFinal.servico;
/*
 * Classe do n� folha da �rvore 
 */
public class Folha extends Arvore {
    public final char valor; // A letra � atribuida a um n� folha 
 
    public Folha(int freq, char val) {
        super(freq);
        valor = val;
    }
}