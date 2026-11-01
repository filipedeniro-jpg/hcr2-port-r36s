#ifndef GL_ES
#define lowp
#endif

attribute vec4 a_position;
attribute vec3 a_texCoord;
varying vec4 v_texCoord_alpha_light;

void main() {
	gl_Position = CC_MVPMatrix * vec4(a_position.xyz, 1.0);
	v_texCoord_alpha_light = vec4(a_texCoord, a_position.w);
}
