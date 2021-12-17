import java.util.Scanner;

public class Study01_4 {
    public static void main(String args[]) {
        String[] DayOfTheWeek = { "日", "月", "火", "水", "木", "金", "土" };
        String[] eDayOfTheWeek = { "sun", "mon", "tue", "wed", "thu", "fri", "sat" };
        int len[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        Scanner sc = new Scanner(System.in);
        int[] input = new int[2];

        while (true) {
            System.out.println("曜日を調べる日の情報を入力してください");
            System.out.print("> ");

            //inputで受け取った月日を配列に格納
            for (int i = 0; i < input.length; i++) {
                input[i] = sc.nextInt();
            }
            int mon = input[0];
            int day = input[1];

            if (mon == 0 && day == 0) {//日付が0月0日の時
                System.out.println("終了します");
                break;
            } else {
                int f = start(args, eDayOfTheWeek);
                int d = lengthDays(mon, day, len);
                int k = weekName(d, f);
                System.out.println(mon + "月" + day + "日" + "は" + DayOfTheWeek[k] + "曜日です");
            }
        }
    }

    //1月1日の曜日を決める
    public static int start(String[] week, String[] eDayOfTheWeek) { 
        int f = 0;
        for (int i = 0; i < 7; i++) {
            if (week[0].equals(eDayOfTheWeek[i])) {
                f = i;
            }
        }
        return f;
    }

    //1月1日から受け取った日付までの総日数を求める
    public static int lengthDays(int mon, int day, int[] len) { 
        int total = -1, d = 0;
        for (int j = 0; j < mon - 1; j++) {
            total += len[j];
        }
        d = total + day;
        return d;
    }

    //DayOfTheWeekに対応する添え字を求める
    public static int weekName(int d, int f) {
        int k = (d + f) % 7;
        return k;
    }
}