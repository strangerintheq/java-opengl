package com.github.strangerintheq.java.opengl.core.shader.uniform;

import javax.media.opengl.GL4;

public class DoubleUniform extends Uniform {

    public DoubleUniform(GL4 gl, int id, String uniformName) {
        super(gl, id, uniformName);
    }

    public void set(double value) {
        gl.glUniform1d(id, value);
    }
}
