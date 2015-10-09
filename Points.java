import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point implements Comparable<Point> {
    private double x;
    private double y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    @Override
    public int compareTo(Point o) {
        return x == o.getX() ? (int)(y - getY()) : (int)(x - o.getX());
    }
}

public class Points {

    public static double distance(Point a, Point b) {
        double xx = a.getX() - b.getX();
        double yy = a.getY() - b.getY();

        return Math.sqrt(xx*xx + yy*yy);
    }

    public static void main(String[] args) throws IOException {
        List<Point> points = new ArrayList<>();
        FileInputStream fs = new FileInputStream(args[0]);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fs));

        while (reader.ready()){
            String[] line = reader.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            points.add(new Point(x, y));
        }

        List<Integer> neib = new ArrayList<>();
        List<Double> radius = new ArrayList<>();

        for (int i = 0 ; i < points.size(); ++i) {
            List<Double> distances = new ArrayList<>();
            for (int j = 0; j < points.size(); ++j ) {
                if(i == j) continue;
                double dist = distance(points.get(i), points.get(j));

                distances.add(dist);
            }

            Collections.sort(distances);
            double rad = distances.get(0);
            radius.add(rad);
            rad *= 2.0;

            int cnt = 0;
            for (double d : distances) {
                if (rad >= d) {
                    ++cnt;
                }
            }

            neib.add(cnt);
        }

        for (int i = 0; i < points.size(); ++i) {
            System.out.printf("%f %d\n", radius.get(i), neib.get(i));
        }
    }
}

