
import java.io.*;
import java.util.*;
class ComparatorQ implements Comparator<Node> 
{ 
	@Override
	public int compare(Node o1, Node o2) {
		if (o1.cost < o2.cost) 
            return 1; 
        else if (o1.cost > o2.cost) 
            return -1; 
                        return 0; 
	} 
} 
  

class Node {

	int value;
	int x;
	int y;
	Node parent;
	int cost;
	int path_cost;
	int flag=0;
	
	
	public Node() {
		super();
		this.value = 0;
		this.x = 0;
		this.y = 0;
		this.parent=null;
		this.cost=0;
		this.path_cost=0;
		this.flag=0;
	}
		
	public Node(int value, int x, int y) {
		super();
		this.value = value;
		this.x = x;
		this.y = y;
		this.parent=null;
		this.cost=0;
		this.path_cost=0;
		this.flag=0;
	}
	
}

public class homework {

	public static void main(String[] args) throws FileNotFoundException 
	{
		
		  
		File file=new File("C:\\Users\\niran\\eclipse-workspace\\KiranCheck\\src\\input.txt");
		
		PrintStream fileOut = new PrintStream("C:\\Users\\niran\\eclipse-workspace\\KiranCheck\\src\\output.txt");
		System.setOut(fileOut);
		
		Scanner sc=new Scanner(file);
		String ch=sc.next();
		
		int w= sc.nextInt();
		int h=sc.nextInt();
		int [][] mat= new int[h][w];
		
		int start_x=sc.nextInt();
		int start_y=sc.nextInt();
		
		int elevation=sc.nextInt();
		
		int noOfGoalStates=sc.nextInt();
		int[][] goalCoordinates=new int[noOfGoalStates][2];
		
		for(int i=0;i<noOfGoalStates;i++)
		{
			for(int j=0;j<2;j++)
			{
				int a=sc.nextInt();
				goalCoordinates[i][j]=a;
			}
		}
		
		for(int i=0; i<h; i++)
		{
			for(int j=0;j<w;j++)
			{
				int a=sc.nextInt();
				mat[i][j]= a;
			}
		}
		Node head= new Node(mat[start_x][start_y], start_x,start_y);
		
		Node destination[]= new Node[noOfGoalStates];
		
		for(int i=0; i<noOfGoalStates;i++)
		{	
			int j=0;
			destination[i]= new Node();
			destination[i].x=goalCoordinates[i][j];
			destination[i].y=goalCoordinates[i][j+1];
			destination[i].cost=0;
			destination[i].value= mat[destination[i].y][destination[i].x];
			destination[i].parent=null;
			destination[i].path_cost=0;
			destination[i].flag=0;
		}
		switch(ch)
		{
		case "BFS" :
					for(int i=0; i<noOfGoalStates;i++)
					{
						BFSImplement(mat,head, destination[i],elevation,h,w);
					}
					break;
		case "UCS" :
					for(int i=0; i<noOfGoalStates;i++)
					{
						UCSImplement(mat,head, destination[i],elevation,h,w);
					}
					break;
		case "A*"  :
					for(int i=0; i<noOfGoalStates;i++)
					{
						AImplement(mat,head, destination[i],elevation,h,w);
					}
					break;
		}
		sc.close();

	}

