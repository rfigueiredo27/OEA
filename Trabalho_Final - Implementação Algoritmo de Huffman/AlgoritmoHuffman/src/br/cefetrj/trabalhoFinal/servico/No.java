package br.cefetrj.trabalhoFinal.servico;
/*
 * N� da �rvore
 */
public class No extends Arvore {
    public final Arvore esquerda, direita; // sub-�rvores
 
    public No(Arvore e, Arvore d) {
        super(e.frequencia + d.frequencia);
        esquerda = e;
        direita = d;
    }
}