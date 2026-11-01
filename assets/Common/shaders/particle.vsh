#ifndef GL_ES
#define lowp
#endif

attribute vec4 pos2_angle_time;
attribute vec4 linVel2_angVel_life;
attribute vec4 corner2_linAccel2;
attribute vec4 size_sizeOverTime_texCoord;
attribute vec4 startColor;
attribute vec4 endColor;
uniform vec3 params; // (time, loop length, alpha)
varying vec2 texCoord;
varying vec4 color;

void main() {
	vec2 pos = pos2_angle_time.xy;
	float angle = pos2_angle_time.z;
	float startTime = pos2_angle_time.w;
	
	vec2 linearVel = linVel2_angVel_life.xy;
	float angularVel = linVel2_angVel_life.z;
	float life = linVel2_angVel_life.w;
	
	vec2 corner = corner2_linAccel2.xy;
	vec2 linearAcc = corner2_linAccel2.zw;

	float size = size_sizeOverTime_texCoord.x;
	float sizeOverTime = size_sizeOverTime_texCoord.y;
	texCoord = size_sizeOverTime_texCoord.zw;
	
	float currentTime = mod(max(params.x - startTime, 0.0), params.y);
	//float currentTime = mod(max(params.x - startTime, 0.0), params.y);
	float currentTime2 = currentTime * currentTime;
	float t = min(currentTime / life, 1.0);

	float alpha = sin(t * 3.1415);
	float s = (currentTime < life) ? 1.0 : 0.0;
	//vec2 currentSize = size * alphaIn + sizeOverTime * currentTime2;
	//vec2 currentSize = alpha > 0.01 ? (size + sizeOverTime * currentTime) : vec2(0,0);//*alpha;
	float currentSize = (size + sizeOverTime * currentTime) * s;
	float currentAngle = angle + angularVel * currentTime;// + 0.5 * angAcc * currentTime2;
	float cosAng = cos(currentAngle);
	float sinAng = sin(currentAngle);
	vec2 currentCorner = currentSize * corner;
	vec2 rotatedCorner = vec2(currentCorner.x * cosAng - currentCorner.y * sinAng, currentCorner.x * sinAng + currentCorner.y * cosAng);
	vec2 currentPos = pos + rotatedCorner + linearVel * currentTime + 0.5 * linearAcc * currentTime2;
	color = mix(startColor, endColor, t);
	color *= alpha*params.z;
	//color.xyz *= color.a;
	gl_Position = CC_MVPMatrix * vec4(currentPos, 0.0, 1.0);
	//gl_Position = vec4(currentPos, 0.0, 1.0);
}
