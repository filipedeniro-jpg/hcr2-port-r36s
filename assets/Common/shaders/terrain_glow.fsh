#ifdef GL_ES
varying lowp vec4 v_texCoord_light_mul;
#else
varying vec4 v_texCoord_light_mul;
#endif

uniform float u_time;

void main()
{
    vec4 tex = texture2D(CC_Texture0, v_texCoord_light_mul.xy);
    vec4 col = vec4(tex.x, 0.663 * tex.x, 0.521 * tex.x, 1.0);
    //vec4 col = vec4(0.6 * tex.x, 0.6 * tex.x, 0.7 * tex.x, 1.0);
    vec4 glow = (0.25 * cos(4.0 * u_time) + 0.75) * vec4(tex.y, tex.z, 0.0, 0.0);

    gl_FragColor = col * vec4(v_texCoord_light_mul.zzz, 1.0) * v_texCoord_light_mul.w + glow;
}