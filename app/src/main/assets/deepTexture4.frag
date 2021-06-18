////设置片元着色器的精度。这里值要兼容性能和效率。通常都是选择mediump
////varying意思是可以传值
//precision mediump float;
//varying  vec4 vColor;
//void main() {
//    gl_FragColor = vColor;
//}

precision mediump float;

//在片元着色器这里添加这个 sampler2D 表示我们要添加2D贴图
uniform sampler2D u_TextureUnit;
varying vec2 v_TextureCoordinates;

//定义一个u_ChangeColor,因为颜色的变量是RGB,所以使用vec3
uniform vec3 u_ChangeColor;

void main(){
    //渲染2D纹理，交给fragColor
    //gl_FragColor=texture2D(u_TextureUnit,v_TextureCoordinates);

    //得到2d color
    vec4 nColor=texture2D(u_TextureUnit,v_TextureCoordinates);
    //黑白图片
    float c= nColor.r * u_ChangeColor.r + nColor.g*u_ChangeColor.g + nColor.b*u_ChangeColor.b;
    gl_FragColor = vec4(c,c,c,nColor.a);
}