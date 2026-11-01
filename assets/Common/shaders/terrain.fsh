#ifdef GL_ES
varying lowp vec4 v_texCoord_light_mul;
#else
varying vec4 v_texCoord_light_mul;
#endif



uniform sampler2D u_texture;

void main()
{
	//gl_FragColor = vec4(v_texCoord_light_mul.xy, 0.0, 1.0);
    gl_FragColor = texture2D(CC_Texture0, v_texCoord_light_mul.xy) * vec4(v_texCoord_light_mul.zzz, 1.0) * v_texCoord_light_mul.w;
    //gl_FragColor = vec4(v_texCoord_light_mul.zzz, 1.0) * v_texCoord_light_mul.w;
    //gl_FragColor = texture2D(CC_Texture0, v_texCoord_light_mul.xy) * v_texCoord_light_mul.w;
}


