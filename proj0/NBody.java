/**
 * Class NBody runs simulation.
 */
public class NBody {
    /**
     * Given a file path, return the radius of that universe
     * @param path the path of planets info file
     */
    public static double readRadius(String path) {
        In in = new In(path);
        in.readInt();  // no use, just pass
        double radius = in.readDouble();
        return radius;
    }

    /**
     * Return an array of Bodys contains all planets
     * @param path the path of planets info file
     */
    public static Body[] readBodies(String path) {
        In in = new In(path);
        // The number of planets.
        int num = in.readInt();  
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
    
    /**
     * The main method runs the simulation.
     * @param args command line parameter contains
     * totall time T, time interval dt, image filename.
     */
    public static void main(String[] args) {
        /* Get initial info from command line parameters */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        
        // Get all the information into this array of Bodys.
        Body[] allBodies = readBodies(filename);

        StdDraw.enableDoubleBuffering();
        double radius = readRadius(filename);
        // Set the scale of the canvas.
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();

        double currentTime = 0;
        /**
         * Loop until current time reaches T
         * within each iteration, update each body and draw it.
         * 
         */
        while (currentTime <= T) {
            double[] xForces = new double[allBodies.length];
            double[] yForces = new double[allBodies.length];
            for (int i = 0; i < allBodies.length; i++) {
                xForces[i] = allBodies[i].calcNetForceExertedByX(allBodies);
                yForces[i] = allBodies[i].calcNetForceExertedByY(allBodies);
            }

            /* Update each body in dt while 
            * exerted to xForces and yForces. */ 
            for (int i = 0; i < allBodies.length; i++) {
                allBodies[i].update(dt, xForces[i], yForces[i]);
            }
            
            // Draw the background.
            StdDraw.picture(0, 0, "images/starfield.jpg");
            
            // Draw each body.
            for (Body b : allBodies) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            currentTime += dt;
        }

        /* Print out the final state of the universe
        * (i.e. all planets) when time reaches T. */  
        StdOut.printf("%d\n", allBodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allBodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          allBodies[i].xxPos, allBodies[i].yyPos, allBodies[i].xxVel,
                          allBodies[i].yyVel, allBodies[i].mass, allBodies[i].imgFileName);
        }
    }
}