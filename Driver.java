import java.io.File;
import java.io.IOException;


public class Driver { 
	 public static void main(String [] args) throws IOException{ 
		 
		 
		 //test File
		 
		 /*
		 File f = new File("C:\\Users\\jiaqi\\Desktop\\B07\\lab1\\save.txt");
		 
		 Polynomial p = new Polynomial(f);
		 
		 
		 System.out.println("Test file:");
		  for(int i = 0; i < p.coef_array.length; i++) {
			  System.out.println(p.coef_array[i]);
			  System.out.println(p.exp_array[i]);
			  System.out.println("==========================");
			  
		  }	*/
		 
		 
		  double [] b1 = {-1,-6,5}; 
		  int [] b2 = {1,0,3};
		  Polynomial f = new Polynomial(b1,b2); 
		  double [] a1 = {-3,2,3,-4}; 
		  int [] a2 = {2,1,3,4};
		  Polynomial p2 = new Polynomial(a1,a2); 
		  Polynomial s = f.multiply(p2);
		  
		  Polynomial k = f.add(p2);
		  f.SaveToFile("C:\\Users\\jiaqi\\Desktop\\b07lab1\\111.txt");
		  
		  System.out.println("Test add:");
		  for(int i = 0; i < k.coef_array.length; i++) {
			  System.out.println("++++++++++++++++++++++");
			  System.out.println(k.coef_array[i]);
			  System.out.println(k.exp_array[i]);  
		  }
		  System.out.println("Test multiply:");
		  for(int i = 0; i < s.coef_array.length; i++) {
			  System.out.println("++++++++++++++++++++++");
			  System.out.println(s.coef_array[i]);
			  System.out.println(s.exp_array[i]);  
		  }
		  System.out.println("evaluate: " + f.evaluate(9));
		  if(k.hasRoot(1)) 
			   System.out.println("1 is a root of s"); 
			  else 
			   System.out.println("1 is not a root of s"); 
	      } 
	} 
