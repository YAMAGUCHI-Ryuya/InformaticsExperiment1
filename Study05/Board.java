import java.io.*;

// 盤面のためのクラス
public class Board {
	// フィールド変数
	private Cell[][] cells;
	protected boolean isUpdated;

	// コンストラクタ
	public Board(int yLength, int xLength) {
		this.cells = new Cell[yLength][xLength];
        for (int y = 0; y < this.cells[0].length; y++) {//yLengthの長さ未満まで
			for (int x = 0; x < this.cells[1].length; x++) {//xLengthの長さ未満まで
				this.cells[y][x] = new Cell(x, y);//Cellクラスで各マスを初期化
			}
		}
	}

	// コンストラクタ(仮置き用)
	public Board(Board board, int nv, Cell firstUndecidedCell) {
		this(board.cells[0].length, board.cells[1].length);// 他のコンストラクタを呼び出す
		for (int y = 0; y < this.cells[0].length; y++) {
			for (int x = 0; x < this.cells[1].length; x++) {
				this.cells[y][x].setValue(board.cells[y][x].getValue());// 盤面valueを複製する
				for (int v = 1; v <= 9; v++) {
					this.cells[y][x].setPossible(v, board.cells[y][x].getPossible(v));// 盤面possibleを複製する
				}
			}
		}
		// そのマスに入る可能性のあった最初の数を新しい盤面のその場所に格納する
		this.cells[firstUndecidedCell.getY()][firstUndecidedCell.getX()].setValue(nv);
		System.out.println(
				"Fixed Temporaly at (" + firstUndecidedCell.getY() + "," + firstUndecidedCell.getX() + ") => " + nv);
	}

