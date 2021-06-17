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

void main(){
    //渲染2D纹理，交给fragColor
    gl_FragColor=texture2D(u_TextureUnit,v_TextureCoordinates);
}