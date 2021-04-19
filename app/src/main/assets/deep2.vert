//基于这个修改
//attribute vec4 vPosition;
//void main() {
//    gl_Position = vPosition;
//}


//定义一个matrix。相当于4x4的矩阵
uniform mat4 u_Matrix;
attribute vec4 vPosition;
void main() {
    //与position相乘
    gl_Position =u_Matrix* vPosition;
}