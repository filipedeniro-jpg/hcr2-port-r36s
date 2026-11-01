attribute vec4 a_position;
attribute vec2 a_texCoord;

#ifdef GL_ES
varying lowp vec2 v_texCoord;
#else
varying vec2 v_texCoord;
#endif

void main()
{
	vec4 clip = CC_PMatrix * a_position;
	gl_Position = vec4(clip.w*a_position.x, clip.w*a_position.y, clip.z, clip.w);
	v_texCoord = a_texCoord;
}
