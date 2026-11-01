#ifdef GL_ES
varying lowp vec3 v_texCoord_alpha;
varying lowp vec2 v_texCoord;
precision highp float;
#else
varying vec3 v_texCoord_alpha;
varying vec2 v_texCoord;
#endif


void main()
{
	float mask = texture2D(CC_Texture0, v_texCoord).r * clamp(texture2D(CC_Texture0, v_texCoord_alpha.xy).g * v_texCoord_alpha.z, 0.1, 0.3);
	vec4 col = vec4(1.0, 1.0, 1.0, mask); 
	gl_FragColor = col;
}
