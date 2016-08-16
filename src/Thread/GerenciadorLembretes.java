package Thread;

public class GerenciadorLembretes {

	public static void main(String[] args) {
		Lembrete l1 = new Lembrete("Dorflex", 4, 10);
		Lembrete l2 = new Lembrete("Paracetamol", 6, 20);
		Lembrete l3 = new Lembrete("Cataflam", 8, 8);

		Thread t1 = new Thread(l1);
		Thread t2 = new Thread(l2);
		Thread t3 = new Thread(l3);

		t1.start();
		t2.start();
		t3.start();

		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
