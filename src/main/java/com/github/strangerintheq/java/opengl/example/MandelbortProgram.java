package com.github.strangerintheq.java.opengl.example;

import javax.media.opengl.GL4;

import com.github.strangerintheq.java.opengl.core.shader.ShaderProgram;
import com.github.strangerintheq.java.opengl.core.shader.uniform.types.DoubleUniform;

import com.github.strangerintheq.java.opengl.core.shader.uniform.types.FloatUniform;
import com.github.strangerintheq.java.opengl.core.shader.uniform.types.IntUniform;
import com.github.strangerintheq.java.opengl.core.shader.uniform.types.Vec2dUniform;
import com.github.strangerintheq.java.opengl.core.shader.uniform.types.Vec2iUniform;
import com.github.strangerintheq.java.opengl.utils.Files;

public class MandelbortProgram extends ShaderProgram {

    private static String vs = Files.read("empty.vertex.glsl");
    private static String fs = Files.read("mandelbort.fragment.glsl");

    public FloatUniform time = uniforms.floatUniform("time");
    public DoubleUniform zoom = uniforms.doubleUniform("zoom");
    public Vec2dUniform center = uniforms.dvec2Uniform("center");
    public IntUniform iterations = uniforms.intUniform("iterations");
    public Vec2dUniform resolution = uniforms.dvec2Uniform("resolution");

    public MandelbortProgram(GL4 gl) {
        super(gl, vs, fs);
    }
}