	private static void BFSImplement(int[][] mat, Node s, Node d, int e, int h, int w)  
	{
		
		Queue<Node> explore = new LinkedList<Node>();
		 ArrayList<Node> visited = new ArrayList<Node>(); 
		Node source= new Node();
		source=s;
		s.flag=1;
		visited.add(s);
		explore.add(s);
		while(s!=null)
		{
			
			if(s.x==d.y && s.y==d.x)
			{
				
				System.out.print(d.x+","+d.y+" ");
				return;
			}
			else
			{
				//System.out.print(s.x+","+s.y+" ");
				Node direction[]=new Node[8];
					if(s.y-1<0 )
					{
						direction[0]=new Node();
						direction[0].value=s.value+e+2;
					}
					else
					{
						direction[0]=new Node(mat[s.x][s.y-1],s.x,s.y-1);
					}
					
					if(s.x+1>=h || s.y-1<0)
					{
						direction[1]=new Node(); 
						direction[1].value=s.value+e+2;
					}
					else
					{
						direction[1]=new Node(mat[s.x+1][s.y-1],s.x+1,s.y-1);
					}

					if(s.x+1>=h)
					{
						direction[2]=new Node(); 
						direction[2].value=s.value+e+2;
					}
					else
					{
						direction[2]=new Node(mat[s.x+1][s.y],s.x+1,s.y);
					}

					if(s.x-1<0 || s.y-1<0 )
					{
						direction[3]=new Node(); 
						direction[3].value=s.value+e+2;
					}
					else
					{
						direction[3]= new Node(mat[s.x-1][s.y-1],s.x-1,s.y-1);
					}
					
					if( s.x-1<0 )
					{
						direction[4]=new Node(); 
						direction[4].value=s.value+e+2;
					}
					else
					{
						direction[4]=new Node(mat[s.x-1][s.y],s.x-1,s.y);
					}

					if(s.x+1>=h || s.y+1>=w)
					{
						direction[5]=new Node(); 
						direction[5].value=s.value+e+2;
					}
					else
					{
						direction[5]= new Node(mat[s.x+1][s.y+1],s.x+1,s.y+1); 
					}

					if( s.y+1>=w)
					{
						direction[6]=new Node();
						direction[6].value=s.value+e+2;
					}
					else
					{
						direction[6]=new Node(mat[s.x][s.y+1],s.x,s.y+1);
					}

					if( s.x-1<0 || s.y+1>=w)
					{
						direction[7]=new Node(); 
						direction[7].value=s.value+e+2;
					}
					else
					{
						direction[7]= new Node(mat[s.x-1][s.y+1],s.x-1,s.y+1);
					}

					if(visited.size() != 0)
					{
						for(int i=0;i<direction.length;i++)
						{
							for(int j=0;j<visited.size();j++)
							{
								if((direction[i].x==visited.get(j).x) && (direction[i].y==visited.get(j).y))
								{
									direction[i].flag=1;
								}
							}
						}
					}

					for(int i=0;i<direction.length;i++)
					{

						if((Math.abs(direction[i].value-s.value)<=e) && (direction[i].flag!=1)  )
						{
							direction[i].parent=s;
							direction[i].flag=1;
							visited.add(direction[i]);
							explore.add(direction[i]);
						}
					}
				explore.poll();
		}
	s=explore.peek();
	}
	
	System.out.println("FAIL");
}	

		

	private static void AImplement(int[][] mat, Node s, Node d, int e, int h, int w)  
	{
		 ArrayList<Node> visited = new ArrayList<Node>();
		 ArrayList<Node> temp= new ArrayList<Node>();
			PriorityQueue<Node> explore = new PriorityQueue<Node>(new ComparatorQ()); 
			Node source= new Node();
			source=s;
			s.flag=1;
			visited.add(s);
			explore.add(s);
			while(s!=null)
			{
				
				if(s.x==d.y && s.y==d.x)
				{
					do
					{
						System.out.print(s.x+","+s.y+" ");
						s=s.parent;
					}while(s!=source);
					
					
					
					return;
				}
				else
				{
					Node direction[]=new Node[8];
					
					if(s.y-1<0 )
					{
						direction[0]=new Node();
						direction[0].value=s.value+e+2;
					}
					else
					{
						direction[0]=new Node(mat[s.x][s.y-1],s.x,s.y-1);
					}
					
					if(s.x+1>=h || s.y-1<0)
					{
						direction[1]=new Node(); 
						direction[1].value=s.value+e+2;
					}
					else
					{
						direction[1]=new Node(mat[s.x+1][s.y-1],s.x+1,s.y-1);
					}

					if(s.x+1>=h)
					{
						direction[2]=new Node(); 
						direction[2].value=s.value+e+2;
					}
					else
					{
						direction[2]=new Node(mat[s.x+1][s.y],s.x+1,s.y);
					}

					if(s.x-1<0 || s.y-1<0 )
					{
						direction[3]=new Node(); 
						direction[3].value=s.value+e+2;
					}
					else
					{
						direction[3]= new Node(mat[s.x-1][s.y-1],s.x-1,s.y-1);
					}
					
					if( s.x-1<0 )
					{
						direction[4]=new Node(); 
						direction[4].value=s.value+e+2;
					}
					else
					{
						direction[4]=new Node(mat[s.x-1][s.y],s.x-1,s.y);
					}

					if(s.x+1>=h || s.y+1>=w)
					{
						direction[5]=new Node(); 
						direction[5].value=s.value+e+2;
					}
					else
					{
						direction[5]= new Node(mat[s.x+1][s.y+1],s.x+1,s.y+1); 
					}

					if( s.y+1>=w)
					{
						direction[6]=new Node();
						direction[6].value=s.value+e+2;
					}
					else
					{
						direction[6]=new Node(mat[s.x][s.y+1],s.x,s.y+1);
					}

					if( s.x-1<0 || s.y+1>=w)
					{
						direction[7]=new Node(); 
						direction[7].value=s.value+e+2;
					}
					else
					{
						direction[7]= new Node(mat[s.x-1][s.y+1],s.x-1,s.y+1);
					}

						if(visited.size() != 0)
						{
							for(int i=0;i<direction.length;i++)
							{
								for(int j=0;j<visited.size();j++)
								{
									if((direction[i].x==visited.get(j).x) && (direction[i].y==visited.get(j).y))
									{
										direction[i].flag=1;
									}
								}
							}
						}
						
						for(int i=0;i<direction.length;i++)
						{
							
							if(Math.abs(direction[i].value-s.value)<=e  && (direction[i].flag!=1)  )
							{
								if(i%2==0)
								{
									direction[i].path_cost=10;
								}
								else
								{
									direction[i].path_cost=14;
									
								}
								
								direction[i].cost= s.cost+ direction[i].path_cost+Math.abs(s.value-direction[i].value);
								direction[i].parent=s;
								direction[i].flag=1;
								temp.add(direction[i]);
								//explore.add(direction[i]);
								
							}
						}
						
						for(Node a:temp)
						{
							visited.add(a);
						}
						for(Node a:temp)
						{
							explore.add(a);
						}
						
					explore.poll();
					
					
			}
		s=explore.peek();
		}

				System.out.println("FAIL");
	}

	

