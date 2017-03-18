/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics2D.Integrators;

import Physics2D.Objects.PointBody;
import Physics2D.Vector2;
import Physics2D.Vectors2;

/**
 *
 * @author bowen
 */
public class Symplectic1 implements Integrator {
    
    private Vector2[] accelerations;
    
    @Override
    public void apply(PointBody[] bodies, double dt) {
        if (dt > 0) {
            accelerations = Symplectic.applySympleticVelocityStep(bodies, 1, dt);
            Symplectic.applySympleticPositionStep(bodies, 1, dt);
        } else {
            Symplectic.applySympleticPositionStep(bodies, 1, dt);
            accelerations =  Symplectic.applySympleticVelocityStep(bodies, 1, dt);
        }
        
    }

    @Override
    public Vector2[] getCurrentAccelerations() {
        return accelerations;
    }
    
    @Override
    public PointBody[] get(PointBody[] bodies, double dt, int steps) {
        PointBody[] bodiesClone = new PointBody[bodies.length];
        
        for (int i=0; i<bodies.length; i++) {
            bodiesClone[i] = bodies[i].clone();
        }
        for (int i=0; i<steps; i++) {
            apply(bodiesClone, dt);
        }
        return bodiesClone;
    }
    
    @Override
    public Vector2[][] getFuture(PointBody[] bodies, double dt, int steps) {
        PointBody[] bodiesClone = new PointBody[bodies.length];
        
        for (int i=0; i<bodies.length; i++) {
            bodiesClone[i] = bodies[i].clone();
        }
        
        Vector2[][] positionTime = new Vector2[bodiesClone.length][steps];
        
        for (int i=0; i<steps; i++) {
            apply(bodiesClone, dt);
            for (int n=0; n<bodiesClone.length; n++) {
                positionTime[n][i] = bodiesClone[n].position();
            }
        }
        return positionTime;
        
    }
}
