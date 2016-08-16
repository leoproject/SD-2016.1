package Thread;

public class Lembrete implements Runnable {

	private String nome;
	private int intervalo;
	private int total;

	public Lembrete(String nome, int intervalo, int total) {
		this.nome = nome;
		this.intervalo = intervalo;
		this.total = total;
	}

	public void run() {
		for (int i = 1; i <= total; i++) {
			System.out.println("tomar " + nome + " " + i);
			try {
				Thread.sleep(intervalo * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
