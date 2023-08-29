package matrix;

public class netur {
	 double weight = 1;
	 boolean output;
	 char outputname;
	 public char getout()
	 {
		 return outputname;
	 }
	 public void addvoitoset(double random,double d)
	 {
		// System.out.print(random*d);
		 weight= weight+(random*d);
	 }
	 public double desigmoidA() {
		 return weight * (1 - weight);
	 }
	 public double sigmoidA() 
	 {
		
		//double link= r+-random;
		 
		 weight = 1.0/( 1.0 + Math.pow(Math.E,(-1.0*weight)));
		return weight;
	 }
	public void weightchange(double m)
	{
		weight =m;
	}
	public double getweight()
	{
		return weight;
	}
	public double changeout(double wanted)
	{
		 double needed = wanted - weight;
		 return(needed+weight);
	}
}
