#ifdef GL_ES
precision lowp float;
#endif


#ifdef GL_ES
varying lowp vec3 v_texCoord_mul;
#else
varying vec3 v_texCoord_mul;
#endif

uniform sampler2D u_texture;

void main()
{
	vec4 tex;
	if(v_texCoord_mul.y <= 1.0){
		tex = texture2D(u_texture, v_texCoord_mul.xy);
	}else{
		tex = texture2D(CC_Texture1, v_texCoord_mul.xy);
	}

	gl_FragColor = tex * vec4(v_texCoord_mul.zzz, 1.0);
}
