/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics2D.Objects;

import Physics2D.Vector2;
import World2D.Objects.PlanetDisplay;
import World2D.Objects.DisplayObject;
import java.awt.Color;

/**
 *
 * @author bowen
 */
public class Star extends PointBody {
    
    public PlanetDisplay displayComponent;
    private double radius;
    final private static Color color = Color.getHSBColor(16F/360, 100F/100, 100F/100);
    
    public Star(String name, double mass, double radius) {
        super(mass);
        this.radius = radius;
        displayComponent = new PlanetDisplay(name, color, radius);
        update();
    }

    public Star(String name, Vector2 position, double mass, double radius) {
        super(position, mass);
        this.radius = radius;
        displayComponent = new PlanetDisplay(name, color, radius);
        update();
    }

    public Star(String name, Vector2 position, Vector2 velocity, double mass, double radius) {
        super(position, velocity, mass);
        this.radius = radius;
        displayComponent = new PlanetDisplay(name, color, radius);
        update();
    }
    @Override
    public final void update() {
        displayComponent.setPos(position().get(0), position().get(1));
        displayComponent.setVel(velocity().get(0), velocity().get(1));
    }
    public void setColour(Color color) {
        displayComponent.setColor(color);
    }
    @Override
    public DisplayObject getDisplayObject() {
        return displayComponent;
    }
    @Override
    public Star clone() {
        Star newTestObject = new Star("Clone", this.position(), this.velocity(), this.mass(), this.radius);
        return newTestObject;
    }
}