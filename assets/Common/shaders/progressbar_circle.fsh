#ifdef GL_ES
precision highp float;
#endif

varying vec2 v_texCoord;

uniform float u_progress;

void main()
{
	vec2 d = v_texCoord - vec2(0.5, 0.5);
	float angle = (3.1415+atan(d.x, -d.y))/(2.0*3.1415);
	//float angle = atan(d.x, -d.y);
	float alpha = (angle <= u_progress) ? 1.0 : 0.0;
	//float alpha = min(max((u_progress-angle)*1000.0, 0.0), 1.0);

    gl_FragColor = texture2D(CC_Texture0, v_texCoord) * alpha;
}