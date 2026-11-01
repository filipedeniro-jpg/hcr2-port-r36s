attribute vec4 a_position;
attribute vec2 a_texCoord;

#ifdef GL_ES
varying lowp vec2 v_texCoord;
varying lowp vec2 v_texTilt;
#else
varying vec2 v_texCoord;
varying vec2 v_texTilt;
#endif

void main()
{
	gl_Position = CC_PMatrix * a_position;
	v_texCoord = a_texCoord.xy;
	v_texTilt = vec2(32.0*v_texCoord.x - 1.2*(v_texCoord.y - 0.66), 0.25);
}
