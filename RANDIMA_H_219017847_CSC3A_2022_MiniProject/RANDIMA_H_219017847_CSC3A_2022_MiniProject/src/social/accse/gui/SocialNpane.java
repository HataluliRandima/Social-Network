package social.accse.gui;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.jwetherell.algorithms.data_structure.Graph;
import com.jwetherell.algorithms.data_structure.Graph.Edge;
import com.jwetherell.algorithms.data_structure.Graph.Vertex;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import social.module.Area;

public class SocialNpane extends StackPane{
	
	//Test reading on file 
	private final static String post = "post";
	private final static String studentunder = "UNDERGRAD";
	
	private String path = "src//social//module//socialNetwork";
	private File file;
	private Scanner scanner;
	private StringTokenizer st;
	private StringTokenizer st_2;
   //It ends here the code 
	
	//Declaring Buttons 
	private Button btnaddGrad;
	private Button btnaddHon;
	private Button btnaddMast;
	private Button btnaddedge;
    private Button btnremovestudent;
    private Button btnUpdatestudent;
    private Button btnremoveconnection ;
    private Button btnhelp;
    private Button btndetails;
    private Button btnCalc;
    
    //Show alert and attempt
    int alertmessage = 0;
	int attemptaccess = 0 ;
	
	private JFrame help;
    
	private GridPane gp;

	private SocialNcanvas cv;
	private Graph<Area> graph = null;

	private ArrayList<Vertex<Area>> vlist;
	private ArrayList<Edge<Area>> elist;

	private BorderPane bp;

