#ifdef GL_ES
varying lowp vec2 v_texCoord;
precision highp float;
#else
varying vec2 v_texCoord;
#endif


void main()
{
	vec4 t = texture2D(CC_Texture0, v_texCoord);
	gl_FragColor = t;
}
