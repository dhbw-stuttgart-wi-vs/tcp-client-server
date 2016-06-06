package de.dbhw.vs.tcp;

public class ThreadDemo {

	public static void main(String[] args) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Ich bin ein neuer Thread " + Thread.currentThread().getId());
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Ich bin fertig " + Thread.currentThread().getId());
			}
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Ich bin ein neuer Thread " + Thread.currentThread().getId());
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Ich bin fertig " + Thread.currentThread().getId());
			}
		}).start();

	}

}
