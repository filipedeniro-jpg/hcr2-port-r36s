attribute vec4 a_position;
attribute vec4 a_texCoord;

uniform vec4 u_params; // bottom position offset (x, y z) + bottom y-tiling (w)

#ifdef GL_ES
varying lowp vec4 v_texCoord_light_mul;
#else
varying vec4 v_texCoord_light_mul;
#endif

void main()
{
	gl_Position = CC_MVPMatrix * vec4(a_position.xyz + u_params.xyz * a_texCoord.w, 1.0);
	v_texCoord_light_mul = vec4(a_texCoord.xyz, a_position.w);
	v_texCoord_light_mul.y += u_params.w*a_texCoord.w;
}