	// 問題の読み込み
	public void load() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int y = 0; y < 9; y++) {
			String buf = br.readLine(); // 1行ごとに読み込み
			for (int x = 0; x < 9; x++) {
				char c = buf.charAt(x); // x番目の文字を抜き出す
				if ('1' <= c && c <= '9') {
					this.cells[y][x].setValue(c - '0'); // 文字列として格納
				}
			}
		}
	}

	// 現在の数独の状態の出力
	public void print() {
		for (int y = 0; y < this.cells[0].length; y++) {
			for (int x = 0; x < this.cells[1].length; x++) {
				if (this.cells[y][x].getValue() != 0) { // 数字が格納されている場合
					System.out.print(this.cells[y][x].getValue());//this.cells[y][x]の数字を出力
				} else { // 数字が格納されていない場合
					System.out.print(".");
				}
			}
			System.out.println();
		}
	}

	// 行
	public void line(String str) throws Exception {
		for (int y = 0; y < 9; y++) {
			if (str.equals("possible")) { // possibleの更新
				possibleLine(y);
			} else if (str.equals("search")) { // 行の調査
				searchLine(y);
			}
		}
	}

	// 列
	public void row(String str) throws Exception {
		for (int x = 0; x < 9; x++) {
			if (str.equals("possible")) { // possibleの更新
				possibleRow(x);
			} else if (str.equals("search")) { // 列の調査
				searchRow(x);
			}
		}
	}

	// ブロック
	public void block(String str) throws Exception {
		for (int by = 0; by < 3; by++) {
			for (int bx = 0; bx < 3; bx++) {
				Cell[] block = new Cell[9];
				for (int y = 0; y < 3; y++) {
					for (int x = 0; x < 3; x++) {
						block[y * 3 + x] = this.cells[by * 3 + y][bx * 3 + x];
					}
				}
				if (str.equals("possible")) { // possibleの更新
					possibleBlock(block);
				} else if (str.equals("search")) { // ブロックの調査
					searchBlock(block);
				}

			}
		}
	}

	// possible更新(行)
	public void possibleLine(int y) {
		boolean[] filter = new boolean[10];
		for (int x = 0; x < 9; x++) {
            //filterのthis.cells[y][x]の添え字の場所にtrueを入れる
            //その行で使われている数字がわかる
			filter[this.cells[y][x].getValue()] = true;
		}
		for (int x = 0; x < 9; x++) {
			for (int v = 1; v <= 9; v++) {
				updatePossible(x, y, filter);
			}
		}
	}

	// possible更新(列)
	public void possibleRow(int x) {
		boolean[] filter = new boolean[10];
		for (int y = 0; y < 9; y++) {
            //filterのthis.cells[y][x]の添え字の場所にtrueを入れる
            //その列で使われている数字がわかる
			filter[this.cells[y][x].getValue()] = true;
		}
		for (int y = 0; y < 9; y++) {
			for (int v = 1; v <= 9; v++) {
				updatePossible(x, y, filter);
			}
		}

	}

	// possible更新(ブロック)
	public void possibleBlock(Cell[] block) {
		boolean[] filter = new boolean[10];
		for (int i = 0; i < 9; i++) {
            //filterのbook[i]の添え字の場所にtrueを入れる
            //そのブロックで使われている数字がわかる
			filter[block[i].getValue()] = true;
		}
		for (int i = 0; i < 9; i++) {
			for (int v = 1; v <= 9; v++) {
				// 各マスに格納できる，可能性のある数を調べる
				block[i].setPossible(v, block[i].getPossible(v) & !filter[v]);
			}
		}
	}

	// 各マスの調査
	public void searchCell() throws Exception {
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				// マスに数が確定していない場合
				if (this.cells[y][x].getValue() == 0) {
					// 確定していない場合
					int count = 0;
					int lastPossible = 0;
					for (int v = 1; v <= 9; v++) {
						if (this.cells[y][x].getPossible(v)) { // そのマスに入る可能性のある数字がある場合
							count++;
							lastPossible = v;// 残り一つの場合に使用
						}
					}
					if (count == 0) { // この数独は解けない
						throw new Exception(
								"Error: No possible number at cell(" + x + "," + y + ")" + this.cells[y][x]);
					}
					if (count == 1) { // 格納できる数が確定している場合
						// そのマスに格納する
						input(x, y, lastPossible);
						this.isUpdated = true;
					}
				}
			}
		}
	}

	// 各行の調査
	public void searchLine(int y) throws Exception {
		for (int v = 1; v <= 9; v++) {
			boolean isFixed = false;
			int count = 0;
			int lastPos = -1;
			for (int x = 0; x < 9; x++) {
				if (this.cells[y][x].getValue() == v) {//その行にvが存在する場合
					isFixed = true;
					break;//その行はもう見ない
                }
                //そのマスにvが存在しない&そのマスにvが入る可能性がある場合
				if (this.cells[y][x].getPossible(v)) {
					count++;//入る可能性のある数字の数
					lastPos = x;
				}
			}
			if (isFixed)
				continue;
			if (count == 0) {//そのマスにvが入る可能性がなかった場合
				throw new Exception("Error: No possible number " + v);
			}
			if (count == 1) {//そのマスにvが入る可能性のものが1つのみ
				input(lastPos, y, v);//そのマスにvを入れる
				this.isUpdated = true;
			}
		}
	}

	// 各列の調査
	public void searchRow(int x) throws Exception {
		for (int v = 1; v <= 9; v++) {
			boolean isFixed = false;
			int count = 0;
			int lastPos = -1;
			for (int y = 0; y < 9; y++) {
				if (this.cells[y][x].getValue() == v) {//その列にvが存在する場合
					isFixed = true;
					break;//その列はもう見ない
                }
                //そのマスにvが存在しない&そのマスにvが入る可能性がある場合
				if (this.cells[y][x].getPossible(v)) {
					count++;//入る可能性のある数
					lastPos = y;
				}
			}
			if (isFixed)
				continue;
			if (count == 0) {//そのマスにvが入る可能性がなかった場合
				throw new Exception("Error: No possible number " + v);
			}
			if (count == 1) {//そのマスにvが入る可能性のものが1つのみ
				input(x, lastPos, v);//そのマスにvを入れる
				this.isUpdated = true;
			}
		}
	}

	// 各ブロックの調査
	public void searchBlock(Cell[] block) throws Exception {
		for (int v = 1; v <= 9; v++) {
			boolean isFixed = false;
			int count = 0;
			int lastPos = -1;
			for (int i = 0; i < 9; i++) {
				if (block[i].getValue() == v) {// そのブロックにvが存在する場合
					isFixed = true;
					break;//そのブロックはもう見ない
				}
				// そのブロックにvが存在しない場合＆そのマスにｖが入る可能性がある場合
				if (block[i].getPossible(v)) {
					count++;//入る可能性のある数字の数
					lastPos = i;
				}
			}
			// そのブロックにvが存在する場合
			if (isFixed)
				continue;

			if (count == 0) {// この数独は解けない
				throw new Exception("Error: No possible number " + v);
			}
			if (count == 1) { // 可能性が１マスの場合
				block[lastPos].setValue(v);// そのマスにv格納
				System.out.println("Fixed at (" + block[lastPos].getX() + "," + block[lastPos].getY() + ") => " + v);
				this.isUpdated = true;
			}
		}
	}

	//possibleを更新させる
	public void updatePossible(int x, int y, boolean[] filter) {
		for (int v = 1; v <= 9; v++) {
            //そのマスに入る可能性のある数字
			this.cells[y][x].setPossible(v, this.cells[y][x].getPossible(v) & !filter[v]);
		}
	}
	//マスに数を格納する
	public void input(int x, int y, int v) {
		this.cells[y][x].setValue(v);//そのマスにvを入れる
		System.out.println("Fixed at (" + x + "," + y + ") => " + v);
    }

	// 未定のマスがあるか調べる
	public Cell searchFirstUndecidedCell() {
		Cell firstUndecidedCell = null; // マス１個分
		undecided_loop: for (int y = 0; y < this.cells[0].length; y++) {
			for (int x = 0; x < this.cells[1].length; x++) {
				if (this.cells[y][x].getValue() == 0) { // マスに数が確定してない場合
					firstUndecidedCell = this.cells[y][x];
					break undecided_loop;
				}
			}
		}
		return firstUndecidedCell;// 未定のマスのアドレスを返す
	}
}