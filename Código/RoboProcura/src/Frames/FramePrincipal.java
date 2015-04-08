package Frames;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class FramePrincipal extends JFrame{
	JPanel ambiente;
	JMenuBar barraMenus;
	JMenu arquivo, executar;
	JMenuItem sair, novo, abrir, salvar;
	JButton executarBu;
	

	public FramePrincipal(){
		inicializaFrame();
		inicializaComponentes();
	}
	
	private void inicializaFrame(){
		this.setTitle("Robo � Procura");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagens/Robo.png"));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setSize(1024, 650);
		this.setLocationRelativeTo(this);
		this.setVisible(true);
	}
	
	private void inicializaComponentes(){
		barraMenus = new JMenuBar();
		barraMenus.setPreferredSize(new java.awt.Dimension(101,23));
		this.setJMenuBar(barraMenus);
		
		novo = new JMenuItem("Novo",KeyEvent.VK_N);
		abrir = new JMenuItem("Abrir",KeyEvent.VK_O);
		salvar = new JMenuItem("Salvar",KeyEvent.VK_S);
		sair = new JMenuItem("Salvar",KeyEvent.VK_E);
		
		arquivo = new JMenu("Arquivo");
		arquivo.add(novo);
		novo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		arquivo.add(abrir);
		abrir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		arquivo.add(salvar);
		salvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		arquivo.add(sair);
		sair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		barraMenus.add(arquivo);
		
		executar = new JMenu("Executar");
		
		barraMenus.add(executar);
		
		ambiente = new JPanel();
		ambiente.setSize(100, 200);
		ambiente.setBackground(Color.WHITE);
		add(ambiente);
		ambiente.setBounds(50, 50, 150, 250);
		
		executarBu = new JButton("Executar");
		executarBu.setSize(100,100);
		add(executarBu);
		executarBu.setBounds(300, 300, 100, 100);
	}
	
	public static void main(String args[]){
		FramePrincipal frame = new FramePrincipal();
	}
	
}
