import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Polynomial {
	double coef_array[];
	int exp_array[];
	
	
	
	public Polynomial(){
 		this.coef_array = new double[0];
 		this.exp_array = new int [0];
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
		if(element.contains("x")) {
			String[] array1 = element.split("x");
			if(array1.length == 2) {
				coef_array[index] = Double.parseDouble(array1[0]);
				exp_array[index] = Integer.parseInt(array1[1]);
			}
			else {
				coef_array[index] = Double.parseDouble(array1[0]);
				exp_array[index] = 1;
			}
		}
		else {
			coef_array[index] = Double.parseDouble(element);
			exp_array[index] = 0;
		}
	}
	
	
	
	public Polynomial add(Polynomial given_polynomial) {
		
		if(given_polynomial.exp_array.length == 0)
			return new Polynomial(this.coef_array, this.exp_array);
		
		int length = given_polynomial.exp_array.length;
		
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
		double[] new_coef = new double[length];
		int[] new_exp = new int[length];
		
		for(int i1 = 0; i1 < given_polynomial.coef_array.length; i1++) {
			new_coef[i1] = given_polynomial.coef_array[i1];
			new_exp[i1] = given_polynomial.exp_array[i1];
		}
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
		
		Polynomial new_poly = new Polynomial(new_coef,new_exp);	
		return new_poly;
		
	}
	
	public double evaluate(double x) {
		double sum = 0;
		for(int i = 0; i < this.coef_array.length; i++) {
			sum = sum + (this.coef_array[i] * Math.pow(x,this.exp_array[i]));
		}
		return sum;
			
	}
	
	public boolean hasRoot(double x) {
		return (evaluate(x) == 0);
		
	}
	
	public Polynomial multiply(Polynomial given_poly) {
		
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
		for(int i = 0; i < this.exp_array.length; i++) {
			for(int j = 0; j < given_poly.exp_array.length; j++) {
				int index = this.exp_array[i] + given_poly.exp_array[j];
				product[index] = product[index] +(this.coef_array[i] * given_poly.coef_array[j]);
			}
		}
		
		int count = 0;
		for(int i = 0; i < length; i++) {
			if(product[i] != 0)
				count++;
		}
		double[] coef = new double[count];
		int[] exp = new int[count];
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
	
	
	public void SaveToFile(String file_name) throws IOException {
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
		