package br.cefetrj.trabalhoFinal.servico;

import java.util.List;

public class Teste {
	
//	Classe que testa os métodos de compactação e descompactação, além de gerar uma tabela com símbolos, repetições e o código Huffman

	public static void main(String[] args) {
		
        String palavra = "aaabbbc"; 
        
        //256 tipos de caracteres diferentes
        int[] frequencia_caracteres = new int[256];
        for (char c : palavra.toCharArray())
        	frequencia_caracteres[c]++;
        
        AlgoritmoHuffman hf = new AlgoritmoHuffman();
        
        Arvore arvore = hf.construtorArvore(frequencia_caracteres);
        
        System.out.println("ALGORITMO DE HUFFMAN\n");
        System.out.println("TABELA DE SIMBOLOS E CÓDIGOS");
        
        System.out.println("Símbolos\tRepetições\tCódigo");
        
        //String impressao = hf.getCodigos(arvore, new StringBuffer());
        //System.out.println(impressao);
                
        System.out.println("\nTEXTO COMPACTADO");
        String codificar = hf.codificar(arvore,palavra);
        System.out.println(codificar);
        
        System.out.println("\n\nTEXTO DECODIFICADO");
        String decodificar = hf.decodificar(arvore,codificar);
        System.out.println(decodificar);
        
        System.out.println("\nImpressão de Códigos:");
        String codigos = hf.imprimir(arvore,palavra);
        System.out.println(codigos);
        
        System.out.println("\nImpressão de Lista:");
        List<String> codigos_lista = hf.getLista(arvore,palavra);
        System.out.println(codigos_lista);

    }

}
