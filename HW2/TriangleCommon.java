public abstract class TriangleCommon implements Triangle {

    private double areaByPoints(Point a, Point b, Point c) {
        return Math.abs((a.getX()*(b.getY()-c.getY()) + b.getX()*(c.getY()-a.getY())
                + c.getX()*(a.getY()-b.getY()))/2.0);
    }

    public boolean contains(Point p) {
        Point array1[] = this.getVertices();
        double PBC = areaByPoints(p, array1[1], array1[2]);
        double PAC = areaByPoints(array1[0], p, array1[2]);
        double PAB = areaByPoints(array1[0], array1[1], p);
        return HW2Utils.areEqual((PBC + PAC + PAB), this.getArea());
    }

    public boolean contains(Triangle triangle) {
        Point trArr[] = triangle.getVertices();

        if(this.isEqual(triangle)) {
            return true;
        }

        if((this.contains(trArr[0]) && this.contains(trArr[1]) && this.contains(trArr[2]))
                && triangle.getArea() <= this.getArea()) {
            return true;
        }
        return false;
    }
}
