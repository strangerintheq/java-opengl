#version 400

layout (location = 0) out vec4 FragColor;

uniform int iterations;
uniform dvec2 resolution;
uniform dvec2 center;
uniform float time;
uniform double zoom;

// multiply complex numbers
dvec2 mul(dvec2 A, dvec2 B) {
    return vec2(A.x * B.x - A.y * B.y, 2.0 * A.x * B.y);
}

// http://www.iquilezles.org/www/articles/mset_smooth/mset_smooth.htm
vec3 color(int i, dvec2 z) {
    float it = float(i);
    float sl = it - log2(log2(float(dot(z,z)))) + 4.0;
    float al = smoothstep(-0.1, 0.0, 1.0);
    it = mix(it, sl, al);
    return 0.5 + 0.5 * cos(3.0 + it * 0.05  + vec3(0.0, 0.5, 1.0));
}

vec3 fractal(dvec2 c) {
    dvec2 z = c;
    for (int i = 0; i < 1024; i++) {
    	if (i == iterations)
    	    break;
    	if (dot(z, z) > 4.0)
    	    return color(i, z);
    	z = mul(z, z) + vec2(sin(time/100.), cos(time/100.));
    }
    return vec3(0.);
}

void main(void) {
    dvec2 c = gl_FragCoord.xy;
	c = -resolution + 2.0 * c;
    c *= zoom/resolution.y;
    c += center;
	FragColor = vec4(fractal(c), 1.0);
}
