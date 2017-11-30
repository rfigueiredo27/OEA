package br.cefetrj.trabalhoFinal.servico;
/*
 * Classe do nó folha da Árvore 
 */
public class Folha extends Arvore {
    public final char valor; // A letra é atribuida a um nó folha 
 
    public Folha(int freq, char val) {
        super(freq);
        valor = val;
    }
}