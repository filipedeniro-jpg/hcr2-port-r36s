attribute vec4 a_position;
attribute vec2 a_texCoord;

uniform vec3 u_offset;

#ifdef GL_ES
varying lowp vec2 v_texCoord;
#else
varying vec2 v_texCoord;
#endif

void main()
{
	gl_Position = CC_MVPMatrix * vec4(a_position.xyz + u_offset, 1.0);
	v_texCoord = a_texCoord.xy;
}
