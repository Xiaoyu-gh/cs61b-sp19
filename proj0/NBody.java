public class NBody {
    public static double readRadius(String path) {
        In in = new In(path);
        in.readInt();  // no use, just pass
        double radius = in.readDouble();
        return radius;
    }
    /**
     * return a Body array contains all planets
     * @param path
     */
    public static Body[] readBodies(String path) {
        In in = new In(path);
        int num = in.readInt();  // the number of planets
        in.readDouble();

        Body[] allBodies = new Body[num];
        for (int i = 0; i < num; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            allBodies[i] = new Body(xP, yP, xV, yV, m, img);
        }
        return allBodies;
    }
    
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] allBodies = readBodies(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();

        double currentTime = 0;
        while (currentTime <= T) {
            double[] xForces = new double[allBodies.length];
            double[] yForces = new double[allBodies.length];
            for (int i = 0; i < allBodies.length; i++) {
                xForces[i] = allBodies[i].calcNetForceExertedByX(allBodies);
                yForces[i] = allBodies[i].calcNetForceExertedByY(allBodies);
            }

            /* update each body in time period dt 
            while exerted to xForces and yForces */ 
            for (int i = 0; i < allBodies.length; i++) {
                allBodies[i].update(dt, xForces[i], yForces[i]);
            }
            
            StdDraw.picture(0, 0, "images/starfield.jpg");
            // draw all bodies
            for (Body b : allBodies) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            currentTime += dt;
        }
    }
}