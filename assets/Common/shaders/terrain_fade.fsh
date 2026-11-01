#ifdef GL_ES
varying lowp vec4 v_texCoord_light_mul;
varying lowp float v_blendcoef;
#else
varying vec4 v_texCoord_light_mul;
varying float v_blendcoef;
#endif


void main()
{
	vec4 col = mix(texture2D(CC_Texture2, v_texCoord_light_mul.xy), texture2D(CC_Texture0, v_texCoord_light_mul.xy), v_blendcoef);
    gl_FragColor = col * vec4(v_texCoord_light_mul.zzz, 1.0) * v_texCoord_light_mul.w;
}


