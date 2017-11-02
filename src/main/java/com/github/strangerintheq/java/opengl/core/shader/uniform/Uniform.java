package com.github.strangerintheq.java.opengl.core.shader.uniform;

import javax.media.opengl.GL4;

public abstract class Uniform {

    protected final GL4 gl;
    protected final int id;

    public Uniform(GL4 gl, int programId, String uniformName) {
        this.gl = gl;
        this.id = gl.glGetUniformLocation(programId, uniformName);
        if (this.id == -1)
            throw new IllegalStateException(uniformName + " uniform not found");
    }
}
