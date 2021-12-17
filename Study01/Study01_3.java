public class Study01_3 {
	public static void main(String[] args) {

		int N = Integer.parseInt(args[0]);

		if (N == 1) { // 1を入力したときは1を表示する。
			System.out.println(N + " = 1");
		} else if (N > 1 && N <= 1000) {
			System.out.print(N + " = ");
			int count = 0;

			for (int i = 2; i <= N; i++) { // 2以上の素数で割る。
				while (N % i == 0) {
					N /= i;
					count++;
				}
				if (count != 0) { // "^0" は非表示
					System.out.print(i + "^" + count);
					if (N > 1) {
						System.out.print(" * ");
					}
					count = 0;
				}
			}
			System.out.println();
		}
	}
}