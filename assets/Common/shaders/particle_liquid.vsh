attribute vec4 a_position;
attribute vec2 a_texCoord;

uniform float u_pointsize;
/*
#ifdef GL_ES
varying lowp vec2 v_velocity;
#else
varying vec2 v_velocity;
#endif
*/
void main()
{
    gl_Position = CC_PMatrix * a_position;
	gl_PointSize = 2.0*u_pointsize;
//	v_velocity = a_texCoord;
}
