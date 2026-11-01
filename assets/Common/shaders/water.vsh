
attribute vec4 a_position;
attribute vec2 a_texCoord;

uniform float u_time;

#ifdef GL_ES
varying lowp vec4 v_texCoord_mul_alpha;
#else
varying vec4 v_texCoord_mul_alpha;
#endif

void main()
{
	float phase = sin(u_time*2.0+a_position.x * 2.0 + a_position.z*3.0);
	float alpha = 1.0;//a_position.w * a_texCoord.z;
	float wave = phase * a_position.w * 0.1;
    float light = 1.0;//a_position.w;//(phase*0.1+0.9)* a_position.w;
    gl_Position = CC_MVPMatrix * vec4(a_position.x + wave, a_position.y + wave, a_position.z, 1.0);
    v_texCoord_mul_alpha = vec4((a_texCoord.x + mod(u_time*0.1, 1.0))*5.0, a_texCoord.y + wave * 0.0, light, alpha);
    //v_texCoord_mul_alpha = vec4(a_texCoord.x * 5.0, a_texCoord.y + wave * 2.0 - mod(u_time*0.1, 1.0), light, alpha);
    //v_texCoord_mul_alpha = vec4(a_texCoord.x * 5.0, a_texCoord.y + wave * 2.0, light, alpha);
}
