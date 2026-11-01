attribute vec4 a_position;
attribute vec4 a_texCoord;

uniform vec4 u_params; // bottom position offset (x, y z) + bottom y-tiling (w)
uniform vec2 u_depthfade;	// fade length, fade start y

#ifdef GL_ES
varying lowp vec4 v_texCoord_light_mul;
varying lowp float v_blendcoef;
#else
varying vec4 v_texCoord_light_mul;
varying float v_blendcoef;
#endif

void main()
{
	gl_Position = CC_MVPMatrix * vec4(a_position.xyz + u_params.xyz * a_texCoord.w, 1.0);
	v_texCoord_light_mul = vec4(a_texCoord.xyz, a_position.w);
	v_texCoord_light_mul.y += u_params.w*a_texCoord.w;
	v_blendcoef = clamp((a_position.y - u_depthfade.y) / u_depthfade.x, 0.0, 1.0);
}
