attribute vec4 a_position;
attribute vec2 a_texCoord;

#ifdef GL_ES
varying lowp vec3 v_texCoord_alpha;
varying lowp vec2 v_texCoord;
#else
varying vec3 v_texCoord_alpha;
varying vec2 v_texCoord;
#endif

uniform vec3 u_shift_alpha;

void main()
{
	gl_Position = CC_PMatrix * a_position;
	v_texCoord_alpha = vec3(a_texCoord + u_shift_alpha.xy, u_shift_alpha.z);
	v_texCoord = a_texCoord;
}
