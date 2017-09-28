package ordenacao;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws Exception 
	{	
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);  

		//Atributo para definir o tamanho do bloco
		int tamanho_bloco;

		//Lê o arquivo Original
		RandomAccessFile arquivo_original = new RandomAccessFile("src/ordenacao/cep.dat", "r");

		//Gera o arquivo ordenado
		RandomAccessFile arquivo_bloco_ordenado = new RandomAccessFile("src/ordenacao/cep_bloco.dat", "rw"); 

		//Escolhe o tamanho do bloco
		System.out.println("Entre com o tamanho do Bloco:");
		tamanho_bloco=scanner.nextInt();

		int contador = 0;
		String linha_arquivo = "";

		System.out.println("Aguarde enquanto o arquivo é separado e ordenado em blocos");

		//Loop para contar as linhas, conforme exemplo em sala de aula
		while((linha_arquivo = arquivo_original.readLine()) != null)
		{
			contador++; 
		}

		//Posição inicial
		arquivo_original.seek(0);

		System.out.println("Total contador: " + contador);

		List<Endereco> lista_enderecos = new ArrayList<Endereco>(tamanho_bloco + 1);


		for(int posicao = 0; posicao < tamanho_bloco; posicao++) 
		{
			//Cria o objeto "endereco" para inserção na lista e manipulação dos atributos
			Endereco endereco = new Endereco();
			endereco.leEndereco(arquivo_original);
			lista_enderecos.add(endereco);

			System.out.println("Posição: " + posicao + " CEP: "  + lista_enderecos.get(posicao).getCep() + " Cidade: " + lista_enderecos.get(posicao).getCidade() + " Estado: " + lista_enderecos.get(posicao).getEstado());

			arquivo_bloco_ordenado.writeBytes("Posição: " + posicao + " CEP: "  + lista_enderecos.get(posicao).getCep() + " Cidade: " + lista_enderecos.get(posicao).getCidade() + " Estado: " + lista_enderecos.get(posicao).getEstado());

		}

		//Ordenação da coleção junto com o comparator
		Collections.sort(lista_enderecos, Endereco.COMPARA_CEP);


		//Teste de CEP para verificar se o mesmo está ordenado nas 10 primeiras posições
		for(int i=0; i<10; i++)
		{
			System.out.println(lista_enderecos.get(i).getCep());
		}
		arquivo_original.close();
		arquivo_bloco_ordenado.close();
	}

}
