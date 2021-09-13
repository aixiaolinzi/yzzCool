attribute vec4 vPosition;
attribute vec4 aColor;//顶点颜色
varying  vec4 vColor;//片元颜色

void main() {
    gl_Position = vPosition;
    vColor = aColor;//将顶点颜色传给片元
}
