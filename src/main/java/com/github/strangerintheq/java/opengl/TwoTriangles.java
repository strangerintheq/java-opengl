package com.github.strangerintheq.java.opengl;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.SwingUtilities;

import com.jogamp.opengl.util.Animator;

// https://stackoverflow.com/a/19043837/2393786
public class TwoTriangles extends Frame implements GLEventListener {

    private Shader shader;
    private VBO quad;
    private long started;
    int width;
    int height;

    TwoTriangles() {
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

    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        shader.enable();
        quad.enable();
        shader.setFloat("time", time());
        shader.setFloat("zoom", 1);
        shader.setInt("iterations", 128);
        shader.setVec2("resolution", width, height);
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
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
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

    public void dispose(GLAutoDrawable drawable) {
    }

    public static void main(String[] args) {
        TwoTriangles frame = new TwoTriangles();
        frame.setTitle("TwoTriangles");
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }
}
