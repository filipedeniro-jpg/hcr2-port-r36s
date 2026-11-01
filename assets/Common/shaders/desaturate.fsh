#ifdef GL_ES
varying lowp vec2 v_texCoord;
precision highp float;
#else
varying vec2 v_texCoord;
#endif

#include "hsv_math.h"

vec4 desaturate(vec4 c)
{
	vec3 hsv = rgb2hsv(c.rgb);
	hsv.y = 0.0;
	return vec4(hsv2rgb(hsv), c.a);
}

void main()
{
	vec4 t = texture2D(CC_Texture0, v_texCoord);
	gl_FragColor = desaturate(t);
}
