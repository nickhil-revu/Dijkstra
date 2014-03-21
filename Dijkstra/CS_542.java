import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CS_542
{
static int[][] weight = new int[1000][1000];
static int[] cost=new int[1000];
static int[] visited=new int[1000];
static int[] pred=new int[1000];
static int fin;
static int i,j,d1;
static int[] path=new int[1000];
static int small_dist,new_dist,k,s,d,current,n,currr_dist;
static char[] ch=new char[3];
static char[] line=new char[ 1000 ];

public static void dk(int source,int destination)   //dijkstra implementation//
{
       fin=0;
	   for(i=1;i<1000;i++)
	   {
	   visited[i]=0;
	   }
	   s = source;
	   d = destination;
	   for(i=1;i<=n;i++)
	   {
	   cost[i]=65534;
	   pred[i]=65534;
	   }
	   cost[s]=0;
	   current=s;
	   visited[current]=1;
	   while(current!=d)
	   {
	     currr_dist=cost[current];
		 small_dist=65534;
		 for(i=1;i<=n;i++)
	   {
		 if(visited[i]==0)
		{
	         new_dist=currr_dist+weight[current][i];
			 if(new_dist<cost[i])
			 {
			 cost[i]=new_dist;
			 pred[i]=current;
			 }
			 if(cost[i]<small_dist)
			 {
			 small_dist=cost[i];
			 k=i;
			 }
	    }
        }
		current=k;
		visited[current]=1;
	}
}

public static void display_result(int check)
{
    i=d;
	path[fin]=d;
	fin++;
    while(pred[i]!=s)
	{
		j=pred[i];
		i=j;
		path[fin]=i;
		fin++;
	}
    path[fin]=s;
	if(check==0)
	{
		for(i=fin;i>0;i--)
		d1=weight[path[i]][path[i-1]];
		if(d1<65000)
		{
		System.out.println("\n Shortest Path:\n");
		for(i=fin;i>0;i--)
		System.out.print(path[i]+" -----> " );
		System.out.print(path[i]+"\n\n");
		for(i=fin;i>0;i--)
		System.out.println( path[i]+"->"+path[i-1]+"\t\t with cost ="+weight[path[i]][path[i-1]]);
		System.out.println("\nFor total cost = "+cost[d]);
		}
		else
	    System.out.println(" \n\n There is no path between the given Source and Destination\n");
    }
    	else
	   {
	   for(i=fin;i>0;i--)
       d1=weight[path[i]][path[i-1]];
	   System.out.print(" |");
	   if(d1>64999)
	   {
		if (d<10)
	    System.out.print(" ");
		System.out.println("        "+d+"  -->   -\t  |");
	   }
	   else
	   {
	    if (d<10)
	    System.out.print(" ");
		System.out.println("        "+d+"  -->   "+path[fin-1]+"\t  |");
       }
       }
}

    /**
     *
     * @return
     */
public static int read() throws IOException
{
          int i1=0, j = 1,k1=0;
          String filename = "";
          System.out.println("Please load original routing table data file: ");
          BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
          filename = buf.readLine();
                        FileInputStream fstream = new FileInputStream(filename);
                        DataInputStream dis = new DataInputStream(fstream);
                        BufferedReader br = new BufferedReader(new InputStreamReader(dis));
                        String line2;
                        int numofrouters=0;
                        while ((line2 = br.readLine()) != null)
                        {
                        numofrouters++;
                        }
fstream.close();
dis.close();
br.close();

                        fstream = new FileInputStream(filename);
                        dis = new DataInputStream(fstream);
                        br = new BufferedReader(new InputStreamReader(dis));
                        while ((line2 = br.readLine()) != null)
						{
                        String[] line = new String[numofrouters];
                        line = line2.split(" ");
                        for (k = 1; k <= numofrouters; k++)
                        {
                        weight[j][k]=Integer.parseInt(line[k-1]);
                        }
                        j++;
                        }
                        fstream.close();
                        dis.close();
                        br.close();

                        //Print the matrix on the console
                        System.out.println("Original routing table is as follows: ");
                        for (j = 1; j <= numofrouters; j++)
                        {
                            for (k = 1; k <= numofrouters; k++) {
                                System.out.print(weight[j][k] + " ");
                            }
                                System.out.println();
                        }

						for (i = 1; i <= numofrouters; i++)
                        {
                            for (j = 1; j <= numofrouters; j++)
                            {
								if(weight[i][j]<0)
								{
								    weight[i][j]=65000;
								}
                            }

                        }
				 return numofrouters;
}


public static void main(String args[]) throws IOException
{
	int src,sr,ds;
	int choice;
	String ch1;
	int	case1=0;
	System.out.println("\n\n");
	System.out.println("LINK STATE ROUTING PROTOCOL USING DIJKSTRA ALGORITHM\n\n");
	System.out.println("|---Submitted by:-------------|");
	System.out.println("| Akash Agarwal A20298520     |");
	System.out.println("| Nickhil Revu  A20299434     |");
	System.out.println("|-----------------------------|");
	System.out.println("\n");
	System.out.println("|--------------------Routing Console----------------|");
	System.out.println("| 1 - Load File                                     |");
	System.out.println("| 2 - Build Routing Table For Router                |");
	System.out.println("| 3 - Compute Optimal Path and Minimum Cost         |");
	System.out.println("| 4 - Exit                                          |");
	System.out.println("|---------------------------------------------------|");
	System.out.println("\t \n Enter Your Choice : ");
	InputStreamReader isr= new InputStreamReader(System.in);
	BufferedReader br= new BufferedReader(isr);
	ch1=br.readLine();
	while(ch1.compareTo("4")!=0)
	    {
		switch(ch1)
		{
			case "1" :
			try
             			{
                        n=read();
                        System.out.println("\n File Loaded \n \nNumber of Routers="+n);
					    case1=1;
			            break;
                        }catch(IOException |NumberFormatException|ArrayIndexOutOfBoundsException e)
                        {
                        System.out.println("\nERROR:"+e);
                        break;
                        }


			case "2" :
			try
			{
		    		if(case1==1)
			     	{
					System.out.println("\n Enter router number:");
					ch1=br.readLine();
					src= Integer.parseInt(ch1);
					if(1>src||src>n)
					System.out.println(" \nInvalid router");
					else
					{
					System.out.println("\n Routing Table for router number  \n \n"+src);
					System.out.println(" |Destination --> Next-hop|");
					System.out.println(" --------------------------");
					for(d=1;d<=n;d++)
					{
					if(d==src)
					{
					}
					else
					{
				    dk(src, d);
                    display_result(1);
					}
					}
					System.out.println(" --------------------------");
					}
					}
					else
					{
					System.out.println("\nPlease Load a File First");
					}
					break;
			    	}catch(IOException | NumberFormatException | ArrayIndexOutOfBoundsException e)
				    {
					System.out.println("\nERROR:"+e);
					break;
					}


			case "3" :
			try
			{
					if(case1==1)
					{
					System.out.println("\nEnter the source node 1 to "+ n);
					ch1=br.readLine();
					sr= Integer.parseInt(ch1);
					System.out.println("\nEnter the destination node 1 to "+n);
					ch1=br.readLine();
					ds= Integer.parseInt(ch1);
					if(sr>n || ds>n||sr<1||ds<1)
					{
					System.out.println("\n Invalid Source or Destination");
					}
					else
				    {
                    if(sr==ds&&sr>0&&ds>0)
					System.out.println("\nSource and destination are same");
					else
					{
					dk(sr,ds);
					display_result(0);
					}
					}
				    }
					else
					{
					System.out.println("Please Load a File First");
					}
					break;
					}catch(IOException | NumberFormatException | ArrayIndexOutOfBoundsException e)
					{
					System.out.println("\nERROR:"+e);
					break;
					}
			case "4":
					break;
        			default :
 					System.out.println("Invalid Choice");
}
			System.out.println("\n\n\n");

	        System.out.println("|--------------------Routing Console----------------|");
			System.out.println("| 1 - Load File                                     |");
			System.out.println("| 2 - Build Routing Table For Router                |");
			System.out.println("| 3 - Compute Optimal Path and Minimum Cost         |");
			System.out.println("| 4 - Exit                                          |");
			System.out.println("|---------------------------------------------------|");
			System.out.println("\n\nEnter Your Choice : ");
			ch1=br.readLine();


}
}
}
