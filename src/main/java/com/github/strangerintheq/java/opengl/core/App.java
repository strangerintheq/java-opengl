package com.github.strangerintheq.java.opengl.core;

import com.jogamp.opengl.util.Animator;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class App extends Frame implements GLEventListener {

    private final long start = System.currentTimeMillis();
    protected int width;
    protected int height;
    protected GLCanvas canvas;
    private Animator animator;
    protected GL4 gl;

    public App() {
        GLProfile profile = GLProfile.getDefault();
        GLCapabilities capabilities = new GLCapabilities(profile);
        canvas = new GLCanvas(capabilities);
        canvas.addGLEventListener(this);
        add(canvas, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                terminate();
            }
        });
    }

    private void terminate() {
        if (null != animator)
            animator.stop();
        dispose();
        System.exit(0);
    }

    public App animate() {
        animator = new Animator(canvas);
        animator.start();
        return this;
    }

    public float time() {
        return (float) ((System.currentTimeMillis() - start) / 1000);
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        gl = glAutoDrawable.getGL().getGL4();
        init();
    }

    protected void init() {}

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {}

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        render();
    }

    protected abstract void render();

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
    }
}
