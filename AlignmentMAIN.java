import java.util.*;
import java.io.*;

public class AlignmentMAIN {
	
	public static void main(String[] args){
		String seq1 ="";
		String seq2 ="";
		Alignment align;
		//align.tracePrint(align.getMatrix(), seq1, seq2);
		Scanner input = new Scanner(System.in);
			System.out.print("\nEnter Match Score: ");
			int match = input.nextInt();
			System.out.print("\nEnter Mismatch Score: ");
			int mismatch = input.nextInt();
			System.out.print("\nEnter Gap Penalty: ");
			int gap = input.nextInt();
			input.close();
//Experimental: READING FROM FASTA FILE 
		//------------------------------------------------------------
	        try (Scanner sc = new Scanner(new File("seq1.fasta"))) {
	        	boolean first = true;
	            while (sc.hasNextLine()) {
	                String line = sc.nextLine().trim();
	                if (line.charAt(0) == '>') {
	                    if (first)
	                        first = false;
	                    else
	                    seq1+= line.substring(1);
	                } else {
	                    seq1+= line;
	                }
	            }
	            sc.close();
	        }catch(FileNotFoundException e) {
	        	System.out.println("There is No Such File: "+e);
	        }
	        try (Scanner sc = new Scanner(new File("seq2.fasta"))) {
	        	boolean first = true;
	            while (sc.hasNextLine()) {
	                String line = sc.nextLine().trim();
	                if (line.charAt(0) == '>') {
	                    if (first)
	                        first = false;
	                    else
	                    seq2+= line.substring(1);
	                } else {
	                    seq2+= line;
	                }
	            }
	            sc.close();
	        }catch(FileNotFoundException e) {
	        	System.out.println("There is No Such File: "+e);
	        }
	        
	        //-------------------------------------------------------------
	        //System.out.println(seq1);
	        //System.out.println(seq2);
	        System.out.println("\n\n\nPAIRWISE SEQUENCE ALIGNMENT:");
	        
	        for (int i = 0; i < seq2.length()*4; i++) {
	        	System.out.printf("%s","-");
			}
	        System.out.println();
//SEQUENCE OBJECT INSTANTIATION
	      //Argument Sequence: SEQ1, SEQ2, MATCH_SCORE, MISMATCH_SCORE, GAP_PENALTY
			align = new Alignment(seq1,seq2,match,mismatch,gap);
	        //Print Alignment
			align.tracePrint(align.getMatrix(), seq1, seq2);
	        
//WRITING ALIGNMENT TO A FILE (TBD)
			/*
			try {
				FileWriter fw = new FileWriter(new File("result_alignment.txt"));
				
			} catch (IOException e) {
				System.out.println("Error Occured While Creating File: "+e);
				e.printStackTrace();
			}
			*/
			
	}
}
