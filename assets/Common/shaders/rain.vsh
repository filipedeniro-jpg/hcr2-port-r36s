attribute vec4 a_position;
attribute vec2 a_texCoord;

uniform vec3 u_camPos;
uniform vec3 u_offset;
uniform vec3 u_size;
uniform vec3 u_dir;
uniform vec2 u_dropSize;

#ifdef GL_ES
varying lowp vec2 v_texCoord;
#else
varying vec2 v_texCoord;
#endif

void main()
{
	vec3 pos = mod(u_offset*(1.0+sin(a_position.z*10.0)*0.5+0.5) + a_position.xyz - u_camPos, u_size) - u_size*0.5 + u_camPos;

	vec3 dir = u_dir;

	// offset bottom vertices from the tip of the drop
	pos += a_position.w * vec3(dir.y, -dir.x, 0.0) * u_dropSize.x - 2.0 * (1.0 - abs(a_position.w)) * dir * u_dropSize.y;

    gl_Position = CC_PMatrix * vec4(pos.xyz, 1.0);
    v_texCoord = a_texCoord;
}
