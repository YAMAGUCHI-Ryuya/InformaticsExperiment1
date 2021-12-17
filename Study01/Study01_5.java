public class Study01_5 {
    public static void main(String args[]) {
        int sNum = Integer.parseInt(args[0]);
        int eNum = Integer.parseInt(args[1]);
        int[] array = new int[168];//素数を格納する配列
        int count = countPrime(sNum, eNum, array);//素数の数
        int x = 0;

        System.out.println("==============" + sNum + "から" + eNum + "までの素数" + "==============");
        for (int i = 0; i < count; i++) {
            System.out.printf("%4d", array[i]);
            x++;
            if (x % 12 == 0) {
                System.out.println();
            }
        }
        System.out.println("================================================");
    }

    //素数かどうかの判定
    static boolean isPrime(int n) {
        boolean b = true;
        for (int j = 2; j < n; j++) {
            if (n % j == 0 || n == 1) {
                b = false;
            }
        }
        return b;
    }

    //isPrimeで判定した数を配列に格納
    static int countPrime(int sNum, int eNum, int[] array) {
        int count = 0;
        for (int i = sNum; i <= eNum; i++){
            if (isPrime(i)) {
                array[count] = i;
                count++;
            }
        }
        return count;
    }
}