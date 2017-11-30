package br.cefetrj.trabalhoFinal.servico;
/*
 * Nó da árvore
 */
public class No extends Arvore {
    public final Arvore esquerda, direita; // sub-árvores
 
    public No(Arvore e, Arvore d) {
        super(e.frequencia + d.frequencia);
        esquerda = e;
        direita = d;
    }
}