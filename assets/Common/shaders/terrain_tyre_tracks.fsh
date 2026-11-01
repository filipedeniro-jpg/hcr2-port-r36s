#ifdef GL_ES
varying lowp vec4 v_texCoord_light_mul;
#else
varying vec4 v_texCoord_light_mul;
#endif


void main()
{
	gl_FragColor = texture2D(CC_Texture0, v_texCoord_light_mul.xy) * v_texCoord_light_mul.w;
}
