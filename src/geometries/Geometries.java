package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Ray;

public class Geometries implements Intersectable {
	private final List<Intersectable> geometries = new LinkedList();

	public Geometries() {
		
	}
	
	public Geometries(Intersectable...geometries) {
		add(geometries);
	}
	
	public void add(Intersectable...geometries) {
		for (Intersectable geom : geometries)
			this.geometries.add(geom);
	}
	
	@Override
	public List<Point> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
