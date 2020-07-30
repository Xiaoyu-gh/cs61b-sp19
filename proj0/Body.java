public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    
    /** Constructor 1 */
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
     * Constructor 2
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

    public double calcDistance(Body b) {
        double dx = b.xxPos - xxPos;
        double dy = b.yyPos - yyPos;
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    public double calcForceExertedBy(Body b) {
        double r = this.calcDistance(b);   // r = distance
        double F = (6.67e-11 * mass * b.mass) / Math.pow(r, 2);
        return F;
    }

    public double calcForceExertedByX(Body b) {
        // dx is singed
        double dx = b.xxPos - xxPos;   
        double F = this.calcForceExertedBy(b);
        double r = this.calcDistance(b);
        return F * dx / r;
    }

    public double calcForceExertedByY(Body b) {
        // dy is singed
        double dy = b.yyPos - yyPos;   
        double F = this.calcForceExertedBy(b);
        double r = this.calcDistance(b);
        return F * dy / r;
    }

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
     * update the position and velocity in time dt
     * and netforce fx and fy
     * @param dt
     * @param fx
     * @param fy
     */
    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        xxVel = xxVel + dt * ax;
        yyVel = yyVel + dt * ay;
        xxPos = xxPos + xxVel * dt;
        yyPos = yyPos + yyVel * dt;
    }

    


}



