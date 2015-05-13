package Buscas;

import java.io.Serializable;

import javax.swing.JButton;

public class Celula implements Serializable{
	private int linha;
	private int coluna;
	private boolean obstaculo, robo, objetivo, wasVisited, adjDisponiveis;
	private JButton botao;
	private Celula anterior = null;
	public int linhaInicial, colunaInicial, linhaObjetivo, colunaObjetivo; 
	
	public Celula(int linha, int coluna, boolean obstaculo){
		this.linha = linha;
		this.coluna = coluna;
		this.obstaculo = obstaculo;
		botao = new JButton();		
		robo = false;
		objetivo = false;
		wasVisited = false;
		adjDisponiveis = true;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public boolean isObstaculo() {
		return obstaculo;
	}

	public void setObstaculo(boolean obstaculo) {
		this.obstaculo = obstaculo;
	}
	
	public JButton getBotao() {
		return botao;
	}

	public void setBotao(JButton botao) {
		this.botao = botao;
	}
	
	public void setRobo(boolean robo){
		this.robo = robo;
	}
	
	public void setObjetivo(boolean objetivo){
		this.objetivo = objetivo;
	}
	
	public boolean isRobo(){
		return robo;
	}
	
	public boolean isObjetivo(){
		return objetivo;
	}
	
	public boolean isWasVisited() {
		return wasVisited;
	}

	public void setWasVisited(boolean wasVisited) {
		this.wasVisited = wasVisited;
	}
	
	public Celula getAnterior() {
		return anterior;
	}

	public void setAnterior(Celula anterior) {
		this.anterior = anterior;
	}
	
	public boolean isAdjDisponiveis() {
		return adjDisponiveis;
	}

	public void setAdjDisponiveis(boolean adjDisponiveis) {
		this.adjDisponiveis = adjDisponiveis;
	}

}