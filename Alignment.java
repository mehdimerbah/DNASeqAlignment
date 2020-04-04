
public class Alignment {
	private String seq1,seq2;
	private int[][] matrix;
	private int match, mismatch, gap;

	public Alignment(String seq1, String seq2, int match, int mismatch, int gap) {
		super();
		this.seq1 = seq1;
		this.seq2 = seq2;
		this.match = match;
		this.mismatch = mismatch;
		this.gap = gap;
		this.matrix = Initialize(seq1,seq2,match,mismatch,gap);
	}
	

	// GETTERS AND SETTERS FOR PRIVATE FIELDS
	//SEQUENCE 1
	public String getSeq1() {
		return seq1;
	}
	public void setSeq1(String seq1) {
		this.seq1 = seq1;
	}

	//SEQUENCE 2
	public String getSeq2() {
		return seq2;
	}
	public void setSeq2(String seq2) {
		this.seq2 = seq2;
	}

	//MATRIX
	public int[][] getMatrix() {
		return matrix;
	}
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	//MATCH SCORE
	public int getMatch() {
		return match;
	}
	public void setMatch(int match) {
		this.match = match;
	}

	//MISMATCH SCORE
	public int getMismatch() {
		return mismatch;
	}
	public void setMismatch(int mismatch) {
		this.mismatch = mismatch;
	}

	//GAP SCORE
	public int getGap() {
		return gap;
	}
	public void setGap(int gap) {
		this.gap = gap;
	}


	//INITIALIZE METHOD: Initialized the matrix for alignment (Return : 2D INT ARRAY)
	public int[][] Initialize(String seq1, String seq2, int match, int mismatch,int gap){
		int m = seq1.length()+1;
		int n = seq2.length()+1;
		
		int[][] matrix = new int[m][n];
		
		for (int i = 0; i <n; i++) {
			matrix[0][i] = i*gap; 
		}
		
		for (int i = 0; i <m; i++) {
			matrix[i][0] = i*gap;
		}
		
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				matrix[i][j]= getMax(i,j,seq1,seq2,matrix,match,mismatch,gap);
			}
		}
		return matrix;
		
	}
	
	
	//GET_MAX METHOD: Calculates max score for matrix cells (Return: max score of type int) 
	public int getMax(int i, int j, String seq1, String seq2, int[][] matrix, int match, int mismatch, int gap) {
		
		int sim;
		int gapPenalty = gap;
		
		if(seq1.charAt(i-1)==seq2.charAt(j-1))
			sim = match;
		else
			sim = mismatch;
		
		int sc1, sc2, sc3;
		sc1 = matrix[i-1][j-1] + sim;
		sc2 = matrix[i][j-1] + gapPenalty;
		sc3 = matrix[i-1][j] + gapPenalty;
		
		int max = Math.max(sc1,sc2);
		int MAX = Math.max(max,sc3);
		
		return MAX;
		
	}
	
	//TRACE_PRINT: Prints our alignment. (Return: void)
	public void tracePrint(int[][] matrix, String seq1, String seq2) {
		int m = matrix.length;
		int n = matrix[0].length;
		System.out.print(" ");
		System.out.printf("%4s"," ");
		for (int i = 0; i < seq2.length(); i++) {
			System.out.printf("%4s",seq2.charAt(i));
		}
		
		System.out.println();
		
		for (int i = 0; i < m; i++) {
			if(i>=1) System.out.print(seq1.charAt(i-1));
			else System.out.print(" ");
			for (int j = 0; j < n; j++) {
				
					System.out.printf("%4d",matrix[i][j]);
				
			}
			System.out.println();
		}
		
		//System.out.println(Arrays.deepToString(matrix));
	}
	
	
 }
