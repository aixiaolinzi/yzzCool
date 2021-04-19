////基于下面修改
////定义一个matrix。相当于4x4的矩阵
//uniform mat4 u_Matrix;
//attribute vec4 vPosition;
//void main() {
//    //与position相乘
//    gl_Position =u_Matrix* vPosition;
//}


//定义一个matrix。相当于4x4的矩阵
uniform mat4 u_Matrix;
attribute vec4 vPosition;
attribute vec4 aColor;//顶点颜色
varying  vec4 vColor;//片元颜色
void main() {
    //与position相乘
    gl_Position =u_Matrix* vPosition;
    vColor = aColor;//将顶点颜色传给片元
}