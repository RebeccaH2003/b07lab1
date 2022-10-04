import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Polynomial {
	double coef_array[];
	int exp_array[];
	
	
	
	public Polynomial(){
 		this.coef_array = new double[0];
 		this.exp_array = new int[0];
	}
	
	public Polynomial(double[] given_coef, int[] given_exp) {
		this.coef_array = given_coef;
		this.exp_array = given_exp;
	}

	public Polynomial(File file) throws IOException {
				
		Scanner scanner = new Scanner(file);
		String line = scanner.nextLine();
		scanner.close();
		
		String new_line = line.replace("-", "+-");
		
		if(new_line.charAt(0) == '+')
			new_line = new_line.substring(1);
		
		String[] array = new_line.split("\\+");
		this.coef_array = new double[array.length];
		this.exp_array = new int[array.length];		
		for (int i = 0; i < this.coef_array.length ; i++){
			this.coef_array[i]= 0;
			this.exp_array[i] = 0;
		}
		for(int i = 0; i < array.length; i++) {
			check_x(array[i], this.coef_array, this.exp_array, i);
		}			
			
	}
	
	public void check_x(String element, double[] coef_array, int[] exp_array, int index) {
		//Assume for x we will write 1x1
		if(element.contains("x")) {
			String[] array1 = element.split("x");
			coef_array[index] = Double.parseDouble(array1[0]);
			exp_array[index] = Integer.parseInt(array1[1]);
		}
		else {
			coef_array[index] = Double.parseDouble(element);
			exp_array[index] = 0;
		}
	}
	
	
	
	public Polynomial add(Polynomial given_polynomial) {
		
		//return other polynomial if, one of them is empty
		if(given_polynomial.exp_array.length == 0)
			return new Polynomial(this.coef_array, this.exp_array);
		if(this.exp_array.length == 0)
			return new Polynomial(given_polynomial.coef_array, given_polynomial.exp_array);
		
		
		int length = given_polynomial.exp_array.length;
		
		//check if this's exp is exist in given_poly
		for(int i = 0; i < this.exp_array.length; i++) {
			boolean exist = false;
			for(int j = 0; j < given_polynomial.exp_array.length; j++) {
				if(this.exp_array[i] == given_polynomial.exp_array[j]) {
					exist = true;
				}
			}
			
			if(exist == false) 
				length++;
		}
		//create new arrays
		double[] new_coef = new double[length];
		int[] new_exp = new int[length];
		
		//put given_poly's exp and coef into new arrays
		for(int i1 = 0; i1 < given_polynomial.coef_array.length; i1++) {
			new_coef[i1] = given_polynomial.coef_array[i1];
			new_exp[i1] = given_polynomial.exp_array[i1];
		}
		
		//put this's coef into new coef, and if this'exp not in new exp, put the exp in
		int index = 0;
		for(int k = 0; k < this.coef_array.length; k++) {
			boolean exist = false;
			for(int z = 0; z < given_polynomial.coef_array.length; z++) {
				if(this.exp_array[k] == given_polynomial.exp_array[z]) {
					exist = true;
					new_coef[z] = new_coef[z] + this.coef_array[k];
				}
			}
			if(exist == false) {
				index++;
				new_exp[given_polynomial.exp_array.length - 1 + index] = this.exp_array[k];
				new_coef[given_polynomial.exp_array.length - 1 + index] = this.coef_array[k];
				
			}
			
		}
		//count non zero coef
		int count = 0;
		for(int i = 0; i < new_coef.length; i++) {
			if(new_coef[i] != 0.0) {
				count++;
			}
		}
		//create new arrays, that will not have zero coef
		double[] coef = new double[count];
		int[] exp = new int[count];
		
		int index1 = 0;
		for(int i = 0; i < new_coef.length; i++) {
			if(new_coef[i] != 0.0) {
				coef[index1] = new_coef[i];
				exp[index1] = new_exp[i];
				index1++;
			}
		}
		
		
		Polynomial new_poly = new Polynomial(coef,exp);	
		return new_poly;
		
	}
	
	public double evaluate(double x) {
		double sum = 0;
		for(int i = 0; i < this.coef_array.length; i++) {
			if(this.exp_array[i] == 0) {
				sum = sum + this.coef_array[i]; 
				continue;
			}	
			sum = sum + (this.coef_array[i] * Math.pow(x, this.exp_array[i]));
		}
		return sum;		
	}
	
	public boolean hasRoot(double x) {
		return (evaluate(x) == 0);
		
	}
	
	public Polynomial multiply(Polynomial given_poly) {
		
		if(given_poly.exp_array.length == 0)
			return this;
		
		if(this.exp_array.length == 0) 
			return given_poly;
		
		//find the largest exp in 2 arrays.
		int max1 = -1;
		int max2 = -1;
		for(int i = 0; i < this.exp_array.length; i++) {
			if(this.exp_array[i] > max1)
				max1 = this.exp_array[i];
		}
		
		for(int i = 0; i < given_poly.exp_array.length; i++) {
			if(given_poly.exp_array[i] > max2)
				max2 = given_poly.exp_array[i];
		}
		
		
		int length = max1 + max2 + 1;
		double[] product = new double[length];
		for(int i = 0; i < length; i++)
			product[i] = 0;
		
		//product is like array in lab1
		for(int i = 0; i < this.exp_array.length; i++) {
			for(int j = 0; j < given_poly.exp_array.length; j++) {
				int index = this.exp_array[i] + given_poly.exp_array[j];
				product[index] = product[index] +(this.coef_array[i] * given_poly.coef_array[j]);
			}
		}
		
		//count non zero coef
		int count = 0;
		for(int i = 0; i < length; i++) {
			if(product[i] != 0)
				count++;
		}
		double[] coef = new double[count];
		int[] exp = new int[count];
		
		//put coef and index(exp) in 2 arrays.
		int index = 0;
		for(int i = 0; i < length; i++) {
			if(product[i] != 0) {
				coef[index] = product[i];
				exp[index] = i;
				index++;
			}
		}
		return new Polynomial(coef,exp);
		
	}
	
	public String helperSave(int index) {
		String equation = "";
		//separate postive and negative
		if(this.coef_array[index] > 0) {
			if(this.exp_array[index] == 0) 
				equation ="+" + Double.toString(this.coef_array[index]);	
			else 
				equation = "+" + Double.toString(this.coef_array[index]) + "x" + Integer.toString(this.exp_array[index]);	
		}
		else {
			if(this.exp_array[index] == 0) 
				equation = Double.toString(this.coef_array[index]);
			else 
				equation = Double.toString(this.coef_array[index]) + "x" + Integer.toString(this.exp_array[index]);	
		}
		
		return equation;
		
	}
	
	public void saveToFile(String file_name) throws IOException {
		FileWriter writer = new FileWriter(file_name);
		String equation = "";
		
		if(this.exp_array[0]  == 0) {
			equation = Double.toString(this.coef_array[0]);
		}
		else {
			equation = Double.toString(this.coef_array[0]) + "x" + Integer.toString(this.exp_array[0]);
		}
		
		
		for(int i = 1; i < this.coef_array.length; i++) {
			equation = equation + helperSave(i);
				
		}
		equation = equation.replace(".0", "");
		equation = equation.replace("x1", "x");
		
		writer.write(equation);
		writer.close();
	}
}