	//Constructor for the class
	public SocialNpane() 
	{

		//To test on reading the file 
		file = new File(path);	
		try {
			File f = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			path =  f.getParent() + "src//social//module//socialNetwork";
			
			scanner = new Scanner(file);
		}catch(URISyntaxException e){
			
		} catch (FileNotFoundException e) {
			System.err.println("File path: " + file.getAbsolutePath() + "\n");
			System.err.println("File does not exist");
		}
		
		//The original mine start here 
		graph = new Graph<>();
		
		// setGui();
		btnaddGrad = new Button("add UnderGraduate");
		btnaddGrad.setPrefWidth(200);

		btnaddHon = new Button("add Honours");
		btnaddHon.setPrefWidth(200);

		btnaddMast = new Button("add Masters");
		btnaddMast.setPrefWidth(200);
		
		btnaddedge = new Button ("Connect students");
		btnaddedge.setPrefWidth(200);
		
		btnremovestudent = new Button ("Remove student");
		btnremovestudent.setPrefWidth(200);
		
		btnUpdatestudent = new Button ("Update student");
		btnUpdatestudent.setPrefWidth(200);
		
		btnremoveconnection = new Button ("Remove connection");
		btnremoveconnection.setPrefWidth(200);
		
		btnhelp = new Button ("Help");
		btnhelp.setPrefWidth(200);
		
		btndetails = new Button ("See student Details");
		btndetails.setPrefWidth(200);
		
		btnCalc = new Button ("Calculate Dist & Alert Mess");
		btnCalc.setPrefWidth(200);

		gp = new GridPane();
		
		bp = new BorderPane();
		
		cv = new SocialNcanvas();

		//Button to add Undergrad student
		btnaddGrad.setOnAction(e -> {

			vlist = (ArrayList<Vertex<Area>>) graph.getVertices();
			elist = (ArrayList<Edge<Area>>) graph.getEdges();

			String name = JOptionPane.showInputDialog("Provide the name of undergraduate student");
			String coursename = JOptionPane.showInputDialog("Provide the course studying of  Undergraduate student");
			int year = Integer.parseInt(JOptionPane.showInputDialog("provide the year of study Undergraduate student"));
			String varname = JOptionPane.showInputDialog("Provide the name of Univerity (only prefix) ");
			int X = Integer.parseInt(JOptionPane.showInputDialog("Provide X location of undergrad range 100 - 700"));
			int Y = Integer.parseInt(JOptionPane.showInputDialog("Provide Y location of undergrad range 100 - 700"));

			Area value = new Area(name, "UNDERGRAD", varname, coursename, year, X, Y);

			Vertex<Area> undergrad = new Vertex<>(value, 10);

			vlist.add(undergrad);

			graph = new Graph<>(vlist, elist);
			cv.draw(graph);
			JOptionPane.showMessageDialog(null, name + "  has been created");
		     alertmessage += 1;
		});
		
		//Button to add Honours student
		btnaddHon.setOnAction(e -> {
			vlist = (ArrayList<Vertex<Area>>) graph.getVertices();
			elist = (ArrayList<Edge<Area>>) graph.getEdges();

			String name = JOptionPane.showInputDialog("Provide the name of Postgrade student");
			String coursename = JOptionPane.showInputDialog("Provide the course studying of  Postgrade student");
			int year = Integer.parseInt(JOptionPane.showInputDialog("provide the year of study Postgrade student"));
			String varname = JOptionPane.showInputDialog("Provide the name of Univerity (only prefix) ");
			int X = Integer.parseInt(JOptionPane.showInputDialog("Provide X location of Postgrade range 100 - 700"));
			int Y = Integer.parseInt(JOptionPane.showInputDialog("Provide Y location of Postgrade range 100 - 700"));

			Area value = new Area(name, "HONOURS", varname, coursename, year, X, Y);

			Vertex<Area> honour = new Vertex<>(value, 10);

			vlist.add(honour);

			graph = new Graph<>(vlist, elist);
			cv.draw(graph);
			JOptionPane.showMessageDialog(null, name + "  has been created");
		     alertmessage += 1;
		});
		
		//Button to add connection between students
		btnaddedge.setOnAction(e ->{

			vlist = (ArrayList<Vertex<Area>>) graph.getVertices();
			elist = (ArrayList<Edge<Area>>) graph.getEdges();
			// reading names of areas that are to be connected
			String Sname = JOptionPane.showInputDialog("Please provide Name of the Student from which you want link to other Student").toUpperCase();
	//		String tt1 = JOptionPane.showInputDialog("What is the type of student this " + Sname +" ?");
			String Aname = JOptionPane.showInputDialog("Please provide Name of the Student to which you want to link to other Student")	.toUpperCase();
			//String tt2 = JOptionPane.showInputDialog("What is the type of student this " + Aname +" ?");
			int cost = Integer.parseInt(JOptionPane.showInputDialog("What is the distance between the students? "));

			Vertex<Area> Student1 = null;
			Vertex<Area> area = null;

			for (int v = 0; v < vlist.size(); v++) {

				String name = vlist.get(v).getValue().getName().toUpperCase();
				String type = vlist.get(v).getValue().getType().toUpperCase();
                int year = vlist.get(v).getValue().getyear();
				// checking if areas are in the list
				if (name.equals(Sname) ) //type.equals(tt1)
				{
 
					Student1 = vlist.get(v);
				 

				} else if (Aname.equals(name) )//&& tt2.equals(type)
				{
			 
					area = vlist.get(v);
			 
				}

			}
                  
			//	if(station.getValue().getType().equals( area.getValue().getType()) && station.getValue().getCoursename().equals(area.getValue().getCoursename()))
				if(Student1.getValue().getType().equals( area.getValue().getType()) )
	        	{
					if( Student1.getValue().getCoursename().equals(area.getValue().getCoursename()))
					{
						
					
				
				 
		
			
 
			// making sure that area is not connected to itself
			if ((!area.equals(null) && !Student1.equals(null)) && !(area.equals(Student1))) {

				Edge<Area> edge = new Edge<>(cost, Student1, area);

				for (int v = 0; v < vlist.size(); v++) {

					if (Student1.getValue().getName().equals(vlist.get(v).getValue().getName())) {
						//change
						if(Student1.getValue().getType() != (vlist.get(v).getValue().getType()))
						{
							JOptionPane.showMessageDialog(null, "Not same type");
						
						}
						else
						{
							vlist.get(v).addEdge(edge);
						}
					} else if (area.getValue().getName().equals(vlist.get(v).getValue().getName())) {
						if(area.getValue().getType() != (vlist.get(v).getValue().getType()))
						{
							JOptionPane.showMessageDialog(null, "Not same type");
						
						}
						else
						{
							vlist.get(v).addEdge(edge);
						}
					}

				}
				
				//Mine now now 
				
                JOptionPane.showMessageDialog(null, Student1.getValue().getName() + " is connected to " + area.getValue().getName());
				// adding an edge
                alertmessage += 1;
				elist.add(edge);

			} else {

				JOptionPane.showMessageDialog(null, "There was problem during processing, please input valid Details");

			}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "They do not do the  same Course");
						//  alertmessage += 1;
						  attemptaccess += 1;
					}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "They are not the same type of students");
				//  alertmessage += 1;
				  attemptaccess += 1;
			}


			graph = new Graph<>(vlist, elist);
			cv.draw(graph);
		});
		
		btnremovestudent.setOnAction(e ->{
			vlist = (ArrayList<Vertex<Area>>) graph.getVertices();
			elist = (ArrayList<Edge<Area>>) graph.getEdges();
			
			//reading name of student you want to remove
			String sname = JOptionPane.showInputDialog("What is the name of the Area you want to remove?").toUpperCase();
			
			int a = -1;
			int b = -1;
			  String hai = null;
			for(int v=0; v<vlist.size(); v++) {
				  b =v;
				   
				
				if(sname.equals(vlist.get(v).getValue().getName().toUpperCase())) {
					 
					a = v;
					
				}
			}
			if(a!=-1)
			{
					for(int d=0; d<elist.size(); d++) {
						
						if((vlist.get(a).equals(elist.get(d).getToVertex()) || (vlist.get(a).equals(elist.get(d).getFromVertex())))) {
							
							
							System.out.println(elist.get(d).getToVertex().getValue().getName());
							//removing edge after an area is removed
							 
				
							
							elist.remove(d);
							
						}
					elist.remove(d);
						
					}
					hai = vlist.get(b).getValue().getName();
					String nam = vlist.get(a).getValue().getName();
					int x = vlist.get(a).getValue().getX();
					int y = vlist.get(a).getValue().getY();
					String type = vlist.get(a).getValue().getType();
					int year = vlist.get(a).getValue().getyear();
					String varn = vlist.get(a).getValue().getVarname();
					String cor = vlist.get(a).getValue().getCoursename();
					
					JOptionPane.showMessageDialog(null, "Name: " + nam + "\nX location: " + x + "\nY location: " +y+ "\nStudent Type: " + type + "\nCourse Name:  "+cor +"\n"+ nam +" is Removed from the system");
					  alertmessage += 1;
					//removing area
					vlist.remove(a);
					
					
				}
 
			graph = new Graph<>(vlist, elist);
			cv.draw(graph);
			
		});
		
		btnUpdatestudent.setOnAction(e -> {

			vlist = (ArrayList<Vertex<Area>>) graph.getVertices();
			elist = (ArrayList<Edge<Area>>) graph.getEdges();
			
			//reading in form GUI
			String name = JOptionPane.showInputDialog("Please provide Name of the Student you want to Update").toUpperCase();
			
			int i = -1;
			
			for(int v=0; v<vlist.size(); v++) {
				
				if(name.equals(vlist.get(v).getValue().getName().toUpperCase())) {
					
					i = v;
					
				}
				
			}
			
			if(i!=-1) {
				
				for(int d=0; d<elist.size(); d++) {
					
					if((vlist.get(i).equals(elist.get(d).getToVertex()) || (vlist.get(i).equals(elist.get(d).getFromVertex())))) {
						
						
						System.out.println(elist.get(d).getToVertex().getValue().getName());
						//removing edge after an area is removed
						elist.remove(d);
						
					}
					elist.remove(d);
				}
				
				String nam = vlist.get(i).getValue().getName();
				int x = vlist.get(i).getValue().getX();
				int y = vlist.get(i).getValue().getY();
				String type = vlist.get(i).getValue().getType();
				String cn = vlist.get(i).getValue().getCoursename();
				int yyy = vlist.get(i).getValue().getyear();
				String vn = vlist.get(i).getValue().getVarname();
				
				JOptionPane.showMessageDialog(null, "Name: " + nam + "\nX location: " + x + "\nY location: " +y+ "\nStudent Type: " + type +"\nCourse name: " + cn +"\nYear : " + yyy + "\nUniversity Name: " + vn);
				  alertmessage += 1;
				nam = JOptionPane.showInputDialog("Provide new name of the student?");
				type = JOptionPane.showInputDialog("What is the type of new Area? (UNDERGRAD or HONOURS ").toUpperCase();
				String coursename = JOptionPane.showInputDialog("Provide the course studying by this student");
				int year = Integer.parseInt(JOptionPane.showInputDialog("provide the year of study  of this student"));
				String varname = JOptionPane.showInputDialog("Provide the name of Univerity (only prefix) ");
				x = Integer.parseInt(JOptionPane.showInputDialog("What is the X location of the student? Range [100 -700]"));
				y = Integer.parseInt(JOptionPane.showInputDialog("What is the Y location of the student?  Range [100 -700]"));
				
				Area newObj = new Area(nam, type, varname, coursename, year, x, y);
				
				Vertex<Area> newA = new Vertex<>(newObj);
				
				vlist.set(i, newA);
				JOptionPane.showMessageDialog(null,  name + " has changed their details to and their new name is " + nam);
			     alertmessage += 1;
			}
			else {
				
				JOptionPane.showMessageDialog(null,  "Name Unknown");
				  attemptaccess += 1;
			}
			
			//updating the lists in the graph
			graph = new Graph<>(vlist, elist);
			cv.draw(graph);
		});
		
		
		btnremoveconnection.setOnAction(e -> {
			vlist = (ArrayList<Vertex<Area>>) graph.getVertices();
			elist = (ArrayList<Edge<Area>>) graph.getEdges();
			
			//reading in cost of edge to be removed
			int cost = Integer.parseInt(JOptionPane.showInputDialog("What is the Cost of theses Students you want to remove?"));
			
			int i = 0;
			int a  = -1;
			
			for (int h = 0;h<vlist.size(); h++)
			{
				a = h;
			}
			 
			for(int d=0; d<elist.size(); d++) {
				
				if(cost == elist.get(d).getCost()) {
					//getting an index of edge to be removed
					i = d;
					
					elist.remove(d);
					
				}
				//elist.remove(d);
				//MY OWN CODE UNDER HERE
		     String nam = vlist.get(i).getValue().getName();
 
				String r = vlist.get(i).getValue().getType();
//				int year = vlist.get(a).getValue().getyear();
               String  varn = vlist.get(a).getValue().getName();
				
             	JOptionPane.showMessageDialog(null,  nam  +   " is are no longer connected  to " +varn );
  			  alertmessage += 1;
				
			}
		
			// removing edge
			elist.remove(i);
			graph = new Graph<>(vlist, elist);
			cv.draw(graph);
		});
		
		btnhelp.setOnAction(e ->{
			help = new JFrame("Help");
			setHelpFrame();
		});
		
		btndetails.setOnAction(e ->{
			vlist = (ArrayList<Vertex<Area>>) graph.getVertices();
			elist = (ArrayList<Edge<Area>>) graph.getEdges();
			
			//reading in form GUI
			String name = JOptionPane.showInputDialog("Please provide Name of the Student you want to see the Details").toUpperCase();
			
			int i = -1;
			
			for(int v=0; v<vlist.size(); v++) {
				
				if(name.equals(vlist.get(v).getValue().getName().toUpperCase())) {
					
					i = v;
					
				}
				
			}
	            if(i!=-1) {
				
				for(int d=0; d<elist.size(); d++) {
					
					if((vlist.get(i).equals(elist.get(d).getToVertex()) || (vlist.get(i).equals(elist.get(d).getFromVertex())))) {
						
						
						System.out.println(elist.get(d).getToVertex().getValue().getName());
						//removing edge after an area is removed
						elist.remove(d);
						
					}
					elist.remove(d);
				}
				
				String nam = vlist.get(i).getValue().getName();
				int x = vlist.get(i).getValue().getX();
				int y = vlist.get(i).getValue().getY();
				String type = vlist.get(i).getValue().getType();
				String cc = vlist.get(i).getValue().getCoursename();
				int yy = vlist.get(i).getValue().getyear();
				String varn = vlist.get(i).getValue().getVarname();
				
				
				JOptionPane.showMessageDialog(null, "Name: " + nam + "\nX location: " + x + "\nY location: " +y+ "\nStudent Type: " + type + "\nCourse Name: " + cc+"\nUniversity name: " + varn + "\nYear: " +yy);
				  alertmessage += 1;
				 // attemptaccess += 1;
			}
			else {
				
				JOptionPane.showMessageDialog(null,  "Name Unknown");
				
			}
			
		});
		
		btnCalc.setOnAction(e ->{

			int add = 0;
			int minus = 0;
			int tot = 0;
			
			for(int d=0; d<elist.size(); d++) {
				
				  
				if((elist.get(d).getFromVertex().getValue().getType().equals("UNDERGRAD"))|| (elist.get(d).getToVertex().getValue().getType().equals("HONOURS")) || (elist.get(d).getFromVertex().getValue().getType().equals("HONOURS"))|| (elist.get(d).getToVertex().getValue().getType().equals("UNDERGRAD")) ) {
					
					tot = tot + elist.get(d).getCost();
					
				}
				else {
					
					tot = tot + elist.get(d).getCost();
					
				}
				
			}
			
			JOptionPane.showMessageDialog(null, "Total distance generated: " + tot +"\nAlert Messages: " +alertmessage +"\nNumber of Attempt: " +attemptaccess);
		});
		
		gp.setVgap(20);
		gp.setHgap(20);


		gp.add(btnaddGrad, 5, 8);
		gp.add(btnaddHon, 5, 9);
	//	gp.add(btnaddMast, 5, 10);
		gp.add(btnaddedge,5,10);
		gp.add(btnUpdatestudent, 5, 11);
		gp.add(btnremovestudent, 5, 12);
		gp.add(btnremoveconnection, 5, 13);
        gp.add(btnhelp, 5, 14);
        gp.add(btndetails, 5, 15);
        gp.add(btnCalc, 5, 16);
		
		
		bp.setLeft(gp);
		bp.setRight(cv);
		
		
		getChildren().addAll(bp);
		
		read();
	}
	public void setHelpFrame() {
		help.setSize(800, 800);
		help.setBackground(Color.YELLOW);
		help.setResizable(true);
		JTextArea helpMenu = new JTextArea(); 
		helpMenu.setBackground(Color.CYAN);
		helpMenu.setFont(helpMenu.getFont().deriveFont(22f));
		helpMenu.setEditable(false);
		helpMenu.setText("--------------------------------------------------------------------------------------"
			+ "\n\tWelcome to the Social-Pane"
			+ "\n\n      To input the Course name of a student it should be a prefix"
			+ "\n For Examples : Computer Science  : CS."
			+ "\n                            Accounting Science : ACCS"
			+"\n                             Business Management : BCOM"
			+"\n                              LAW : LLB"
			+ "\n--------------------------------------------------------------------------------------"
			+ "\n          To Input the Uniiversity name it should be Prefix"
			+ "\n For Examples : Univeristy of johannesburg : UJ"
			+ "\n                            University of Venda   : UNIVEN"
			+ "\n                               University of Limpopo : UL"
			+ "\n                             University of Cape town : UCT   "
			+ "\n                                          "
			+ "\n--------------------------------------------------------------------------------------"
			+ "\n        FOr those students who already online "
			+ "\n You have to click button for suggest so that you could see their details   "
			+ "\n   Inorder for you to be able to connect to them"
			+ "\n--------------------------------------------------------------------------------------");
		help.add(helpMenu);
		help.setVisible(true);	
	}
	
	public void read() {
		String line = "";
		
		while(scanner.hasNext()) {
			line = scanner.nextLine();
			st = new StringTokenizer(line,"#");
			
			String the_obj = st.nextToken();
			st_2 = new StringTokenizer(the_obj);
	 
			String type = st_2.nextToken();
			switch(type) {
			case post:{

			}
				break;
			case studentunder:{
				vlist = (ArrayList<Vertex<Area>>) graph.getVertices();
				elist = (ArrayList<Edge<Area>>) graph.getEdges();
				
				
				String name = st_2.nextToken();
				String typ = st_2.nextToken();
				String cco = st_2.nextToken();
				int hh = Integer.parseInt(st_2.nextToken());
				int xv = Integer.parseInt(st_2.nextToken());
				int yv = Integer.parseInt(st_2.nextToken());
			
	
				Area cs = new Area(name, "UNDERGRAD","UJ",cco,hh,xv, yv);
			 
				
				Vertex<Area> mine = new Vertex<>(cs, 10);	
				vlist.add(mine);
				
				//updating the lists in the graph
				graph = new Graph<>(vlist, elist);
				cv.draw(graph);
			
				break;
			}
		}
		}
		
	}
}
