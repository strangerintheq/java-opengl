package com.github.strangerintheq.java.opengl.core.shader.uniform;

import javax.media.opengl.GL4;

public class Vec2iUniform extends Uniform {

    public Vec2iUniform(GL4 gl, int id, String uniformName) {
        super(gl, id, uniformName);
    }

    public void set(int a, int b) {
        gl.glUniform2i(id, a, b);
    }
}
