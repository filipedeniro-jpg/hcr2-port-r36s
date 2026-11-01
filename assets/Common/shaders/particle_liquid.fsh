/*#ifdef GL_ES
precision lowp float;
#endif

#ifdef GL_ES
varying lowp vec2 v_velocity;
#else
varying vec2 v_velocity;
#endif
*/
// TODO: replace this with a texture lookup, scale and rotate using particle velocity

void main()
{
	vec2 center = vec2(0.5, 0.5);
	vec2 c = vec2(gl_PointCoord) - center;

	float f = max(0.0, (0.025 / length(c)) - 0.05);
	vec2 force = vec2(16.0*f*c.x, 16.0*f*c.y) + center;

	gl_FragColor = vec4(force.x, force.y, 0.0, f);

}
