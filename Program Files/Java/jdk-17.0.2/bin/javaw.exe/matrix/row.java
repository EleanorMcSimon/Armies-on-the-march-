package matrix;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;



public class row {
	netur row[];
	row last;
	boolean output;
	boolean inpute;
	
	HashSet<Double> hs = new HashSet<Double>();
char[] outs;
	public row(int size, row last, boolean output, boolean inpute, char[]out)
	{
		row = new netur[size];
		this.last = last;
		this.output = output;
		this.inpute = inpute;
		this.outs = out;
		
	}
public boolean isout()
{
	return output;
}
	public void fillrand()
	{
		Random r = new Random();
		for(int x = 0;  x < (last.sizeofoptions()+row.length); x++)
		{
		double randomValue = last.sizeofoptions() + (last.sizeofoptions() - 0) * r.nextDouble();
		hs.add(1.0/( 1.0 + Math.pow(Math.E,(-1.0*randomValue))));
		//hs.add(r.nextGaussian(0.5, 0.0000001));
		}
	}
	public int sizeofoptions()
	{
		return row.length;
	}
	public netur getnetron(int i)
	{
		return row[i];
	}
	public void makefirstlayer()
	{
		for(int i=0; i < row.length; i++)
		{
			netur n = new netur();
			//System.out.print(row.length);
			row[i] =n;
		}
	}
	public void makehiddenlayer(int[] a) {
		fillrand();
		System.out.print(last.sizeofoptions());
		for(int i=0; i < row.length; i++)
		{
			//System.out.print(i);
			netur n = new netur();
			for(int x=0; x < last.sizeofoptions(); x++)
			{
			
				n.addvoitoset(last.getnetron(x).getweight(), hs.iterator().next());
				
			}
			if(this.output)
			{
				n.outputname = outs[i];
			}
			row[i] = n;
			row[i].sigmoidA();
		}
	}
	public ArrayList<Double> change(double trainingsum, char anser)
	{
		ArrayList<Double> of = new ArrayList<Double>();
		for(int x =0; x < row.length; x++)
		{
			row[x].getweight();
			char s = row[x].outputname; 
			if(s == anser && row[x].weight < 1.0 )
			{
				double c = (-1*(1/trainingsum))*(((-Math.log(1))/1)*row[x].desigmoidA()+((1)*((-Math.log(1-row[x].desigmoidA()))/row[x].desigmoidA())));
				
			//System.out.print(c);
				if(Double.isNaN(c))
				{
					c=1.0;
				}
				
				row[x].weightchange(c);
				of.addAll(back(c, x));
				
			}
			else if(row[x].weight > 0)
			{
				double c = (-1*(1/trainingsum))*(((-Math.log(0))/1)*row[x].desigmoidA()+((0)*((-Math.log(0-row[x].desigmoidA()))/row[x].desigmoidA())));
				 
					if(Double.isNaN(c))
					{
						c=0.0;
					}
					row[x].weightchange(c);	
				of.addAll(back(c, x));
				
			}
		}
		return(of);
	}
	public ArrayList<Double> back (double weighchang, int a)
	{	
		ArrayList<Double> d = new ArrayList<Double>();
		Stream<Double> f;
	List<Double> j;
		 f = hs.stream();
		  j =f.toList();
		  
		  if(last != null && last.sizeofoptions() != 1  )
		  {
	if(a < j.size() )
	{
			double w =row[a].desigmoidA()*j.get(a);
			d.add(w);
	}		
		  }
			
		  
		  return d;
	}
	
	public void input(double f[]) {
	for(int x=0; x <row.length; x++)
	{
		row[x].weightchange(f[x]);;
	}
	}
	
	
}
