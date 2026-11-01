#ifdef GL_ES
varying lowp vec2 v_texCoord;
#else
varying vec2 v_texCoord;
#endif


void main()
{
	vec2 center = vec2(0.5, 0.5);
	float threshold = 0.5 / 16.0;
	
	vec4 field = texture2D(CC_Texture0, v_texCoord);
	float d = threshold / field.a;

	if (d < 1.0) {
		vec2 c = vec2(field.r, field.g) - center;
		vec2 texc = d*normalize(c);
		vec4 col = texture2D(CC_Texture1, texc + center);
		gl_FragColor = col;
	} 
	else {
		discard;
	}

}
