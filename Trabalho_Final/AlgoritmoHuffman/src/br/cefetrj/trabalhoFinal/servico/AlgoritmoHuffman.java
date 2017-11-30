package br.cefetrj.trabalhoFinal.servico;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

import br.cefetrj.trabalhoFinal.entidade.Huffman;

public class AlgoritmoHuffman {
	/*
	 * Implementação do Algoritmo de Huffman
	 * 1º Constrói a árvore binária
	 * 2º Efetua a codificação dos símbolos
	 * 3º Efetua a decodificação
	 * 4º Imprime a tabela de códigos
	 * 5º Gera os códigos codificados e decodificados
	 */
	public Arvore construtorArvore(int[] frequencia_caracteres) {
		//fila de prioridade
		PriorityQueue<Arvore> arvores = new PriorityQueue<Arvore>();
		for (int i = 0; i < frequencia_caracteres.length; i++){
			if (frequencia_caracteres[i] > 0)
				arvores.offer(new Folha(frequencia_caracteres[i], (char)i)); 
		}
		while (arvores.size() > 1) {
			Arvore a = arvores.poll(); 
			Arvore b = arvores.poll(); 
			arvores.offer(new No(a, b)); 
		}
		return arvores.poll();
	}

	public String codificar(Arvore arvore, String codificar){
		assert arvore != null;

		String codificarTexto = "";
		for (char c : codificar.toCharArray()){
			codificarTexto+=(getCodes(arvore, new StringBuffer(),c));
		}
		return codificarTexto; 
	}
	

	public String decodificar(Arvore arvore, String codificar) {
		assert arvore != null;

		String decodificarTexto="";
		No no = (No)arvore;
		for (char code : codificar.toCharArray()){
			if (code == '0'){ // Pra eu lembrar: Começa sempre do lado esquerdo
				if (no.esquerda instanceof Folha) { 
					decodificarTexto += ((Folha)no.esquerda).valor;  
					no = (No)arvore;
				}else{
					no = (No) no.esquerda; 
				}
			}else if (code == '1'){
				if (no.direita instanceof Folha) {
					decodificarTexto += ((Folha)no.direita).valor;
					no = (No)arvore; 
				}else{
					no = (No) no.direita; // Continua do lado direito
				}
			}
		} 
		return decodificarTexto; 
	}
	
	public String imprimir(Arvore arvore, String codificar){
		assert arvore != null;
		String imprimirTexto = "";
		for (char c : codificar.toCharArray()){
		imprimirTexto+=(getCodigos(arvore, new StringBuffer(),c));
		}
		return imprimirTexto; 
	}
	
	public List<String> getLista(Arvore arvore, String codificar) {
		assert arvore != null;
		String imprimirTexto = "";
		boolean repetido = false;
		ArrayList<String> lista = new ArrayList<String>();
			for (char c : codificar.toCharArray()){
			imprimirTexto = (getCodigos2(arvore, new StringBuffer(), c));
			if(!(lista.contains(imprimirTexto)))
			lista.add(imprimirTexto);
			}
		return lista; 
	}
	
	public List<Huffman> getLista2(Arvore arvore, String codificar) {
		assert arvore != null;
		String imprimirTexto = "";
		String simbolo;
		String repeticoes = "";
		String codigo = "";
		ArrayList<Huffman> lista = new ArrayList<Huffman>();
			for (char c : codificar.toCharArray()){
			Huffman huffman = new Huffman();
			imprimirTexto = (getCodigos2(arvore, new StringBuffer(), c));
			String[] texto = imprimirTexto.split("_");
			simbolo = texto[0];
			repeticoes = texto[1];
			codigo = texto[2];
			huffman.setCodigo(codigo);
			huffman.setRepeticoes(repeticoes);
			huffman.setSimbolos(simbolo);
			if(!(lista.contains(huffman.getCodigo())))
			lista.add(huffman);
			if((lista.contains(huffman)));
			lista.remove(huffman);
			}
			
		return lista; 
	}
	
	public String getCodigos(Arvore arvore, StringBuffer codigo, char l) {
		assert arvore != null;
		String resultado = "";
		if (arvore instanceof Folha) {
			Folha folha = (Folha)arvore;
			if (folha.valor == l){
				char simbolo = folha.valor;
				int repeticoes = folha.frequencia;
				resultado += simbolo+"\t\t" + repeticoes+"\t\t"+ codigo.toString() + "\n";
				//System.out.println(resultado);
				return resultado;
			}
		} else if (arvore instanceof No) {
			No no = (No)arvore;
			codigo.append('0');
			String esquerda = getCodigos(no.esquerda, codigo, l);
			codigo.deleteCharAt(codigo.length()-1);
			codigo.append('1');
			String direita = getCodigos(no.direita, codigo, l);
			codigo.deleteCharAt(codigo.length()-1);
			if (esquerda==null) return direita; else return esquerda;
		}
		return null;
	}
	
	public String getCodigos2(Arvore arvore, StringBuffer codigo, char l) {
		assert arvore != null;
		String resultado = "";
		if (arvore instanceof Folha) {
			Folha folha = (Folha)arvore;
			if (folha.valor == l){
				char simbolo = folha.valor;
				int repeticoes = folha.frequencia;
				resultado += simbolo+ "_" + repeticoes+"_"+ codigo.toString();
				return resultado;
			}
		} else if (arvore instanceof No) {
			No no = (No)arvore;
			codigo.append('0');
			String esquerda = getCodigos2(no.esquerda, codigo, l);
			codigo.deleteCharAt(codigo.length()-1);
			codigo.append('1');
			String direita = getCodigos2(no.direita, codigo, l);
			codigo.deleteCharAt(codigo.length()-1);
			if (esquerda==null) return direita; else return esquerda;
		}
		return null;
	}

	public static String getCodes(Arvore arvore, StringBuffer codigo, char l) {
		assert arvore != null;

		if (arvore instanceof Folha) {
			Folha folha = (Folha)arvore;

			if (folha.valor == l ){
				return codigo.toString() + "\n";
				
			}

		} else if (arvore instanceof No) {
			No no = (No)arvore;

			codigo.append('0');
			String esquerda = getCodes(no.esquerda, codigo, l);
			codigo.deleteCharAt(codigo.length()-1);

			codigo.append('1');
			String direita = getCodes(no.direita, codigo,l);
			codigo.deleteCharAt(codigo.length()-1);

			if (esquerda==null) return direita; else return esquerda;
		}
		return null;
	}

}
