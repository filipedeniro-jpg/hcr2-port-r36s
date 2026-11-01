#ifdef GL_ES
precision highp float;
#endif

varying vec2 v_texCoord;

uniform vec3 u_texsize;
uniform vec4 u_color;

void main()
{
    vec2 radius = u_texsize.xy * u_texsize.z;
    vec2 diagRadius = u_texsize.xy * u_texsize.z * 0.75; // Diagonal uses slightly smaller radius

    float center = texture2D(CC_Texture0, v_texCoord).a;
    float smp0 = texture2D(CC_Texture0, v_texCoord + vec2(0.0, radius.y)).a;
    float smp1 = texture2D(CC_Texture0, v_texCoord + vec2(0.0, -radius.y)).a;
    float smp2 = texture2D(CC_Texture0, v_texCoord + vec2(radius.x, 0.0)).a;
    float smp3 = texture2D(CC_Texture0, v_texCoord + vec2(-radius.x, 0.0)).a;
    float smp4 = texture2D(CC_Texture0, v_texCoord + vec2(-diagRadius.x, -diagRadius.y)).a;
    float smp5 = texture2D(CC_Texture0, v_texCoord + vec2(-diagRadius.x, diagRadius.y)).a;
    float smp6 = texture2D(CC_Texture0, v_texCoord + vec2(diagRadius.x, -diagRadius.y)).a;
    float smp7 = texture2D(CC_Texture0, v_texCoord + vec2(diagRadius.x, diagRadius.y)).a;

    float avg = (smp0 + smp1 + smp2 + smp3 + smp4 + smp5 + smp6 + smp7) * 0.125;
    float outside = 1.0 - avg;
    // Fade effect inside elements
    float fade = (1.0 - 2.0 * center);

    // Use 4x "overburn" for strength, but clamp to 1 max finally
    float strength = min(1.0, 4.0 * avg * outside * fade * u_color.a);

    gl_FragColor = vec4(u_color.rgb * strength, strength);
}