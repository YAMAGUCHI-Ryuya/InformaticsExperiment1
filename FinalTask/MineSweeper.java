interface MineSweeperGUI {
    public void setTextToTile(int x, int y, String text);

    public void win();

    public void lose();

    public void setdarkGray(int x, int y);

    public void setCyan(int x, int y);

    public void setlightGray(int x, int y);

    public void setRed(int x, int y);
}

public class MineSweeper {

    private final int height;// x座標
    private final int width;// y座標
    private final int numberOfTiles;// マスの総数
    private final int numberOfBombs;// 地雷の数
    private final int[][] table;// 隣接するマスに存在する地雷の数
    private final boolean[][] flagTable;// フラグの有無
    private final boolean[][] openPanel;// パネルが開けられているかどうか
    private int count = 0;// パネルを開いた回数
    private boolean firstOpen;// パネルを開くのが一番初めかどうか

    public MineSweeper(int height, int width, int numberOfBombs) {
        this.height = height;
        this.width = width;
        this.numberOfTiles = height * width;
        this.numberOfBombs = numberOfBombs;
        this.table = new int[height][width];
        this.flagTable = new boolean[height][width];
        this.openPanel = new boolean[height][width];
        initTable();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    // 盤面の初期化
    void initTable() {
        /* ----- ここから実装してください． ----- */
        // 再初期化したときに前のデータを消す
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                this.table[y][x] = 0;
                this.openPanel[y][x] = false;
            }
        }
        setBombs();

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (this.table[y][x] != -1) {
                    // this.table[y][x]を原点として相対座標で求める
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            // 原点の調査を省く
                            if (i == 0 && j == 0) {
                                continue;
                            }
                            int _y = y + i;
                            int _x = x + j;
                            // 2次元配列以外を省く
                            if (_y < 0 || _y >= this.height || _x < 0 || _x >= this.width) {
                                continue;
                            }
                            if (this.table[_y][_x] == -1) {
                                this.table[y][x]++;
                            }
                        }
                    }
                }
            }
        }
    }

    // 地雷をセット
    void setBombs() {
        /* ----- ここから実装してください． ----- */
        for (int i = 0; i < numberOfBombs; i++) {
            int x = new java.util.Random().nextInt(this.width);// 地雷を設置するx座標
            int y = new java.util.Random().nextInt(this.height);// 地雷を設置するy座標
            while (this.table[y][x] == -1) {// 地雷の配置のかぶりをなくす
                x = new java.util.Random().nextInt(this.width);
                y = new java.util.Random().nextInt(this.height);
            }
            this.table[y][x] = -1;
        }
    }

    // 左クリックしてパネルを開く
    public void openTile(int x, int y, MineSweeperGUI gui) {
        /* ----- ここから実装してください． ----- */
        // 最初に地雷が隠されたパネルを開かない対策
        if (this.table[x][y] != 0 && this.firstOpen == false) {
            while (this.table[x][y] != 0) {
                initTable();
            }
        }
        this.firstOpen = true;
        // 地雷が隠されているパネルを開いたとき
        if (this.table[x][y] == -1 && this.flagTable[x][y] == false) {
            openAllTiles(gui);
            gui.lose();
        }
        // this.table[y][x]を原点として相対座標で求める
        else if (this.table[x][y] == 0 && this.flagTable[x][y] == false) {
            gui.setdarkGray(x, y);
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int _y = x + i;
                    int _x = y + j;
                    // 2次元配列以外を省く
                    // 地雷と既に開けられたパネルも見ない
                    if (_y < 0 || _y >= this.height || _x < 0 || _x >= this.width || this.table[_y][_x] == -1
                            || this.openPanel[_y][_x] == true) {
                        continue;
                    }
                    // 開けられていなければcount
                    if (this.openPanel[_y][_x] == false) {
                        count++;
                    }
                    this.openPanel[_y][_x] = true;// パネルが開いた
                    openTile(_y, _x, gui);
                }
            }
        } else if (this.table[x][y] >= 1 && this.flagTable[x][y] == false) {
            String str = String.valueOf(this.table[x][y]);
            gui.setTextToTile(x, y, str);
            // 開けられていなければcount
            if (this.openPanel[x][y] == false) {
                count++;
            }
            // パネルが開いた
            this.openPanel[x][y] = true;
        }
        // 開けられたマスの数と地雷が入っていないマスの数が一致したら
        if (count == numberOfTiles - numberOfBombs) {
            gui.win();
        }
    }

    // 右クリックしてパネルにフラグを立てる
    public void setFlag(int x, int y, MineSweeperGUI gui) {
        /* ----- ここから実装してください． ----- */
        // フラグがない場合かつ開けられていない
        if (this.flagTable[x][y] == false && this.openPanel[x][y] == false) {
            gui.setTextToTile(x, y, "F");
            this.flagTable[x][y] = true;
            gui.setCyan(x, y);
        }
        // フラグが既に存在かつ開かれていない
        else if (this.openPanel[x][y] == false) {
            gui.setTextToTile(x, y, " ");
            this.flagTable[x][y] = false;
            gui.setlightGray(x, y);
        }
    }

    // すべてのパネルを開く
    private void openAllTiles(MineSweeperGUI gui) {
        /* ----- ここから実装してください． ----- */
        // 地雷の場所をすべてBとして表示
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (this.table[y][x] == -1) {// 地雷が入っていたら
                    gui.setTextToTile(y, x, "●~*");
                    gui.setRed(y, x);
                } else if (this.table[y][x] != 0) {
                    String str = String.valueOf(this.table[y][x]);
                    gui.setTextToTile(y, x, str);
                }
            }
        }
    }
}