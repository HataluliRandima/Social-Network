package social.module;
/**
 * @author hatal
 *
 */
public class Area implements Comparable<Area> {
	
	private String name;
	private String type;
	private String varname;
	private String coursename;
	private int year;
	private int X;
	private int Y;
	
	

	public Area(String name, String type,String varname,String coursename,int year, int x, int y) {
		super();
		this.name = name;
		this.type = type;
		this.varname=varname;
		this.coursename = coursename;
		this.year = year;
		X = x;
		Y = y;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}

	public String getVarname() {
		return varname;
	}



	public void setVarname(String Varname) {
		this.varname = Varname;
	}
	
	public String getCoursename() {
		return coursename;
	}



	public void setCourseName(String CousreName) {
		this.coursename = CousreName;
	}

	public int getX() {
		return X;
	}



	public void setX(int x) {
		X = x;
	}



	public int getY() {
		return Y;
	}



	public void setY(int y) {
		Y = y;
	}

	public int getyear() {
		return year;
	}



	public void setyear(int Year) {
		year = Year;
	}


	@Override
	public int compareTo(Area arg0) {
		 
		return 0;
	}

}
