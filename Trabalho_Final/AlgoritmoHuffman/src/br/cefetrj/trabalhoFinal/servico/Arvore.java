package br.cefetrj.trabalhoFinal.servico;
/*
 * Classe Abstrata para a classe concreta N� e Folha
 */
public abstract class Arvore implements Comparable<Arvore> {
    public final int frequencia;
    //
    public Arvore(int freq) { 
    	frequencia = freq; 
    }
    
    // Compara��o de Frequ�ncias
    public int compareTo(Arvore arvore) {
        return frequencia - arvore.frequencia;
    }
}