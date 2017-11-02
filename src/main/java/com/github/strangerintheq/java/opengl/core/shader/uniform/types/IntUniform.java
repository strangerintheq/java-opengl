package com.github.strangerintheq.java.opengl.core.shader.uniform.types;

import javax.media.opengl.GL4;

import com.github.strangerintheq.java.opengl.core.shader.uniform.Uniform;

public class IntUniform extends Uniform {

    public IntUniform(GL4 gl, int id, String uniformName) {
        super(gl, id, uniformName);
    }

    public void set(int value) {
        gl.glUniform1i(id, value);
    }
}
