#ifdef GL_ES
precision lowp float;
#endif


#ifdef GL_ES
varying lowp vec4 v_texCoord_mul_alpha;
#else
varying vec4 v_texCoord_mul_alpha;
#endif

uniform sampler2D u_texture;

void main()
{
	vec4 tex = texture2D(u_texture, v_texCoord_mul_alpha.xy);
    gl_FragColor = tex * vec4(v_texCoord_mul_alpha.zzz, v_texCoord_mul_alpha.w);
}
