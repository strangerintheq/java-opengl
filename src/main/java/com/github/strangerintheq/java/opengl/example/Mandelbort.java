package com.github.strangerintheq.java.opengl.example;

import javax.media.opengl.GL4;
import javax.media.opengl.GLAutoDrawable;
import javax.swing.SwingUtilities;
import com.github.strangerintheq.java.opengl.core.App;
import com.github.strangerintheq.java.opengl.core.Primitives;
import com.github.strangerintheq.java.opengl.core.geometry.Geometry;
import com.github.strangerintheq.java.opengl.core.mouse.Mouse2d;

public class Mandelbort extends App {

    private MandelbortProgram fractal;
    private Geometry quad;
    private Mouse2d mouse;

    @Override
    protected void render() {
        fractal.enable();
//        fractal.time.set(time());
        fractal.zoom.set(mouse.zoom);
        fractal.iterations.set(256);
        fractal.resolution.set(width, height);
        fractal.center.set(mouse.centerX, mouse.centerY);
        quad.enable();
        quad.drawTriangleStrip();
        quad.disable();
        fractal.disable();
    }

    public void init(GLAutoDrawable drawable) {
        GL4 gl = drawable.getGL().getGL4();
        fractal = new MandelbortProgram(gl);
        quad = new Geometry(gl, Primitives.UNIT_QUAD);
        mouse = new Mouse2d(0,0, 1.5, width, height);
        canvas.addMouseMotionListener(mouse);
        canvas.addMouseListener(mouse);
        canvas.addMouseWheelListener(mouse);

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        super.reshape(drawable, x, y, width, height);
        mouse.updateResolution(width, height);
    }

    public static void main(String[] args) {
        App frame = new Mandelbort().animate();
        frame.setTitle("Mandelbort");
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }
}
