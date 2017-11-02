package com.github.strangerintheq.java.opengl.core.shader.uniform;

import javax.media.opengl.GL4;

public class FloatUniform extends Uniform {

    public FloatUniform(GL4 gl, int id, String uniformName) {
        super(gl, id, uniformName);
    }

    public void set(float value) {
        gl.glUniform1f(id, value);
    }
}
