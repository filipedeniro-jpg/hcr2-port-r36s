#ifndef GL_ES
#define lowp
#endif

varying lowp vec4 v_texCoord_alpha_light;

void main() {
	vec4 tex = texture2D(CC_Texture0, v_texCoord_alpha_light.xy);
	gl_FragColor = tex;
}
