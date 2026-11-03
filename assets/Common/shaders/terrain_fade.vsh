attribute vec4 a_position;
attribute vec4 a_texCoord;

uniform vec4 u_params; // bottom position offset (x, y z) + bottom y-tiling (w)
uniform vec2 u_depthfade;	// fade length, fade start y

#ifdef GL_ES
varying lowp vec4 v_texCoord_light_mul;
varying lowp float v_blendcoef;
#else
varying vec4 v_texCoord_light_mul;
varying float v_blendcoef;
#endif

void main()
{
	float fator_fundo = a_texCoord.y;
	vec3 pos = a_position.xyz;
	// Extrusao vertical descendente do solo para preencher a base da tela
	pos.y -= 25.0 * fator_fundo;
	gl_Position = CC_MVPMatrix * vec4(pos, 1.0);

	v_texCoord_light_mul = vec4(a_texCoord.xyz, a_position.w);
	v_texCoord_light_mul.y = a_texCoord.y * 4.0;
	v_blendcoef = 1.0 - fator_fundo;
}
