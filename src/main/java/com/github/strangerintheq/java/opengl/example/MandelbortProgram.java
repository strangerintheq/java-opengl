package com.github.strangerintheq.java.opengl.example;

import javax.media.opengl.GL4;

import com.github.strangerintheq.java.opengl.core.shader.ShaderProgram;
import com.github.strangerintheq.java.opengl.core.shader.uniform.DoubleUniform;
import com.github.strangerintheq.java.opengl.core.shader.uniform.FloatUniform;
import com.github.strangerintheq.java.opengl.core.shader.uniform.IntUniform;
import com.github.strangerintheq.java.opengl.core.shader.uniform.Vec2dUniform;
import com.github.strangerintheq.java.opengl.utils.Files;

public class MandelbortProgram extends ShaderProgram {

    private static String vs = Files.read("empty.vertex.glsl");
    private static String fs = Files.read("mandelbort.fragment.glsl");

    public FloatUniform time;
    public DoubleUniform zoom;
    public Vec2dUniform center;
    public IntUniform iterations;
    public Vec2dUniform resolution;

    public MandelbortProgram(GL4 gl) {
        super(gl, vs, fs);
//        time = new FloatUniform(gl, id, "time");
//        zoom = new DoubleUniform(gl, id, "zoom");
//        center = new Vec2dUniform(gl, id, "center");
//        iterations = new IntUniform(gl, id, "iterations");
//        resolution = new Vec2dUniform(gl, id, "resolution");
    }
}
