package com.github.strangerintheq.java.opengl.core.geometry;

import com.jogamp.common.nio.Buffers;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL4;

public class Geometry {

    private final GL4 gl;
    private int[] id = new int[1];
    private int size;

    public Geometry(GL4 gl, float[] vertices) {
        this.gl = gl;
        size = vertices.length;
        createVBO();
        bind();
        fill(vertices);
    }

    private void createVBO() {
        gl.glGenBuffers(1, id, 0);
    }

    public void bind() {
        gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, id[0]);
    }

    public void fill(float[] quadVertices) {
        gl.glBufferData(
            GL2.GL_ARRAY_BUFFER,
            quadVertices.length * 4,
            asFloatBuffer(quadVertices),
            GL2.GL_STATIC_DRAW
        );
    }

    private Buffer asFloatBuffer(float[] quadVertices) {
        return Buffers.newDirectFloatBuffer(quadVertices).rewind();
    }

    public void enable() {
        bind();
// not needed when using layout qualifier in shader
//        gl.glBindAttribLocation(id[0], 0, "VertexPosition");
        gl.glEnableVertexAttribArray(0);
        gl.glVertexAttribPointer( 0, 2, GL4.GL_FLOAT,  false, 0, 0 );
    }

    public void disable (){
        gl.glDisableVertexAttribArray(0);
    }

    public void delete() {
        gl.glDeleteBuffers(1, id, 0);
    }

    public void drawTriangleStrip() {
        gl.glDrawArrays(GL2.GL_TRIANGLE_STRIP, 0, size);
    }
}
