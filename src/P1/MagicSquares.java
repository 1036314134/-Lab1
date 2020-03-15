package P1;

import java.io.*;
import java.util.Scanner;

public class MagicSquares {
	public static void main(String[] args){
		boolean ans;
		System.out.println("1.txt�����");
		ans = isLegalMagicSquare("src/P1/txt/1.txt");
		System.out.println(ans);
		
		System.out.println("2.txt�����");
		ans = isLegalMagicSquare("src/P1/txt/2.txt");
		System.out.println(ans);
		
		System.out.println("3.txt�����");
		ans = isLegalMagicSquare("src/P1/txt/3.txt");
		System.out.println(ans);
		
		System.out.println("4.txt�����");
		ans = isLegalMagicSquare("src/P1/txt/4.txt");
		System.out.println(ans);
		
		System.out.println("5.txt�����");
		ans = isLegalMagicSquare("src/P1/txt/5.txt");
		System.out.println(ans);
		
		System.out.println();
		System.out.println("������һ����������");
		
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		while (n <= 0 || n % 2 == 0) {
			System.out.println("�����������������");
			n = scan.nextInt();
		}
        scan.close();
        
        System.out.println("6.txt�����");
        ans = isLegalMagicSquare("src/P1/txt/6.txt");
		System.out.println(ans);
        
	}
	

	
	public static boolean isLegalMagicSquare(String path){
		 String[] numbers;
		 int [][] a = new int [10000][10000];
		 int index = 0;
 		 try (FileReader reader = new FileReader(path); BufferedReader br = new BufferedReader(reader)) {
			 String line = null;
			 while ((line = br.readLine()) != null){
			 	numbers = line.split("\t");
			 	a[index] = new int[numbers.length]; 
			 	for(int i = 0; i < numbers.length; i++){
			 		try {
			 			a[index][i] = Integer.parseInt(numbers[i]);
			 		}catch(Exception e){
			 			System.out.println("�ָʽ����") ;
			 		}
			 		if(a[index][i] < 0) {
			 			System.out.println("�����и���");
			 			return false;
			 		}
			 	}	
			 	index++;
			 }
		 }catch (IOException e) {
		 	e.printStackTrace();
		 }
 		 
 		 int n = index;
		 int sum, summ = 0;
		 
		 if(n != a[0].length) {
			 System.out.println("����������");
			 return false;
		 }
		 for(int i = 0; i < n; i++) {
			 if(a[i].length != n) {
				 System.out.println("���Ǿ���");
				 return false;
			 }
		 }
	     
	     for(int i = 0; i < n; i++) {
	    	 summ += a[0][i];
	     }
	    	 
	     for(int i = 0; i < n; i++){
	    	 sum = 0;
	    	 for(int j = 0; j < n; j++){
	              sum += a[i][j]; 
	         }
	    	 if(sum != summ) {
	    		 return false;
	    	 }
	     }
	     
	     for(int i = 0; i < n; i++){
	    	 sum = 0;
	    	 for(int j = 0; j < n; j++){
	              sum += a[j][i]; 
	         }
	    	 if(sum != summ) {
	    		 return false;
	    	 }
	     }
	     
	     sum = 0;
	     for(int i = 0; i < n; i++) {
	    	 sum += a[i][i];
	     }
	     if(sum != summ) {
	    	 return false;
	     }
	     
	     sum = 0;
	     for(int i = 0; i < n; i++) {
	    	 sum += a[i][n - i - 1];
	     }
	     if(sum != summ) {
	    	 return false;
	     }
	     
	     return true;
	}
	

	public static boolean generateMagicSquare(int n) throws IOException {

		int magic[][] = new int[n][n];//��ʼ��һ������
		int row = 0, col = n / 2, i, j, square = n * n;

		for (i = 1; i <= square; i++) {//ѭ��n*n��
			magic[row][col] = i;//�ӵ�һ�е�n/2-1�п�ʼ
			if (i % n == 0)//ÿ����n�ξ�������һ��
				row++;
			else {
				if (row == 0)//�ڵ�һ��ʱ
					row = n - 1;//�ص���n��
				else
					row--;//����ÿ�μ�һ��
				if (col == (n - 1))//����ڵ�n��
					col = 0;//�ص���һ��
				else
					col++;//����ÿ�μ�һ��
			}
		}
		File file = new File("src/P1/txt/6.txt");
		PrintWriter output = new PrintWriter(file);
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				output.print(magic[i][j] + "\t");
			output.println();
		}
		output.close();
		return true;
    }
	

}

