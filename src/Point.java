/**
 * Custom Point class that allows for important calculations for the collisions of hitboxes.
 * In particular, it has a static method that detects when two line segments intersect.
 * @author Jonathan Huang
 */
public class Point {
	double x, y;
	
	/**
	 * Constructor for Point
	 * @param xpos x coordinate
	 * @param ypos y coordinate
	 */
	public Point(double xpos, double ypos) {
		x = xpos; y = ypos;
	}
	
	/**
	 * Copy Constructor for Point
	 * @param np Point
	 */
	public Point(Point np) {
		x = np.x;
		y = np.y;
	}
	
	/**
	 * Setter method for x
	 * @param nx x coordinate
	 */
	public void setX(double nx) {
		x = nx;
	}
	
	/**
	 * Setter method for y
	 * @param ny y coordinate
	 */
	public void setY(double ny) {
		y = ny;
	}
	
	/**
	 * Getter method for x
	 * @return x coordinate
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Getter method for y
	 * @return y coordinate
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Checks if a point lies on a line segment.
	 * @param a point A
	 * @param b point B
	 * @param c point C 
	 * @return true if B lies on segment AC, false otherwise.
	 */
	public static boolean onSegment(Point a, Point b, Point c) {
		return (b.x <= Math.max(a.x, c.x) && b.x >= Math.min(a.x, c.x)
				&& b.y <= Math.max(a.y, c.y) && b.y >= Math.min(a.y, c.y));
	}
	
	/**
	 * Checks the orientation of three points.
	 * @param a point A
	 * @param b point B
	 * @param c point C
	 * @return 0 if the points are collinear, 1 if A, B, C form a clockwise orientation, 2 if A, B, C form a counterclockwise orientation
	 */
	public static int orientation(Point a, Point b, Point c) {
		int val = (int)((b.y - a.y) * (c.x - b.x) - (b.x - a.x) * (c.y - b.y));
		
		if(val == 0) return 0;
		
		return (val > 0) ? 1 : 2;
	}
	
	/**
	 * Checks if two line segments intersect.
	 * @param a point A
	 * @param b point B
	 * @param c point C
	 * @param d point D
	 * @return true if segment AB intersect segment CD, false otherwise
	 */
	public static boolean intersect(Point a, Point b, Point c, Point d) {
		int o1 = orientation(a, b, c);
		int o2 = orientation(a, b, d);
		int o3 = orientation(c, d, a);	
		int o4 = orientation(c, d, b);
		
		if(o1 != o2 && o3 != o4) return true;
		
		if(o1 == 0 && onSegment(a, c, b)) return true;
		if(o2 == 0 && onSegment(a, d, b)) return true;
		if(o3 == 0 && onSegment(c, a, d)) return true;
		if(o4 == 0 && onSegment(c, b, d)) return true;
		
		return false;
	}
	
	/**
	 * Checks if this point is within a rectangle.
	 * @param nx top left x coordinate of rectangle
	 * @param ny top left y coordinate of rectangle
	 * @param width of rectangle
	 * @param height of rectangle
	 * @return true if point is within the rectangle, false otherwise
	 */
	public boolean inRectangle(Rectangle r) {
		double nx = r.getPoint().getX();
		double ny = r.getPoint().getY();
		return x >= nx && nx + r.getWidth() >= x && y >= ny && ny + r.getHeight() >= y;
	}
	
	/**
	 * Checks if this point is within a circle
	 * @param nx center x coordinate of circle
	 * @param ny center y coordinate of circle
	 * @param radius of circle
	 * @return true if point is within the circle, false otherwise
	 */
	public boolean inCircle(double nx, double ny, double radius) {
		return (x-nx) * (x-nx) + (y-ny) * (y-ny) <= radius * radius;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
