public class Polynomial {
	double array[];
	
	public Polynomial(){
 		this.array = new double[0];
	}
	
	public Polynomial(double[] given_coef) {
		this.array = given_coef;
	}
	
	
	public Polynomial add(Polynomial given_poly) {
		int len = (this.array.length > given_poly.array.length) ? this.array.length: given_poly.array.length;
		double new_array[] = new double[len];
		
		//new_array = new double [0];
		for (int i = 0; i < len ; i++){
			new_array[i]= 0;
		}
		
		
		for(int i = 0; i < this.array.length; i++) {
			new_array[i] = this.array[i];
		}
		for(int i = 0; i < given_poly.array.length; i++) {
			new_array[i] = new_array[i] + given_poly.array[i];
		}
		
		Polynomial result = new Polynomial(new_array);
		
		return result;
		
		
	}
	
	public double evaluate(double x) {
		double sum = 0;
		for(int i = 0; i < array.length; i++) {
			sum = sum + (array[i] * Math.pow(x,i));
		}
		return sum;
			
	}
	
	public boolean hasRoot(double x) {
		return (evaluate(x) == 0);
		
	}

}
		