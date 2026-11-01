#ifdef GL_ES
varying lowp vec2 v_texCoord;
#else
varying vec2 v_texCoord;
#endif

uniform vec4 u_color;

void main()
{
	gl_FragColor = u_color * texture2D(CC_Texture0, v_texCoord);
}
