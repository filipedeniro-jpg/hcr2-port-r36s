attribute vec4 a_position;
attribute vec4 a_texCoord;

#ifdef GL_ES
varying lowp vec4 v_texCoord_light_mul;
#else
varying vec4 v_texCoord_light_mul;
#endif

void main()
{
	gl_Position = CC_MVPMatrix * vec4(a_position.xyz, 1.0);
	v_texCoord_light_mul = vec4(a_texCoord.xyz, a_position.w);
	//v_texCoord_light_mul = vec4(a_texCoord.xy, a_texCoord.z * (1.0-0.5*a_texCoord.w*max(1.0-a_texCoord.y,0.0)), a_position.w);
}
