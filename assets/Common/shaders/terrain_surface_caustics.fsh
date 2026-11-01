#ifdef GL_ES
varying lowp vec4 v_texCoord_light_mul;
varying lowp float v_blendcoef;
#else
varying vec4 v_texCoord_light_mul;
varying float v_blendcoef;
#endif

uniform float u_shift;

float pcurve(float x)
{
	return 9.5 * x * pow (1.0 - x, 3.0);
}

void main()
{
	float mask = pcurve(v_texCoord_light_mul.y);

	vec4 col = mix(texture2D(CC_Texture2, v_texCoord_light_mul.xy), texture2D(CC_Texture0, v_texCoord_light_mul.xy), v_blendcoef);
    col *= vec4(v_texCoord_light_mul.zzz, 1.0) * v_texCoord_light_mul.w;
    col.xyz += texture2D(CC_Texture1, v_texCoord_light_mul.xy + u_shift*vec2(0.05)).xyz*col.w * mask * v_blendcoef;

    gl_FragColor = col;
}
