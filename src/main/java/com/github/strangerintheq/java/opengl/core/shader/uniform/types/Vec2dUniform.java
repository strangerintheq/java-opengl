package com.github.strangerintheq.java.opengl.core.shader.uniform.types;

import javax.media.opengl.GL4;

import com.github.strangerintheq.java.opengl.core.shader.uniform.Uniform;

public class Vec2dUniform extends Uniform {

    public Vec2dUniform(GL4 gl, int id, String uniformName) {
        super(gl, id, uniformName);
    }

    public void set(double a, double b) {
        gl.glUniform2d(id, a, b);
    }
}
