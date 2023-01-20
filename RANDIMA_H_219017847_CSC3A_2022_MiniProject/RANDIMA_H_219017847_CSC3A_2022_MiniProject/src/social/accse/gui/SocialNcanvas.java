package social.accse.gui;

import java.util.ArrayList;

import com.jwetherell.algorithms.data_structure.Graph;
import com.jwetherell.algorithms.data_structure.Graph.Edge;
import com.jwetherell.algorithms.data_structure.Graph.Vertex;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import social.module.Area;

public class SocialNcanvas  extends Canvas{
private GraphicsContext gc;
	
	public SocialNcanvas()
	{
		
		setWidth(1000);
		setHeight(800);
	}
	
	public void draw(Graph<Area> g)
	{
		
		gc = getGraphicsContext2D();
		
		ArrayList<Vertex<Area>> vs = (ArrayList<Graph.Vertex<Area>>) g.getVertices();
		ArrayList<Edge<Area>> es = (ArrayList<Graph.Edge<Area>> ) g.getEdges();
		
		gc.setFill(Color.LIGHTSEAGREEN);
		gc.fillRect(80, 0, 1000, 800);
		
		for (int a = 0; a < vs.size();a++)
		{
			
			String name = vs.get(a).getValue().getName();
			String type = vs.get(a).getValue().getType();
			int year = vs.get(a).getValue().getyear();
			int x = vs.get(a).getValue().getX();
			int y = vs.get(a).getValue().getY();
			
			if(type.equals("UNDERGRAD"))
			{
				gc.setFill(Color.GREEN);
				gc.fillOval(x, y, 40, 40);
				gc.strokeText(name, x+1, y-1);
			}
			else if (type.equals("HONOURS"))
			{
				gc.setFill(Color.RED);
				gc.fillRect(x, y, 40, 40);
				gc.strokeText(name, x+1, y-1);
			}
			else if (type.equals("MASTERS"))
			{
				gc.setFill(Color.ORANGE);
				gc.fillOval(x, y, 30, 30);
				gc.strokeText(name, x+1, y-1);
			}
			
		}
		
		for (int b = 0; b < es.size();b++)
		{
			Vertex<Area> from = es.get(b).getFromVertex();
			Vertex<Area> to = es .get(b).getToVertex();
			
			int xf = from.getValue().getX();
			int yf = from .getValue().getY();
			
			int xt = to.getValue().getX();
			int yt = to.getValue().getY();
			
			gc.setFill(Color.BLACK);
			gc.strokeLine(xf, yf, xt, yt);
			
			gc.strokeText(" " + es.get(b).getCost(), (xf+xt)/2, (yf+yt)/2);
		}
	}

}
