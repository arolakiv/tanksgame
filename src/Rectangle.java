public class Rectangle {
	private Point p;
	private double width, height;
	
	public Rectangle(Point np, double nw, double nh) {
		p = np;
		width = nw;
		height = nh;
	}
	
	public Rectangle(Rectangle r) {
		p = r.p;
		width = r.width;
		height = r.height;
	}
	
	public Point getPoint() {
		return p;
	}
	
	public void setPoint(Point np) {
		p = np;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public boolean intersect(Rectangle other) {
		for(Point v : other.getVertices()) {
			if(v.inRectangle(this)) {
				return true;
			}
		}
		for(Point v : getVertices()) {
			if(v.inRectangle(other)) {
				return true;
			}
		}
		return false;
	}
	
	public Point[] getVertices() {
		Point[] res = new Point[4];
		
		res[0] = p;
		res[1] = new Point(p.getX() + width, p.getY());
		res[2] = new Point(p.getX() + width, p.getY() + height);
		res[3] = new Point(p.getX(), p.getY() + height);
		
		return res;
	}
	
	public String toString() {
		StringBuffer res = new StringBuffer("");
		for(Point p : getVertices()) {
			res.append(p.toString());
			res.append(" , ");
		}
		return res.toString();
	}
}
