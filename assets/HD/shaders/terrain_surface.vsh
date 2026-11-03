attribute vec4 a_position;
attribute vec4 a_texCoord;

#ifdef GL_ES
varying lowp vec4 v_texCoord_light_mul;
#else
varying vec4 v_texCoord_light_mul;
#endif

void main()
{
	vec3 pos = a_position.xyz;
	// Extrusao vertical descendente da base do solo (a_texCoord.y == 1.0)
	pos.y -= 25.0 * a_texCoord.y;
	gl_Position = CC_MVPMatrix * vec4(pos, 1.0);
	v_texCoord_light_mul = vec4(a_texCoord.xyz, a_position.w);
}