	private static void UCSImplement(int[][] mat, Node s, Node d, int e, int h, int w) 
	{
		 ArrayList<Node> prin = new ArrayList<Node>(); 
		 ArrayList<Node> visited = new ArrayList<Node>(); 
		PriorityQueue<Node> explore = new PriorityQueue<Node>(new ComparatorQ()); 
		Node source= new Node();
		source=s;
		s.flag=1;
		visited.add(s);
		explore.add(s);
		while(s!=null)
		{
			
			if(s.x==d.y && s.y==d.x)
			{
				
				do
				{
					//System.out.print(s.y+","+s.x+" ");
					prin.add(s);
					s=s.parent;
				}while(s!=source);
				
				System.out.print(source.y+","+source.x+" ");
				Collections.reverse(prin);
				for (Node n : prin) {
					System.out.print(n.y+"," + n.x +" ");
				}
				
				System.out.println();
				

				
				return;
			}
			else
			{
				
				Node direction[]=new Node[8];
				
				if(s.y-1<0 )
				{
					direction[0]=new Node();
					direction[0].value=s.value+e+2;
				}
				else
				{
					direction[0]=new Node(mat[s.x][s.y-1],s.x,s.y-1);
				}
				
				if(s.x+1>=h || s.y-1<0)
				{
					direction[1]=new Node(); 
					direction[1].value=s.value+e+2;
				}
				else
				{
					direction[1]=new Node(mat[s.x+1][s.y-1],s.x+1,s.y-1);
				}

				if(s.x+1>=h)
				{
					direction[2]=new Node(); 
					direction[2].value=s.value+e+2;
				}
				else
				{
					direction[2]=new Node(mat[s.x+1][s.y],s.x+1,s.y);
				}

				if(s.x-1<0 || s.y-1<0 )
				{
					direction[3]=new Node(); 
					direction[3].value=s.value+e+2;
				}
				else
				{
					direction[3]= new Node(mat[s.x-1][s.y-1],s.x-1,s.y-1);
				}
				
				if( s.x-1<0 )
				{
					direction[4]=new Node(); 
					direction[4].value=s.value+e+2;
				}
				else
				{
					direction[4]=new Node(mat[s.x-1][s.y],s.x-1,s.y);
				}

				if(s.x+1>=h || s.y+1>=w)
				{
					direction[5]=new Node(); 
					direction[5].value=s.value+e+2;
				}
				else
				{
					direction[5]= new Node(mat[s.x+1][s.y+1],s.x+1,s.y+1); 
				}

				if( s.y+1>=w)
				{
					direction[6]=new Node();
					direction[6].value=s.value+e+2;
				}
				else
				{
					direction[6]=new Node(mat[s.x][s.y+1],s.x,s.y+1);
				}

				if( s.x-1<0 || s.y+1>=w)
				{
					direction[7]=new Node(); 
					direction[7].value=s.value+e+2;
				}
				else
				{
					direction[7]= new Node(mat[s.x-1][s.y+1],s.x-1,s.y+1);
				}
					if(visited.size() != 0)
					{
						for(int i=0;i<direction.length;i++)
						{
							for(int j=0;j<visited.size();j++)
							{
								if((direction[i].x==visited.get(j).x) && (direction[i].y==visited.get(j).y))
								{
									direction[i].flag=1;
								}
							}
						}
					}
					
					for(int i=0;i<direction.length;i++)
					{
						
						if(Math.abs(direction[i].value-s.value)<=e  && (direction[i].flag!=1)  )
						{
							if(i%2==0)
							{
								direction[i].path_cost=10;
							}
							else
							{
								direction[i].path_cost=14;
								
							}
							
							direction[i].cost= s.cost+ direction[i].path_cost;
							direction[i].parent=s;
							direction[i].flag=1;
							visited.add(direction[i]);
							//explore.add(direction[i]);
							
						}
					}
					
				explore.poll();
				
				for(Node a:visited)
				{
					explore.add(a);
				}
				
		}
	s=explore.peek();
	}

			System.out.println("FAIL");
}
}
