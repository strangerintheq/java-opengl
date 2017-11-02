package com.github.strangerintheq.java.opengl.example;

import javax.media.opengl.GL4;
import javax.media.opengl.GLAutoDrawable;
import javax.swing.SwingUtilities;
import com.github.strangerintheq.java.opengl.core.App;
import com.github.strangerintheq.java.opengl.core.Primitives;
import com.github.strangerintheq.java.opengl.core.geometry.Geometry;

public class Mandelbort extends App {

    private MandelbortProgram fractal;
    private Geometry quad;

    private double zoom = 1.5;
    private double x = 0;
    private double y = 0;

    @Override
    protected void render() {
        fractal.enable();
//        fractal.time.set(time());
        fractal.zoom.set(zoom);
        fractal.iterations.set(128);
        fractal.resolution.set(width, height);
        fractal.center.set(x, y);
        quad.enable();
        quad.drawTriangleStrip();
        quad.disable();
        fractal.disable();
    }

    public void init(GLAutoDrawable drawable) {
        GL4 gl = drawable.getGL().getGL4();
        fractal = new MandelbortProgram(gl);
        quad = new Geometry(gl, Primitives.UNIT_QUAD);
    }

    public static void main(String[] args) {
        App frame = new Mandelbort().animate();
        frame.setTitle("Mandelbort");
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }
}
