package com.github.strangerintheq.java.opengl.core.shader.uniform;

import javax.media.opengl.GL4;

import com.github.strangerintheq.java.opengl.core.shader.uniform.types.DoubleUniform;
import com.github.strangerintheq.java.opengl.core.shader.uniform.types.FloatUniform;
import com.github.strangerintheq.java.opengl.core.shader.uniform.types.IntUniform;
import com.github.strangerintheq.java.opengl.core.shader.uniform.types.Vec2dUniform;
import com.github.strangerintheq.java.opengl.core.shader.uniform.types.Vec2iUniform;

public class Uniforms {

    private final GL4 gl;
    private final int programId;

    public Uniforms(GL4 gl, int programId) {
        this.gl = gl;
        this.programId = programId;
    }

    public DoubleUniform doubleUniform(String name) {
        return new DoubleUniform(gl, programId, name);
    }
    public FloatUniform floatUniform(String name) {
        return new FloatUniform(gl, programId, name);
    }
    public Vec2dUniform dvec2Uniform(String name) {
        return new Vec2dUniform(gl, programId, name);
    }

    public IntUniform intUniform(String name) {
        return new IntUniform(gl, programId, name);
    }

    public Vec2iUniform ivec2Uniform(String name) {
        return new Vec2iUniform(gl, programId, name);
    }
}
