#ifdef GL_ES
precision mediump float;
varying lowp vec2 v_texCoord;
#else
varying vec2 v_texCoord;
#endif

uniform sampler2D u_texture;
uniform float u_phase;

const float ANGLE_FALLOFF = 0.05;
const float DARK_INTENSITY = 0.2;
const float LIGHT_INTENSITY = 1.0;
const float PIOVER2 = 1.570796;
const float PI2 = 6.28318;

void main()
{
    //gl_FragColor = texture2D(u_texture, v_texCoord);
	
	// Pixel offset from center
	float dx = 0.5 - v_texCoord.x;
	float dy = v_texCoord.y - 0.5;

	// Precalculations
	float dxdx = dx*dx;
	float dydy = dy*dy;
	float dxdy = dx*dy;
	
	// Fast atan2 approximation
	float c1 = clamp(10000.0*(abs(dx)-abs(dy)), 0.0, 1.0);
	float c2 = 1.0 - c1;
	float pixelAngle = -(c1 * ((-dxdy) / (dxdx+0.28088*dydy) + PIOVER2*sign(dx)) + c2 * (-PIOVER2*sign(dxdy) + PIOVER2*sign(dx) + (dxdy) / (dydy + 0.28088*dxdx)));
	pixelAngle = -pixelAngle;
	//pixelAngle = 3.14159 - pixelAngle;
	if(pixelAngle < 0.0) pixelAngle += PI2;
	
	float intensity = LIGHT_INTENSITY;
	
	// Apply angular falloff
	//float angleOffset = ANGLE_FALLOFF - (pixelAngle / PI2 - u_phase);
	float angleOffset = ANGLE_FALLOFF - (pixelAngle / PI2 - u_phase);
	float angleFalloff = clamp(angleOffset / ANGLE_FALLOFF, 0.0, 1.0);
	intensity *= angleFalloff;
	
	intensity = clamp(intensity, DARK_INTENSITY, LIGHT_INTENSITY);
	
	//intensity = 0.3*pixelAngle;

	gl_FragColor = texture2D(u_texture, v_texCoord);
	gl_FragColor[0] *= intensity;
	gl_FragColor[1] *= intensity;
	gl_FragColor[2] *= intensity;
}
