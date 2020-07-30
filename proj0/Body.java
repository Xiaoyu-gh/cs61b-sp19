/**
 * Class Body represents a planet in the universe.
 */
public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    
    /**
     * Constructor 1
     */
    public Body(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img; 
    }
    
    /**
     * Constructor 2: copy from a object Body
     * @param b
     */
    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /**
     * Calculates the distance between two bodies.
     */
    public double calcDistance(Body b) {
        double dx = b.xxPos - xxPos;
        double dy = b.yyPos - yyPos;
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * Calculate the force exerted on
     * this body by the given body.
     */
    public double calcForceExertedBy(Body b) {
        double r = this.calcDistance(b);   // r = distance
        double F = (6.67e-11 * mass * b.mass) / Math.pow(r, 2);
        return F;
    }

    /**
     * Calculate the force exerted in X direction.
     */
    public double calcForceExertedByX(Body b) {
        // dx is singed
        double dx = b.xxPos - xxPos;   
        double F = this.calcForceExertedBy(b);
        double r = this.calcDistance(b);
        return F * dx / r;
    }

    /**
     * Calculate the force exerted in Y direction.
     */
    public double calcForceExertedByY(Body b) {
        // dy is singed
        double dy = b.yyPos - yyPos;   
        double F = this.calcForceExertedBy(b);
        double r = this.calcDistance(b);
        return F * dy / r;
    }

    /**
     * Calculate the net X force exerted by all bodies 
     * in that array upon the current Body.
     * @param bodys an array of objects Body
     * @return the net X force
     */
    public double calcNetForceExertedByX(Body[] bodys) {
        double FxNet = 0;
        for (Body b : bodys) {  // note: must declare Body 
            if (this.equals(b)) {
                continue;
            }
            double Fx = this.calcForceExertedByX(b);
            FxNet += Fx;
        }
        return FxNet;
    }

    /**
     * Calculate the net Y force exerted by all bodies 
     * in that array upon the current Body.
     * @param bodys an array of objects Body
     * @return the net Y force
     */
    public double calcNetForceExertedByY(Body[] bodys) {
        double FyNet = 0;
        for (Body b : bodys) {
            if (this.equals(b)) {
                continue;
            }
            double Fy = this.calcForceExertedByY(b);
            FyNet += Fy;
        }
        return FyNet;
    }

    /**
     * Update the position and velocity in small 
     * time period dt and netforce fx and fy
     * @param dt the small time period
     * @param fx net X force
     * @param fy net Y force
     */
    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        xxVel = xxVel + dt * ax;
        yyVel = yyVel + dt * ay;
        xxPos = xxPos + xxVel * dt;
        yyPos = yyPos + yyVel * dt;
    }

    /**
     * Draw the Body's images at its current position
     * using StdDraw API.
     */
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
		StdDraw.show();
    }
}



