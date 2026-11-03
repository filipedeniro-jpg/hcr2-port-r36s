#ifdef GL_ES
varying lowp vec4 v_texCoord_light_mul;
varying lowp float v_blendcoef;
#else
varying vec4 v_texCoord_light_mul;
varying float v_blendcoef;
#endif

void main()
{
    vec4 col_bot = texture2D(CC_Texture0, v_texCoord_light_mul.xy);
    vec4 col_top = texture2D(CC_Texture2, v_texCoord_light_mul.xy);
    vec4 tex = mix(col_bot, col_top, v_blendcoef);
    gl_FragColor = vec4(tex.rgb, 1.0);
}
