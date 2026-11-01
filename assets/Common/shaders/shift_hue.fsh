#ifdef GL_ES
varying lowp vec2 v_texCoord;
//varying lowp vec4 v_color;
#else
varying vec2 v_texCoord;
//varying vec4 v_color;
#endif

#include "hsv_math.h"

uniform float u_shift;

vec4 shiftHue(vec4 c, float s)
{
	vec3 hsv = rgb2hsv(c.rgb);
	hsv.x = hsv.x + s;
	return vec4(hsv2rgb(hsv), c.a);
}

void main()
{
	vec4 t = texture2D(CC_Texture0, v_texCoord);
	gl_FragColor = shiftHue(t, u_shift);
}
