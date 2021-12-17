import java.io.*;
public class SudokuSolver {
	public static void main(String[] args) {
		Board board = new Board(9, 9); // ボードを生成
		// 問題の読み込み
		try {
			board.load();//問題のファイルを読み込む
		} catch (IOException e) {
			System.out.println("Input Error");
			System.exit(1);
		}
		// 問題を解く
		try {
			solve(board); // solveメソッドにて問題を解く
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 問題を解くメソッド
	public static Board solve(Board board) throws Exception {
		while (true) {
			//状態の画面出力
			board.print();
			//possibleの更新
			board.line("possible"); // 行
			board.row("possible");	// 列
			board.block("possible");//ブロック

			board.isUpdated = false;// 調査状態をリセット
			board.searchCell();     // 各マスの調査
			board.line("search");   // 各行の調査
			board.row("search");    // 各列の調査
			board.block("search");  // 各ブロックの調査
			// マスの更新をしていない場合繰り返しを抜ける
			if (!board.isUpdated) {
				break;
			}
		}
		Cell firstUndecidedCell = board.searchFirstUndecidedCell();
		// 未定のマスがなければ終了
		if (firstUndecidedCell == null) {
			System.out.println("Solved");
			return board;
		}
		// 未定のマスがあれば，そのマスに仮の数を置く
		for (int nv = 1; nv <= 9; nv++) {
			if (firstUndecidedCell.getPossible(nv)) {
				try {
					// 数を仮置きした盤面を用意する
					Board newboard = new Board(board, nv, firstUndecidedCell);
					solve(newboard);// 数を仮置きした盤面で数独を解く
					return newboard;
				} catch (Exception e) {
					System.out.println("Temporaly-fixing failed");
				}
			}
		}
		throw new Exception("Now possible value");
	}
}