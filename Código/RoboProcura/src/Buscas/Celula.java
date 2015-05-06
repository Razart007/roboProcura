package Buscas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Celula {
	private int linha;
	private int coluna;
	private boolean obstaculo;
	private JButton botao;
	
	
	public Celula(int linha, int coluna, boolean obstaculo){
		this.linha = linha;
		this.coluna = coluna;
		this.obstaculo = obstaculo;
		botao = new JButton();		
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
}