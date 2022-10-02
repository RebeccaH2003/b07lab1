import java.io.File;
import java.io.IOException;

public class Driver { 
 public static void main(String [] args) { 
	//test File
	 /*
	 File f = new File("C:\\Users\\jiaqi\\Desktop\\B07\\lab1\\123.txt");
	 
	 Polynomial p = new Polynomial(f);
	 
	 System.out.println("Test file:");
	  for(int i = 0; i < p.coef_array.length; i++) {
		  System.out.println(p.coef_array[i]);
		  System.out.println(p.exp_array[i]);
		  System.out.println("==========================");
		  
	  }	*/
	 /*
	  double [] a1 = {1,-1}; 
	  int [] a2 = {0,2};
	  Polynomial p2 = new Polynomial(a1,a2); 
	  Polynomial s = p.multiply(p2);
	  Polynomial k = p.add(p2);
	  k.SaveToFile("C:\\Users\\jiaqi\\Desktop\\B07\\lab1\\save.txt");
	  */
	 
	  double [] a1 = {18,3,1}; 
	  int [] a2 = {5,0,2};
	  Polynomial f = new Polynomial(a1,a2); 
	  double [] b1 = {2,5,-9}; 
	  int [] b2 = {4,2,1};
	  Polynomial k = new Polynomial(b1,b2); 
	  Polynomial s = f.multiply(k);
	  //k.SaveToFile("C:\\Users\\jiaqi\\Desktop\\B07\\lab1\\save.txt");
	  System.out.println("k(0.1) = " + k.evaluate(0.1)); 
	  
	  
	  
	  System.out.println("Test multiply:");
	  for(int i = 0; i < s.coef_array.length; i++) {
		  System.out.println("++++++++++++++++++++++");
		  System.out.println(s.coef_array[i]);
		  System.out.println(s.exp_array[i]);  
	  }
	  
	  System.out.println("k(-0.1) = " + k.evaluate(-0.1)); 
	  if(k.hasRoot(1)) 
		   System.out.println("1 is a root of s"); 
		  else 
		   System.out.println("1 is not a root of s"); 
   } 
}