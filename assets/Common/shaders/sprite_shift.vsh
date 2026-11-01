attribute vec4 a_position;
attribute vec2 a_texCoord;

#ifdef GL_ES
varying lowp vec2 v_texCoord;
#else
varying vec2 v_texCoord;
#endif

uniform vec2 u_shift;

void main()
{
	gl_Position = CC_PMatrix * a_position;
	v_texCoord = a_texCoord + u_shift;
}
