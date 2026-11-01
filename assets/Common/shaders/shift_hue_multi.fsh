#ifdef GL_ES
varying lowp vec2 v_texCoord;
varying lowp vec2 v_texCoord2;
//varying lowp vec4 v_color;
precision highp float;
#else
varying vec2 v_texCoord;
varying vec2 v_texCoord2;
//varying vec4 v_color;
#endif

#include "hsv_math.h"

uniform vec3 u_shift;
uniform sampler2D u_mask;

vec4 shiftHue(vec4 c, float s)
{
	vec3 hsv = rgb2hsv(c.rgb);
	hsv.x = hsv.x + s;
	return vec4(hsv2rgb(hsv), c.a);
}

void main()
{
	vec4 t = texture2D(CC_Texture0, v_texCoord);
	vec4 m = texture2D(u_mask, v_texCoord2);

	vec4 g = shiftHue(t, u_shift.x);
	vec4 b = shiftHue(t, u_shift.y);
	vec4 a = shiftHue(t, u_shift.z);
    
	gl_FragColor = m.r * t + m.g * g + m.b * b + (1.0 - m.a) * a;
}
