
attribute vec4 a_position;
attribute vec2 a_texCoord;

uniform float u_alpha;

#ifdef GL_ES
varying lowp vec4 v_texCoord_mul_alpha;
#else
varying vec4 v_texCoord_mul_alpha;
#endif

void main()
{
	float alpha = u_alpha;
    float light = 1.0;
    gl_Position = CC_PMatrix * vec4(a_position.x, a_position.y, a_position.z, 1.0);
    v_texCoord_mul_alpha = vec4(a_texCoord.x, a_texCoord.y, light, alpha);
}
