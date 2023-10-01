public class Triangle2 extends TriangleCommon implements Triangle {
    private Point left;
    private double length;
    private boolean upper;

    public Triangle2(Point left, double length, boolean upper) {
        if(length < 0) {
            this.left = new Point(-0.5, -0.29);
            this.length = 1;
            this.upper = true;
        } else {
        this.left = left;
        this.length = length;
        this.upper = upper;
        }
    }

    public Triangle2() {
        left = new Point(-0.5, -0.29);
        length = 1;
        upper = true;
    }

    public Point[] getVertices() {
        double h2;
        if(!this.upper) {
            h2 = -HW2Utils.getHeightFromLengthEdge(this.length);
        } else {
            h2 = HW2Utils.getHeightFromLengthEdge(this.length);
        }

        Point a = left.copy();
        Point b = new Point(this.left.getX()+this.length, this.left.getY());
        Point c = new Point(0,this.left.getY()+h2);
        Point[] arr = {a,b,c};
        return arr;
    }

    public Point getCenter() {
        Point center = HW2Utils.getCenterFromLeftPointLengthEdge(this.left, this.length, this.upper);
        Point p2 = center.copy();
        return p2;
    }

    public double height() {
        return HW2Utils.getHeightFromLengthEdge(length);
    }

    public double getLengthEdge() {
        return length;
    }

    public boolean isUpTriangle() {
        return upper;
    }

    public void setCenter(Point p) {
        if(p != null) {
            Point lef2 = HW2Utils.getLeftPointFromCenterLengthEdge(p, this.length, this.upper);
            this.left = lef2.copy();
        }
    }

    public void updateHeight(double height) {
        Point cen = getCenter();
        if(height > 0) {
            this.length = HW2Utils.getLengthEdgeFromHeight(height);
            setCenter(cen);
        }
    }

    public void inverse() {
        if(!this.upper) {
            this.upper = true;
        }
        else {
            this.upper = false;
        }
    }

    public void updateLengthEdge(double lengthEdge) {
        Point cen = getCenter();
        if(lengthEdge > 0) {
            this.length = lengthEdge;
            setCenter(cen);
        }
    }

    public void scale(double scalePar) {
        if(scalePar > 0) {
            updateLengthEdge(this.length * scalePar);
        }
    }

    public void moveVertical(double delta) {
        Point center = getCenter();
        center.moveVertical(delta);
        setCenter(center);
    }

    public void moveHorizontal(double delta) {
        Point center = getCenter();
        center.moveHorizontal(delta);
        setCenter(center);
    }

    public void move(Point delta) {
        Point center = getCenter();
        center.move(delta);
        setCenter(center);
    }

    public boolean isEqual(Triangle triangle) {
        Point[] arr = triangle.getVertices();

        double height2;
        if(!this.upper) {
            height2 = -HW2Utils.getHeightFromLengthEdge(this.length);
        } else {
            height2 = HW2Utils.getHeightFromLengthEdge(this.length);
        }
        Point a = left.copy();
        Point b = new Point(this.left.getX()+this.length, this.left.getY());
        Point c = new Point(0,this.left.getY()+height2);
        Point[] array = {a,b,c};

        if(arr[0].equals(array[0]) && arr[1].equals(array[1]) && arr[2].equals(array[2])) {
            return true;
        }
        return false;
    }

    public double getArea() {
        return (height()*this.length)/2.0;
    }

    public double getPerimeter() {
        return this.length*3;
    }

}
