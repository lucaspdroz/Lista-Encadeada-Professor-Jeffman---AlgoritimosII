package Principal;

import java.util.Scanner;

public class SkipList {
	private static Node Head = null;
	private static Node Tail = null;
	private static Node Current = null;

	// Records previous quarter or half mark
	private static Node quarter = null;
	private static Node half = null;
	public static Scanner e = new Scanner(System.in);

	public static void main(String[] args) {
		mostraMenu();
		
		
		System.out.println("Pesquisa por ID de (" + " x " + "até" +" y " +" ):");
		int id = e.nextInt();
		// Quantidade de Numeros na Lista
		int ListCount = 100;

		// Setup a standard double linked list manually
		for (int i = 1; i <= ListCount; i++) {
			Node newNode = new Node();
			newNode.data = i;

			// associa o head e o Tail garantindo o atual
			if (Head == null) {
				Head = newNode;
				Tail = newNode;
				Current = newNode;

				quarter = newNode;
				half = newNode;
			} else {
				Current.next = newNode;
				newNode.prev = Current;

				// Add a quarter pointer if this node is a multiple of 25
				if ((i % 25) == 0) {
					newNode.prevQuarter = quarter;
					quarter.nextQuarter = newNode;

					quarter = newNode;
				}

				// Add a half pointer if this node is a multiple of 50
				if ((i % 50) == 0) {
					newNode.prevHalf = half;
					half.nextHalf = newNode;

					half = newNode;
				}

				// Set current node to be the next in line, set the tail
				// and then loop back around for next node.
				Current = newNode;
				Tail = newNode;
			}
		}
		
		// Run some tests
		System.out.println("Encontrado o ID " + id);
		procuraNumero(id);

	}

	private static void mostraMenu() {
		System.out.println("Selecione a Opção: \n 1 - Procurar por ID \n 2 - fechar o programa ");
		int menu = e.nextInt();
		switch(menu){
		case 1:
			Pesquisa();
		break;
		
		case 2: System.exit(0);
		
		}
	}

	public static void Pesquisa() {
		
	}	
	
	public static void procuraNumero(int num) {
		Node currentNode = Head;
		boolean Found = false;

		while (currentNode != null) {
			System.out.println("Contador de Nodos: " + currentNode.data);

			if (currentNode.data > num) {
				break;
			}

			// faz os pulos para chegar mais proximo
			if (currentNode.data != num) {
				if ((currentNode.nextHalf != null) && (currentNode.nextHalf.data <= num)) {
					currentNode = currentNode.nextHalf;
				} else if ((currentNode.nextQuarter != null) && (currentNode.nextQuarter.data <= num)) {
					currentNode = currentNode.nextQuarter;
				} else {
					currentNode = currentNode.next;
				}
			} else {
				Found = true;
				break;
			}
		}
		if (Found) {
			System.out.println("ID Encontrado!");
		} else {
			System.out.println("Numero Inexistente");
		}
	}
}
