#ifdef GL_ES
varying lowp vec2 v_texCoord;
varying lowp vec2 v_texCoord2;
precision highp float;
#else
varying vec2 v_texCoord;
varying vec2 v_texCoord2;
#endif

#include "hsv_math.h"

uniform float u_offset;
uniform float u_angle;
uniform vec3 u_color1;
uniform vec3 u_color2;
uniform sampler2D u_mask;
uniform sampler2D u_mask_texture;

vec4 shiftHue(vec4 c, vec4 s)
{
	vec3 hsv = rgb2hsv(c.rgb);
	hsv.x = hsv.x + s.x;
	return vec4(hsv2rgb(hsv), c.a);
}

void main()
{  
    vec3 color1 = vec3(0.4, 0.96, 0.98);
    vec3 color2 = vec3(1.0, 0.94, 0.0);
  
    float mixValue = distance(vec2(v_texCoord2.x + u_offset, 0),vec2(0,0));
    float mixValue2 = distance(vec2(v_texCoord2.x, 0),vec2(1,0));
    
    vec3 color = mix(u_color1,u_color2,mixValue);
    color += mix(u_color2, u_color1, mixValue2);

	vec4 t = texture2D(CC_Texture0, v_texCoord);
	vec4 m = texture2D(u_mask, v_texCoord2);
	vec4 s = shiftHue(t, vec4(color,1.0));
	gl_FragColor = m * t + (1.0 - m) * s;
}
