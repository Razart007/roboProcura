package Buscas;

public class Pilha {
	private final int SIZE = 8;
	private int[] queArray;
	private int front;
	private int rear;
	//-------------------------------------------------------------
	public Pilha()            
	   {
	   queArray = new int[SIZE];
	   front = 0;
	   rear = -1;
	   }
	//-------------------------------------------------------------
	public boolean isEmpty()
	   {
	   return ( rear+1==front || (front+SIZE-1==rear) );
	   }
	//-------------------------------------------------------------
}