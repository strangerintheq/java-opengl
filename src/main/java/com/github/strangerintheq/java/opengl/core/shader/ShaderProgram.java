package com.github.strangerintheq.java.opengl.core.shader;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL4;

public class ShaderProgram {
	
	protected final int id;
	protected final GL4 gl;

	public ShaderProgram(GL4 gl, String vs, String fs) {
		this.gl = gl;
		this.id = gl.glCreateProgram();
		int vertexShader = shader(GL4.GL_VERTEX_SHADER, vs);
		int fragmentShader = shader(GL4.GL_FRAGMENT_SHADER, fs);
		gl.glAttachShader(id, vertexShader);
		gl.glAttachShader(id, fragmentShader);
		gl.glLinkProgram(id);
		gl.glValidateProgram(id);
		IntBuffer intBuffer = IntBuffer.allocate(1);
		gl.glGetProgramiv(id, GL4.GL_LINK_STATUS, intBuffer);
		if (intBuffer.get(0) == GL4.GL_TRUE)
			return;
		if (id > 0) gl.glDeleteProgram(id);
		throw new IllegalStateException();
	}
	
	public void enable() {
		gl.glUseProgram(id);
	}

	public void disable() {
		gl.glUseProgram(0);
	}

	private int shader(int type, String src) {
		int id = gl.glCreateShader(type);
		gl.glShaderSource(id, 1, new String[] {src}, null, 0);
		gl.glCompileShader(id);
	    int status = getIntFromShader(id, GL4.GL_COMPILE_STATUS);
	    if (status == 0) {
	    	int logLength = getIntFromShader(id, GL4.GL_INFO_LOG_LENGTH);
	        ByteBuffer infoLog = ByteBuffer.allocate(logLength);
	        IntBuffer intValue = IntBuffer.allocate(1);
	        gl.glGetShaderInfoLog(id, logLength, intValue, infoLog);
	        int actualLength = intValue.get();
	        byte[] infoBytes = new byte[actualLength];
	        infoLog.get(infoBytes);
	        if (id > 0) gl.glDeleteShader(id);
	        throw new IllegalStateException(new String(infoBytes));
	    }
		return id;
	}
	
	private int getIntFromShader(int id, int parameter) {
        IntBuffer intValue = IntBuffer.allocate(1);
        gl.glGetShaderiv(id, parameter, intValue);
        return intValue.get();	
	}
}
