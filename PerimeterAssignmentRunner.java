import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Create a counter
        int count = 0;
         for (Point P : s.getPoints()) {
             count= count+1;                       
        }       
        return count;
    }

    public double getAverageLength(Shape s) {
        double AveLength = getPerimeter (s) / getNumPoints (s);
        return AveLength;
    }

    public double getLargestSide(Shape s) {
        double LargestSide = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();         
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update LargestSide
            if (currDist > LargestSide) {
                LargestSide = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }       
        return LargestSide;
    }

    public double getLargestX(Shape s) {
        double LargestX = -1000000.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            if (currPt.getX() > LargestX) {
                LargestX = currPt.getX();
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }             
        return LargestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double perim = 0.0;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if (perim < getPerimeter(s)){
                perim = getPerimeter(s);
            }
        }
        return perim;
    }

    public String getFileWithLargestPerimeter() {  
        File temp = null;    // replace this code
        double largestPerim = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerim = getPerimeter(s);
            if (currPerim > largestPerim){
                largestPerim = currPerim;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        // Number of Points in a shape
        System.out.println("Number of Points = " + getNumPoints (s));
        // Average side length of a shape
        System.out.println("Average side length  = " + getAverageLength(s));
        // Largest side length of a shape
        System.out.println("Largest side length  = " + getLargestSide(s));
        // Largest X
        System.out.println("Largest X  = " + getLargestX(s));        
    }
    
    public void testPerimeterMultipleFiles() {
         System.out.println("Largest Perimeter from Multiple Files  = " + getLargestPerimeterMultipleFiles());        
    }

    public void testFileWithLargestPerimeter() {
         System.out.println("File with Largest Perimeter   = " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.printFileNames();
        pr.testFileWithLargestPerimeter();
        pr.testPerimeterMultipleFiles();
    }
}
