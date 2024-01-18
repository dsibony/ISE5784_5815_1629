package geometries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import primitives.Point;
import primitives.Ray;

public class Geometries implements Intersectable {
	private final List<Intersectable> geometries = new LinkedList<Intersectable>();

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
		List<Point> intersections = null;
		for (Intersectable geom : geometries) {
			List<Point> geoIntersections = geom.findIntersections(ray);
			if (geoIntersections != null) {
				if (intersections == null)
					intersections = new ArrayList<Point>(geoIntersections);
				intersections.addAll(geoIntersections);
			}
		}
		return intersections;
	}
	
}
