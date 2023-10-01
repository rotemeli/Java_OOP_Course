public class Triangle1 extends TriangleCommon implements Triangle {
    private Point center;
    private double height;

    public Triangle1(Point center, double height) {
        if(height == 0) {
            this.center = new Point(0, 0);
            this.height = Math.sqrt(3) / 2;
        } else {
            this.center = center;
            this.height = height;
        }
    }

    public Triangle1() {
        center = new Point(0,0);
        height = Math.sqrt(3) / 2;
    }

    public Point[] getVertices() {
        double l1 = HW2Utils.getLengthEdgeFromHeight(Math.abs(this.height));
        boolean flag;
        if(this.height < 0) {
            flag = false;
        } else {
            flag = true;
        }

        Point a = HW2Utils.getLeftPointFromCenterLengthEdge(center, l1, flag);
        Point b = new Point(a.getX()+l1, a.getY());
        Point c = new Point(0,a.getY()+height);
        Point[] arr = {a,b,c};
        return arr;
    }

    public Point getCenter() {
        Point p1 = center.copy();
        return p1;
    }

    public double height() {
        return Math.abs(height);
    }

    public double getLengthEdge() {
        return HW2Utils.getLengthEdgeFromHeight(Math.abs(height));
    }

    public boolean isUpTriangle() {
        if(height > 0) {
            return true;
        }
        return false;
    }

    public void setCenter(Point p) {
        if(p != null) {
            this.center = p.copy();
        }
    }

    public void updateHeight(double height) {
        if(height > 0) {
            this.height = height;
        }
    }

    public void inverse() {
        this.height = -height;
    }

    public void updateLengthEdge(double lengthEdge) {
        if(lengthEdge > 0) {
            this.height = HW2Utils.getHeightFromLengthEdge(lengthEdge);
        }
    }

    public void scale(double scalePar) {
        if(scalePar > 0) {
            updateLengthEdge(getLengthEdge()*scalePar);
        }
    }

    public void moveVertical(double delta) {
        this.center.moveVertical(delta);
    }

    public void moveHorizontal(double delta) {
        this.center.moveHorizontal(delta);
    }

    public void move(Point delta) {
        this.center.move(delta);
    }

    public boolean isEqual(Triangle triangle) {
        Point[] arr = triangle.getVertices();

        double len1 = HW2Utils.getLengthEdgeFromHeight(Math.abs(height));
        boolean flag;
        if(height < 0) {
            flag = false;
        } else {
            flag = true;
        }

        Point a = HW2Utils.getLeftPointFromCenterLengthEdge(this.center, len1, flag);
        Point b = new Point(a.getX()+len1, a.getY());
        Point c = new Point(0,a.getY()+this.height);
        Point[] array = {a,b,c};


        if(arr[0].equals(array[0]) && arr[1].equals(array[1]) && arr[2].equals(array[2])) {
            return true;
        }
        return false;
    }

    public double getArea() {
        return (this.height()*getLengthEdge())/2.0;
    }

    public double getPerimeter() {
        return getLengthEdge()*3;
    }

}

