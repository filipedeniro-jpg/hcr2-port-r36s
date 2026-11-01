attribute vec4 a_position;
attribute vec2 a_texCoord;

#ifdef GL_ES
varying lowp vec2 v_texCoord;
varying lowp vec2 v_texCoord2;
#else
varying vec2 v_texCoord;
varying vec2 v_texCoord2;
#endif

uniform mat4 u_transform;

void main()
{
	gl_Position = CC_PMatrix * a_position;
	v_texCoord = a_texCoord;
	v_texCoord2 = (u_transform * vec4(a_texCoord, 0, 1)).xy;
}
