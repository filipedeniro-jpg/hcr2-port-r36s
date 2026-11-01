#ifdef GL_ES
varying lowp vec2 v_texCoord;
varying lowp vec2 v_texTilt;
#else
varying vec2 v_texCoord;
varying vec2 v_texTilt;
#endif

uniform float u_param;
const vec3 beamColor = vec3(0.7,0.9,0.9);

void main()
{
	// noise.x = offset 0, noise.y = offset 1 pix
	vec4 noise = vec4(texture2D(CC_Texture0, v_texTilt).xy,texture2D(CC_Texture0, v_texTilt + vec2(0.0,0.5)).xy);
	vec4 beams = 0.5*sin(u_param*noise) + vec4(0.5);
	vec2 final_beams = beams.xy - clamp(beams.zw*1.7 + vec2((v_texCoord.y - 0.66)*8.0 - 1.6), 0.0,1.0);
	float beam = mix(final_beams.x, final_beams.y, fract(32.0*v_texTilt.x));

	gl_FragColor = vec4(beamColor,0.2*beam);
}
