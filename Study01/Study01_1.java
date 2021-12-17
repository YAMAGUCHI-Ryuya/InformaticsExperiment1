public class Study01_1 {
    public static void main(String args[]) {
        System.out.println("正の整数もしくはexsitを入力してください");
        System.out.print("> ");
        String input = new java.util.Scanner(System.in).nextLine();
        
        while (!(input.equals("exit"))) {//inputがexitでなければループ
            for (int i = 0; i < args.length; i++) {
                int num = Integer.parseInt(input);
                if (args[i].length() == num) {//args[i]の要素の文字列の長さとnumが等しいとき
                    System.out.println(args[i]);
                }
            }
            System.out.println("正の整数もしくはexsitを入力してください");
            System.out.print("> ");
           input = new java.util.Scanner(System.in).nextLine();
        }
    }
}