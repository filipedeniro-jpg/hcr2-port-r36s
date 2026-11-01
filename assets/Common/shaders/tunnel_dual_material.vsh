
attribute vec4 a_position;
attribute vec2 a_texCoord;

#ifdef GL_ES
varying lowp vec3 v_texCoord_mul;
#else
varying vec3 v_texCoord_mul;
#endif

void main()
{
    float light = a_position.w;
    gl_Position = CC_MVPMatrix * vec4(a_position.x, a_position.y, a_position.z, 1.0);
    v_texCoord_mul = vec3(a_texCoord.x, a_texCoord.y, light);
}
