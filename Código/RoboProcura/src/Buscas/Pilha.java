package Buscas;

public class Pilha {
	private final int SIZE = 140;
	private CelulaPilha[] st;
	private int top;

	public Pilha() {
		st = new CelulaPilha[SIZE];
		top = -1;
	}
	
	public void push(int linha, int coluna){ 
		int aux = top+1;
		CelulaPilha aux1 = new CelulaPilha();
		aux1.linha = linha;
		aux1.coluna = coluna;
		st[aux] = aux1;
		top++;
	}
	public CelulaPilha pop(){ 
		return st[top--]; 
	}

	public CelulaPilha peek(){ 
		return st[top]; 
	}

	public boolean isEmpty() { 
		return (top == -1); 
	}
}