#version 300 es
precision mediump float;

in vec2 v_TexCoord;
out vec4 outColor;

uniform sampler2D u_Texture;
uniform float u_Time;

void main() {
    vec2 uv = v_TexCoord;

    // Random shift effect based on row position
    float glitchOffset = mod(uv.y * 500.0 + u_Time * 10.0, 2.0) - 1.0;
    uv.x += glitchOffset * 0.02; // Small horizontal distortion

    outColor = texture(u_Texture, uv);
}
