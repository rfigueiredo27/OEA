package br.cefetrj.trabalhoFinal.servico;
/*
 * Classe Abstrata para a classe concreta Nó e Folha
 */
public abstract class Arvore implements Comparable<Arvore> {
    public final int frequencia;
    //
    public Arvore(int freq) { 
    	frequencia = freq; 
    }
    
    // Comparação de Frequências
    public int compareTo(Arvore arvore) {
        return frequencia - arvore.frequencia;
    }
}