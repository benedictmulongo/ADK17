public class TryReducer {

	public static void main(String args[]) {
		new TryReducer();
	}

	public TryReducer() {

		Kattio io = new Kattio(System.in, System.out);

		int v = io.getInt();
		int e = io.getInt();
		int m = io.getInt();

		if (m >= v) {
			io.println("3");
			io.println("2");
			io.println("3");
			printStandardRoller(io);
			printStandardScener(io);
		} else {
			int n = v + 3;
			int s = e + 2 + v;
			int k = m + 2;

			io.println(n);
			io.println(s);
			io.println(k);

			printStandardRoller(io);

			for (int i = 4; i <= n; i++) {
				for (int j = 1; j <= k; j++) {
					if (j == 1) {
						io.print(k + " " + j);
					} else {
						io.print(" " + j);
					}
				}
				io.println();
			}

			printStandardScener(io);

			//Kopplar alla till nummer 3
			for (int i = 1; i <= v; i++ ) {
				io.println("2 3 " + (i+3));
			}

			for (int i = 0; i < e; i++) {
				int start = io.getInt() + 3;
				int stopp = io.getInt() + 3;
				io.println("2 " + start + " " + stopp);
			}
		}

		io.close();

	}
	static void printStandardRoller(Kattio io) {
		io.println("1 1");
		io.println("1 2");
		io.println("1 3");
	}
	static void printStandardScener(Kattio io) {
		io.println("2 1 3");
		io.println("2 2 3");
	}
}