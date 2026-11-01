attribute vec4 a_position;
attribute vec4 a_texCoord;

uniform vec4 u_params; // (height, depth, z_pos, track)

#ifdef GL_ES
varying lowp vec4 v_texCoord_light_mul;
#else
varying vec4 v_texCoord_light_mul;
#endif

void main()
{
	float z = a_position.z;
	float track = u_params.w;
	float track1_alpha = fract(a_texCoord.w);
	float track2_alpha = (a_texCoord.w - track1_alpha)*0.01;
	float alpha = mix(track1_alpha, track2_alpha, track);
	float width = 0.0625;
	float z_pos = u_params.z;
	float depth = z * width;
	vec2 offset = normalize(vec2(-u_params.x, u_params.y))*(z_pos-width);//(width*1.0);
	gl_Position = CC_MVPMatrix * vec4(a_position.x, a_position.y - (depth - z) * (u_params.x / u_params.y) + offset.x, depth + offset.y, 1.0);
	v_texCoord_light_mul = vec4(a_texCoord.x*2.0, a_texCoord.y, a_texCoord.z, alpha);
}
