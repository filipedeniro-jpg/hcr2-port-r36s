#ifdef GL_ES
varying lowp vec4 v_texCoord_light_mul;
#else
varying vec4 v_texCoord_light_mul;
#endif

void main()
{
    vec4 tex = texture2D(CC_Texture0, v_texCoord_light_mul.xy);
    if (tex.a < 0.05) {
        discard;
    }
    gl_FragColor = tex;
}
