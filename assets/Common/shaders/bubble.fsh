#ifdef GL_ES
varying lowp vec2 v_texCoord;
#else
varying vec2 v_texCoord;
#endif

uniform vec2 u_distscale;
uniform vec2 u_clip;
uniform vec2 u_fieldcoefs;


// draw a metaball with bubble texture

void main()
{
	float threshold = 2.0;
	vec2 tc = vec2(u_distscale.y*v_texCoord.x, v_texCoord.y);
	vec2 c = tc - vec2(0.5, 0.5);

	// discard pixels inside the tube
	if (dot(u_clip, c) > 0.0) {
		discard;
	}
	vec2 cT = tc - vec2(u_distscale.x + 0.5, 0.5);

	// calculate the bubble field strengths
	float d = u_fieldcoefs.x / max(length(c), 0.001);
	float dT = u_fieldcoefs.y / max(length(cT), 0.001); 

	if (d + dT > threshold) {
		//vec2 f = normalize(d*normalize(c) + dT*normalize(cT));
		vec2 f = normalize(d*c + dT*cT);

		float td = threshold / (d + dT);
		vec2 texc = td*f;

		vec4 col = texture2D(CC_Texture0, texc + vec2(0.5, 0.5));

		gl_FragColor = col;
	} else {
		discard;
	}
}
