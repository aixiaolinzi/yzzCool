#extension GL_OES_EGL_image_external : require
precision mediump float;

varying vec2 vTextureCoordinate;
uniform samplerExternalOES uTexture;
uniform int show;
uniform float scaleWidth;
uniform float scaleHeight;
uniform float shiftX;
uniform float shiftY;
uniform float limitX;
uniform float limitY;


void main() {
    gl_FragColor = texture2D(uTexture,vTextureCoordinate);
    if(show == 1){
        vec2 srcCoord = vec2(1);
        if(vTextureCoordinate.x< limitX && vTextureCoordinate.y < limitY){
            srcCoord.x = vTextureCoordinate.x *  scaleHeight  + shiftX ;
            srcCoord.y = vTextureCoordinate.y *  scaleWidth  +  shiftY ;
            gl_FragColor = texture2D(uTexture,srcCoord);
        }
    }

}