#ifdef GL_ES
varying lowp vec2 v_texCoord;
#else
varying vec2 v_texCoord;
#endif

uniform float u_param;
uniform float u_depth;
uniform float u_coef;
uniform vec4 u_color1;
uniform vec4 u_color2;

uniform sampler2D u_distort;


void main()
{
	//float mask = clamp(length(1.25*(v_texCoord - 0.5) - vec2(0.0,0.2)),0.0,1.0);

	// water wobble
	vec2 wobble_uv = texture2D(u_distort, v_texCoord+u_param*vec2(0.05)).xy;
	float vignette = texture2D(u_distort, v_texCoord).z;
	float depth = clamp(u_depth - v_texCoord.t*u_coef, 0.0, 1.0);

	vec4 col = texture2D(CC_Texture0, v_texCoord+0.025*wobble_uv);
	//col.g = texture2D(CC_Texture0, v_texCoord+0.035*wobble_uv).g;

	// depth color gradient
	vec4 grad = mix(u_color1, u_color2, depth);

	//col = (1.0 - expStep(mask, 0.5, -4.0*(1.0-depth+0.1)))*mix(vec4(1.0, 1.0, 1.0, 1.0), grad, depth+0.25) * col * col;
	col = mix(vec4(1.0, 1.0, 1.0, 1.0), grad, vignette*depth+0.25) * col;
	gl_FragColor = col;
	//gl_FragColor = vec4(vec3(expStep(mask, 0.5, -4.0*(1.0-depth+0.1))+0.5),1.0);
}
