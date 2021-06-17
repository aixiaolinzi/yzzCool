////定义一个matrix。相当于4x4的矩阵
//uniform mat4 u_Matrix;
//attribute vec4 vPosition;
//attribute vec4 aColor;//顶点颜色
//varying  vec4 vColor;//片元颜色
//void main() {
//    //与position相乘
//    gl_Position =u_Matrix* vPosition;
//    vColor = aColor;//将顶点颜色传给片元
//}

attribute vec4 a_Position;
//添加了一个 a_TextureCoordinates ,因为他有两个分量。S坐标和T  坐标，所以定义为vec2.
attribute vec2 a_TextureCoordinates;
//然后把坐标传递给被插值的varying
varying vec2 v_TextureCoordinates;

void main(){
    gl_Position = a_Position;
    v_TextureCoordinates = a_TextureCoordinates;
}