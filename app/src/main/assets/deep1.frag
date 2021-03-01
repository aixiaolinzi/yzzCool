//设置片元着色器的精度。这里值要兼容性能和效率。通常都是选择mediump
precision mediump float;
uniform vec4 vColor;
void main() {
    gl_FragColor = vColor;
}