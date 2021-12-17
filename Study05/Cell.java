public class Cell {
	private final int x;
	private final int y;
	private int value;
	private boolean[] possible;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		value = 0;
		possible = new boolean[9 + 1];
		for (int v = 1; v <= 9; v++) {
			possible[v] = true;
		}
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public void setValue(int value){//数字をset
		this.value = value;
	}

	public void setPossible(int v, boolean possible){//possibleをset
		this.possible[v] = possible;
	}

	public int getValue(){//数字をget
		return this.value;
	}

	public boolean getPossible(int v){//possibleをget
		return this.possible[v];
	}
}