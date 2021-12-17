public class Study01_2 {
    public static void main(String args[]) {
        int n = Integer.parseInt(args[0]);

        //ひし形の上部(n段目まで)
        for (int i = 1; i <= n; i++) {
            for (int j = i; j < n; j++) {
                System.out.print(" ");
            }
            if (i == 1) {//ひし形の頂点(上)
                System.out.println("*");
            } else {
                System.out.print("*");
                for (int k = 1; k < 2 * i - 2; k++) {//中の空白を作る
                    System.out.print(" ");
                }
                System.out.println("*");
            }
        }

        //ひし形の下部(n+1段目から2n-1段目まで)
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i; j < n; j++) {
                System.out.print(" ");
            }
            if (i == 1) {
                System.out.println("*");//ひし形の頂点(下)
            } else {
                System.out.print("*");//左
                for (int k = 1; k < 2 * i - 2; k++) {//中の空白を作る
                    System.out.print(" ");
                }
                System.out.println("*");//右
            }
        }
    }  
}