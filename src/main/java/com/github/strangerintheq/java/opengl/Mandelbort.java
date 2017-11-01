package com.github.strangerintheq.java.opengl;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.SwingUtilities;

import com.github.strangerintheq.java.opengl.core.App;
import com.github.strangerintheq.java.opengl.core.Shader;
import com.github.strangerintheq.java.opengl.core.VBO;
import com.github.strangerintheq.java.opengl.utils.Files;
import com.jogamp.opengl.util.Animator;

public class Mandelbort extends App {

    private Shader shader;
    private VBO quad;
    private long started;
    private int width;
    private int height;
    private double zoom = 1;
    private double x = 0;
    private double y = 0;

    Mandelbort() {
        GLProfile profile = GLProfile.getDefault();
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(capabilities);
        final Animator animator = new Animator(canvas);
        canvas.addGLEventListener(this);
        add(canvas, BorderLayout.CENTER);
        animator.start();
        started = System.currentTimeMillis();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                animator.stop();
                dispose();
                System.exit(0);
            }
        });
    }

    @Override
    protected void render() {
        shader.enable();

        shader.setFloat("time", time());
        shader.setFloat("zoom", 1);
        shader.setInt("iterations", 512);
        shader.setVec2("resolution", width, height);
        shader.setVec2("center", x, y);
        quad.enable();
        quad.drawTriangleStrip();
        gl.glDrawArrays(GL2.GL_TRIANGLE_STRIP, 0, quad.size());
        quad.disable();
        shader.disable();
    }

    private float time() {
        double spent = System.currentTimeMillis() - started;
        return (float) (spent / 1000);
    }

    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        String vs = Files.read("line.vertex.glsl");
        String fs = Files.read("line.fragment.glsl");
        shader = new Shader(gl, vs, fs);
        float[] quadVertices = {
                -1, -1, 0,
                -1, +1, 0,
                +1, -1, 0,
                +1, +1, 0
        };
        quad = new VBO(gl, quadVertices);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void dispose(GLAutoDrawable drawable) { }

    public static void main(String[] args) {
        Mandelbort frame = new Mandelbort();
        frame.setTitle("Mandelbort");
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }
}
