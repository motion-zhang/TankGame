package tank;
import processing.core.PVector;

public class Steering {
  private static final float PI = (float) Math.PI;
  PVector postion;
  PVector velocity;
  PVector lastNonZeroVel;
  float angle;
  float acceptLocRad, slowLocRad, maxSpeed;
  float acceptRotRad, slowRotRad, maxRotSpeed;
  float maxAccel, timeToTarget;

  public Steering(int x, int y){
    postion = new PVector(x, y);
    velocity = new PVector(0,0);
    lastNonZeroVel = velocity.copy();
    angle = 0;
    acceptLocRad = 40;
    slowLocRad = 75;
    maxSpeed = 5;
    acceptRotRad = PI/30;
    slowRotRad = PI/15;
    maxRotSpeed = PI/25;
    maxAccel = (float) 0.4;
    timeToTarget = (float) 0.2;
    }

  float wrapAngle(float angle) {
    if (angle > PI) {
      return angle - 2 * PI;
    }
    if (angle < -PI) {
      return angle + 2 * PI;
    }
    return angle;
  }
}
